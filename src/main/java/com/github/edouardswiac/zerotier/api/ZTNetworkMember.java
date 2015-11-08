package com.github.edouardswiac.zerotier.api;


import java.util.Objects;

public final class ZTNetworkMember {
  private String networkId;
  private String state;
  private String nodeId;
  private ZTNetworkMemberConfig config;

  public ZTNetworkMember() {}
  public ZTNetworkMember(String networkId, String address) {
    this.networkId = networkId;
    this.nodeId = address;
    this.config = new ZTNetworkMemberConfig(networkId, address);
  }

  public String getNetworkId() {
    return networkId;
  }

  public String getState() {
    return state;
  }

  public String getNodeId() {
    return nodeId;
  }

  public ZTNetworkMemberConfig getConfig() {
    return config;
  }

  public void setConfig(ZTNetworkMemberConfig config) {
    this.config = config;
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
    return Objects.hash(nodeId);
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("ZTNetworkMember{");
    sb.append("networkId='").append(networkId).append('\'');
    sb.append(", state='").append(state).append('\'');
    sb.append(", nodeId='").append(nodeId).append('\'');
    sb.append(", config=").append(config);
    sb.append('}');
    return sb.toString();
  }
}
