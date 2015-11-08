package com.github.edouardswiac.zerotier.api;


import java.util.Objects;

public final class ZTNetworkMemberConfig {
  private String nwid;
  private String address;
  private Boolean authorized;
  private Boolean activeBridge;
  private String identity;

  public ZTNetworkMemberConfig() {}
  public ZTNetworkMemberConfig(String networkId, String address) {
    this.nwid = networkId;
    this.address = address;
  }

  public String getNetworkId() {
    return nwid;
  }

  public String getAddress() {
    return address;
  }

  public Boolean isAuthorized() {
    return authorized;
  }

  public void setAuthorized(Boolean authorized) {
    this.authorized = authorized;
  }

  public Boolean getActiveBridge() {
    return activeBridge;
  }

  public void setActiveBridge(Boolean activeBridge) {
    this.activeBridge = activeBridge;
  }

  public String getIdentity() {
    return identity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ZTNetworkMemberConfig that = (ZTNetworkMemberConfig) o;
    return Objects.equals(address, that.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address);
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("ZTNetworkMemberConfig{");
    sb.append("nwid='").append(nwid).append('\'');
    sb.append(", address='").append(address).append('\'');
    sb.append(", authorized=").append(authorized);
    sb.append(", activeBridge=").append(activeBridge);
    sb.append('}');
    return sb.toString();
  }
}
