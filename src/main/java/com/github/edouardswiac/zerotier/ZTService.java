package com.github.edouardswiac.zerotier;


import com.github.edouardswiac.zerotier.api.ZTNetwork;
import com.github.edouardswiac.zerotier.api.ZTStatus;

import java.util.List;

public interface ZTService {
  String API_VERSION = "0.6";

  List<ZTNetwork> getNetworks();
  public void getNetwork();
  public void updateNetowk();
  public void deleteNetwork();
  public void listNetworks();

  ZTStatus status();

  public void getNetworkMembers();
  public void getNetworkMember();
  public void updateNetworkMember();
}
