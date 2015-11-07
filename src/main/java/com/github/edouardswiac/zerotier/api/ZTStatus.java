package com.github.edouardswiac.zerotier.api;



public class ZTStatus {
  private Boolean online;
  private String version;

  public ZTStatus() {
  }

  public boolean isOnline() {
    return online;
  }

  public String getVersion() {
    return version;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("ZTStatus{");
    sb.append("online=").append(online);
    sb.append(", version='").append(version).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
