package com.github.edouardswiac.zerotier;


import com.github.edouardswiac.zerotier.api.ZTStatus;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class ZTServiceTest {
  ZTService s;
  @Before
  public void setup() {
    s = new ZTServiceImpl(ZTServiceImpl.DEFAULT_URL, "y9mBNRmamDTK5GeBSPDhR16UHP1FSXcP");

  }
  @Test
  public void testStatus() {
    ZTStatus status = s.status();
    assertThat(status.isOnline());
    assertThat(status.getVersion()).startsWith(ZTService.API_VERSION);
  }

  @Test
  public void testGetNetworks() {

  }
}
