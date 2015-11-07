package com.github.edouardswiac.zerotier;


import com.github.edouardswiac.zerotier.api.ZTNetwork;
import com.github.edouardswiac.zerotier.api.ZTNetworkMember;
import com.github.edouardswiac.zerotier.api.ZTStatus;

import java.util.List;
import java.util.Map;

public interface ZTService {
  String API_VERSION = "0.6";
  String ZT_COM_CENTRAL_URL = "https://my.zerotier.com/api/";

  List<ZTNetwork> getNetworks();

  public void getNetwork();
  public void updateNetowk();
  public void deleteNetwork();
  public void listNetworks();

  ZTStatus status();

  Map<String, Integer> getNetworkMembers(String networkId);
  public void getNetworkMember();
  public void updateNetworkMember();
}
