package com.github.edouardswiac.zerotier;


import com.github.edouardswiac.zerotier.api.ZTStatus;

public interface ZTService {
  public static String API_VERSION = "0.6.0";
  public static String ZT_COM_CENTRAL_URL = "https://my.zerotier.com/api/";

  public void createNetwork();
  public void getNetwork();
  public void updateNetowk();
  public void deleteNetwork();
  public void listNetworks();

  public ZTStatus status();

  public void getNetworkMembers();
  public void getNetworkMember();
  public void updateNetworkMember();
}
