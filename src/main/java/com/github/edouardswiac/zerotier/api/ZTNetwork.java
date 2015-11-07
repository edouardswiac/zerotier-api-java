package com.github.edouardswiac.zerotier.api;


import java.util.Objects;

public class ZTNetwork {
  private String id;
  private ZTNetworkConfig config;

  public ZTNetwork() {}

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ZTNetworkConfig getConfig() {
    return config;
  }

  public void setConfig(ZTNetworkConfig config) {
    this.config = config;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ZTNetwork ztNetwork = (ZTNetwork) o;
    return Objects.equals(id, ztNetwork.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("ZTNetwork{");
    sb.append("id='").append(id).append('\'');
    sb.append(", config=").append(config);
    sb.append('}');
    return sb.toString();
  }
}
