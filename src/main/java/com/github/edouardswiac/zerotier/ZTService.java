package com.github.edouardswiac.zerotier;


import com.github.edouardswiac.zerotier.api.ZTStatus;

public interface ZTService {
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
