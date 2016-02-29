package com.github.edouardswiac.zerotier;


import com.github.edouardswiac.zerotier.api.ZTNetwork;
import com.github.edouardswiac.zerotier.api.ZTNetworkMember;
import com.github.edouardswiac.zerotier.api.ZTStatus;

import com.github.edouardswiac.zerotier.api.ZTNet;
import com.github.edouardswiac.zerotier.api.ZTController;
import com.github.edouardswiac.zerotier.api.ZTCNetwork;
import com.github.edouardswiac.zerotier.api.ZTCMember;

import java.util.List;
import java.util.Map;

public interface ZTService {
  String API_VERSION = "0.6";
  String ZT_COM_CENTRAL_URL = "https://my.zerotier.com/api/";

  ZTStatus status();

  void createNetwork(ZTNetwork network);
  List<ZTNetwork> getNetworks();
  ZTNetwork getNetwork(String networkId);
  void updateNetwork(ZTNetwork network);
  void deleteNetwork(String networkId);

  void createNetworkMember(ZTNetworkMember networkMember);
  Map<String, Integer> getNetworkMembers(String networkId);
  ZTNetworkMember getNetworkMember(String networkId, String address);
  void updateNetworkMember(ZTNetworkMember networkMember);
  void deleteNetworkMember(String networkId, String address);

  List<ZTNet> getNets();
  ZTController controller();
  List<String> getCNetworks();
  ZTCNetwork getCNetwork(String networkId);
  ZTCNetwork updateCNetwork(ZTCNetwork network);
  void deleteCNetwork(String networkId);

  Map<String, Integer> getCMembers(String networkId);
  ZTCMember getCMember(String networkId, String memberId);
  ZTCMember updateCMember(ZTCMember member);
  void deleteCMember(ZTCMember member);
  void deleteCMember(String networkId, String memberId);
}
