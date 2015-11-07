package com.github.edouardswiac.zerotier.api;


public class ZTNetworkConfig {
  private String name;
  private int authorizedMemberCount;

  public ZTNetworkConfig() {}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAuthorizedMemberCount() {
    return authorizedMemberCount;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("ZTNetworkConfig{");
    sb.append("name='").append(name).append('\'');
    sb.append(", authorizedMemberCount=").append(authorizedMemberCount);
    sb.append('}');
    return sb.toString();
  }
}
