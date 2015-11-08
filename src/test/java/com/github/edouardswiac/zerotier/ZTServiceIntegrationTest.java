package com.github.edouardswiac.zerotier;


import com.github.edouardswiac.zerotier.api.ZTNetwork;
import com.github.edouardswiac.zerotier.api.ZTNetworkMember;
import com.github.edouardswiac.zerotier.api.ZTStatus;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.assertj.core.api.Assertions.*;

public class ZTServiceIntegrationTest {
  ZTService s;
  static Random random = new Random();
  static String authToken = System.getenv("ZT_AUTH_TOKEN");
  static String newNetworkName = "integration testing" + random.nextLong();
  static String newMemberAddress = Integer.toString(random.nextInt());

  @BeforeClass
  public static void before() {
    if (authToken == null) fail("an auth token is required to run the integration test suite");
  }

  @Before
  public void setup() {
    s = new ZTServiceImpl(authToken);
  }

  @Test
  public void testStatus() {
    ZTStatus status = s.status();
    System.out.println(status);
    assertThat(status.isOnline());
    assertThat(status.getVersion()).startsWith(ZTService.API_VERSION);
  }

  @Test
  public void testGetNetworks() {
    List<ZTNetwork> networks = s.getNetworks();
    System.out.println(networks);
  }

  @Test
  public void testCRUDNetworkAndCRUDMember() {
    ZTNetwork network = null;
    // create network
    s.createNetwork(new ZTNetwork(newNetworkName));
    for(ZTNetwork n: s.getNetworks()) {
      if (n.getConfig().getName().equals(newNetworkName)) {
        network = n;
      }
    }
    assertThat(network).isNotNull().withFailMessage("network was not created");
    String networkId = network.getId();

    // network has no members yet
    Map<String, Integer> membersWithRevisions = s.getNetworkMembers(networkId);
    assertThat(membersWithRevisions).isEmpty();

    // create member
    ZTNetworkMember ztNetworkMember = new ZTNetworkMember(networkId, newMemberAddress);
    s.createNetworkMember(ztNetworkMember);

    // make sure it's there
    ztNetworkMember = s.getNetworkMember(networkId, newMemberAddress);
    assertThat(ztNetworkMember).isNotNull().withFailMessage("new member was not created");
    assertThat(ztNetworkMember.getNetworkId()).isEqualTo(networkId);
    assertThat(ztNetworkMember.getNodeId()).isEqualTo(newMemberAddress);

    // update member
    boolean oldIsAuth = ztNetworkMember.getConfig().isAuthorized();
    boolean newIsAuth = !oldIsAuth;

    ztNetworkMember.getConfig().setAuthorized(newIsAuth);
    s.updateNetworkMember(ztNetworkMember);

    ztNetworkMember = s.getNetworkMember(networkId, newMemberAddress);
    assertThat(ztNetworkMember.getConfig().isAuthorized()).isEqualTo(newIsAuth).withFailMessage("new member not upated");

    ztNetworkMember = s.getNetworkMember(networkId, newMemberAddress);
    ztNetworkMember.getConfig().setAuthorized(oldIsAuth);
    s.updateNetworkMember(ztNetworkMember);

    ztNetworkMember = s.getNetworkMember(networkId, newMemberAddress);
    assertThat(ztNetworkMember.getConfig().isAuthorized()).isEqualTo(oldIsAuth).withFailMessage("new member not upated");;

    // update network testing this by toggling the name
    ZTNetwork ztNetwork = s.getNetwork(networkId);
    String oldName = ztNetwork.getConfig().getName();
    String testName = oldName+" updated";

    System.out.println("setting up new name");
    ztNetwork.getConfig().setName(testName);
    System.out.println(ztNetwork);
    s.updateNetwork(ztNetwork);

    System.out.println("checking that new name has been applied");
    ztNetwork = s.getNetwork(networkId);
    System.out.println(ztNetwork);
    assertThat(ztNetwork.getConfig().getName()).isEqualTo(testName).withFailMessage("network not upated");

    System.out.println("reverting to old name");
    ztNetwork.getConfig().setName(oldName);
    s.updateNetwork(ztNetwork);

    System.out.println("checking that old name has been restored");
    ztNetwork = s.getNetwork(networkId);
    System.out.println(ztNetwork);
    assertThat(ztNetwork.getConfig().getName()).isEqualTo(oldName).withFailMessage("network not upated");

    // delete member
    s.deleteNetworkMember(networkId, newMemberAddress);

    // delete network
    s.deleteNetwork(network.getId());
  }

}
