package com.github.edouardswiac.zerotier;


import com.github.edouardswiac.zerotier.api.ZTStatus;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class ZTServiceTest {
  @Test
  public void testStatus() {
    ZTService s = new ZTServiceImpl(ZTServiceImpl.ZT_COM_CENTRAL_URL, "");
    ZTStatus status = s.status();

    System.out.println(status);
    assertThat(status.isOnline());
  }
}
