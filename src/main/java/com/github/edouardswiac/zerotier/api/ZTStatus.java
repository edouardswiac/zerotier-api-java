package com.github.edouardswiac.zerotier.api;


public final class ZTStatus {
  private String  address;
  private String  publicIdentity;
  private long    worldId;
  private long    worldTimestamp;
  private Boolean online;
  private Boolean tcpFallbackActive;
  private int     versionMajor;
  private int     versionMinor;
  private int     versionRev;
  private String  version;
  private long    clock;
  private Object  cluster;

  public ZTStatus() {
  }

  public String getAddress() {
    return address;
  }

  public String getPublicIdentity() {
    return publicIdentity;
  }

  public long getWorldId() {
    return worldId;
  }

  public long getWorldTimestamp() {
    return worldTimestamp;
  }

  public Boolean getOnline() {
    return online;
  }

  public Boolean getTcpFallbackActive() {
    return tcpFallbackActive;
  }

  public int getVersionMajor() {
    return versionMajor;
  }

  public int getVersionMinor() {
    return versionMinor;
  }

  public int getVersionRev() {
    return versionRev;
  }

  public long getClock() {
    return clock;
  }

  public Object getCluster() {
    return cluster;
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
