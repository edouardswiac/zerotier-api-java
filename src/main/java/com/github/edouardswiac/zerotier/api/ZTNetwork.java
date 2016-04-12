package com.github.edouardswiac.zerotier.api;

import java.util.List;
import java.util.Objects;

public final class ZTNetwork {
  private String nwid;
  private String mac;
  private String name;
  private String status;
  private String type;
  private int mtu;
  private boolean dhcp;
  private boolean bridge;
  private boolean broadcastEnabled;
  private int portError;
  private int netconfRevision;
  private List<String> multicastSubscriptions;
  private List<String> assignedAddresses;
  private String portDeviceName;

  private ZTNetworkConfig config;

  public ZTNetwork() {
  }

  public ZTNetwork(String newNetworkName) {
    this.config = new ZTNetworkConfig();
    this.config.setName(name);
  }

  public String getNwid() {
    return nwid;
  }

  public String getMac() {
    return mac;
  }

  public String getName() {
    return name;
  }

  public int getMtu() {
    return mtu;
  }

  public boolean isDhcp() {
    return dhcp;
  }

  public boolean isBridge() {
    return bridge;
  }

  public boolean isBroadcastEnabled() {
    return broadcastEnabled;
  }

  public int getNetconfRevision() {
    return netconfRevision;
  }

  public List<String> getMulticastSubscriptions() {
    return multicastSubscriptions;
  }

  public List<String> getAssignedAddresses() {
    return assignedAddresses;
  }

  public String getStatus() {
    return status;
  }

  public String getType() {
    return type;
  }

  public int getPortError() {
    return portError;
  }

  public String getPortDeviceName() {
    return portDeviceName;
  }

  public ZTNetworkConfig getConfig() {
    return config;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    ZTNetwork ztNet = (ZTNetwork) o;
    return Objects.equals(nwid, ztNet.nwid);
}

  @Override
  public int hashCode() {
    return Objects.hash(nwid);
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("ZTNetwork{");
    sb.append("nwid='").append(nwid).append('\'');
    sb.append(", name=").append(name);
    sb.append('}');
    return sb.toString();
  }
}
