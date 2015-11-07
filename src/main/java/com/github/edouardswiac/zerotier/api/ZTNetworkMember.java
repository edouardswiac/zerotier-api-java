package com.github.edouardswiac.zerotier.api;


import java.util.Objects;

public class ZTNetworkMember {
  private String networkId;

  public ZTNetworkMember() {}

  public String getNetworkId() {
    return networkId;
  }

  public void setNetworkId(String networkId) {
    this.networkId = networkId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ZTNetworkMember that = (ZTNetworkMember) o;
    return Objects.equals(networkId, that.networkId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(networkId);
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("ZTNetworkMember{");
    sb.append("networkId='").append(networkId).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
