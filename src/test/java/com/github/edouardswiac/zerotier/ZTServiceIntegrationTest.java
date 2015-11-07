package com.github.edouardswiac.zerotier;


import com.github.edouardswiac.zerotier.api.ZTNetwork;
import com.github.edouardswiac.zerotier.api.ZTStatus;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ZTServiceIntegrationTest {
  ZTService s;
  String authToken = System.getenv("ZT_AUTH_TOKEN");
  String networkId = System.getenv("ZT_NETWORK_ID");

  @Before
  public void setup() {
    if (authToken == null) fail("an auth token is required to run the integration test suite");
    if (networkId == null) fail("a network ID with members is required to run the integration test suite");
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
    System.out.println(networks.toString());
  }


  @Test
  public void testGetMembers() {
    Map<String, Integer> membersWithRevisions = s.getNetworkMembers(networkId);
    System.out.println(membersWithRevisions);
    assertThat(membersWithRevisions).isNotEmpty();
  }
}
