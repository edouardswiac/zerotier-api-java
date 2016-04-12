package com.github.edouardswiac.zerotier.api;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.annotations.SerializedName;

public final class ZTCNetwork implements Cloneable {
  private String                               nwid;
  protected String                             name;
  @SerializedName("private") protected boolean privat;
  protected boolean                            enableBroadcast;
  protected boolean                            allowPassiveBridging;
  protected String                             v4AssignMode;
  protected String                             v6AssignMode;
  protected int                                multicastLimit;
  private long                                 creationTime;
  private int                                  revision;
  private int                                  memberRevisionCounter;
  private long                                 clock;
  private int                                  authorizedMemberCount;
  protected List<ZTCRelay>                     relays;
  protected List<String>                       ipLocalRoutes;
  protected List<ZTCAssignment>                 ipAssignmentPools;
  protected List<ZTCRule>                      rules;

  private String                               controllerInstanceId;
  private List<String>                         gateways;

  public String getNwid() {
    return nwid;
  }

  public String getControllerInstanceId() {
    return controllerInstanceId;
  }

  public long getClock() {
    return clock;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isPrivate() {
    return privat;
  }

  public void setPrivate(boolean privat) {
    this.privat = privat;
  }

  public boolean isEnableBroadcast() {
    return enableBroadcast;
  }

  public void setEnableBroadcast(boolean enableBroadcast) {
    this.enableBroadcast = enableBroadcast;
  }

  public boolean isAllowPassiveBridging() {
    return allowPassiveBridging;
  }

  public void setAllowPassiveBridging(boolean allowPassiveBridging) {
    this.allowPassiveBridging = allowPassiveBridging;
  }

  public String getV4AssignMode() {
    return v4AssignMode;
  }

  public void setV4AssignMode(String v4AssignMode) {
    this.v4AssignMode = v4AssignMode;
  }

  public String getV6AssignMode() {
    return v6AssignMode;
  }

  public void setV6AssignMode(String v6AssignMode) {
    this.v6AssignMode = v6AssignMode;
  }

  public int getMulticastLimit() {
    return multicastLimit;
  }

  public void setMulticastLimit(int multicastLimit) {
    this.multicastLimit = multicastLimit;
  }

  public long getCreationTime() {
    return creationTime;
  }

  public int getRevision() {
    return revision;
  }

  public int getMemberRevisionCounter() {
    return memberRevisionCounter;
  }

  public int getAuthorizedMemberCount() {
    return authorizedMemberCount;
  }

  public List<ZTCRelay> getRelays() {
    return relays;
  }

  public void setRelays(List<ZTCRelay> relays) {
    this.relays = relays;
  }

  public List<String> getGateways() {
    return gateways;
  }

  public List<String> getIpLocalRoutes() {
    return ipLocalRoutes;
  }

  public void setIpLocalRoutes(List<String> ipLocalRoutes) {
    this.ipLocalRoutes = ipLocalRoutes;
  }

  public List<ZTCAssignment> getIpAssignmentPools() {
    return ipAssignmentPools;
  }

  public void setIpAssignmentPools(List<ZTCAssignment> ipAssignmentPools) {
    this.ipAssignmentPools = ipAssignmentPools;
  }

  public List<ZTCRule> getRules() {
    return rules;
  }

  public void setRules(List<ZTCRule> rules) {
    this.rules = rules;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (allowPassiveBridging ? 1231 : 1237);
    result = prime * result + authorizedMemberCount;
    result = prime * result + ((controllerInstanceId == null) ? 0 : controllerInstanceId.hashCode());
    result = prime * result + (enableBroadcast ? 1231 : 1237);
    result = prime * result + ((gateways == null) ? 0 : gateways.hashCode());
    result = prime * result + ((ipAssignmentPools == null) ? 0 : ipAssignmentPools.hashCode());
    result = prime * result + ((ipLocalRoutes == null) ? 0 : ipLocalRoutes.hashCode());
    result = prime * result + multicastLimit;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((nwid == null) ? 0 : nwid.hashCode());
    result = prime * result + (privat ? 1231 : 1237);
    result = prime * result + ((v4AssignMode == null) ? 0 : v4AssignMode.hashCode());
    result = prime * result + ((v6AssignMode == null) ? 0 : v6AssignMode.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ZTCNetwork other = (ZTCNetwork) obj;
    if (allowPassiveBridging != other.allowPassiveBridging)
      return false;
    if (authorizedMemberCount != other.authorizedMemberCount)
      return false;
    if (controllerInstanceId == null) {
      if (other.controllerInstanceId != null)
        return false;
    } else if (!controllerInstanceId.equals(other.controllerInstanceId))
      return false;
    if (enableBroadcast != other.enableBroadcast)
      return false;
    if (gateways == null) {
      if (other.gateways != null)
        return false;
    } else if (!gateways.equals(other.gateways))
      return false;
    if (ipAssignmentPools == null) {
      if (other.ipAssignmentPools != null)
        return false;
    } else if (!ipAssignmentPools.equals(other.ipAssignmentPools))
      return false;
    if (ipLocalRoutes == null) {
      if (other.ipLocalRoutes != null)
        return false;
    } else if (!ipLocalRoutes.equals(other.ipLocalRoutes))
      return false;
    if (multicastLimit != other.multicastLimit)
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (nwid == null) {
      if (other.nwid != null)
        return false;
    } else if (!nwid.equals(other.nwid))
      return false;
    if (privat != other.privat)
      return false;
    if (v4AssignMode == null) {
      if (other.v4AssignMode != null)
        return false;
    } else if (!v4AssignMode.equals(other.v4AssignMode))
      return false;
    if (v6AssignMode == null) {
      if (other.v6AssignMode != null)
        return false;
    } else if (!v6AssignMode.equals(other.v6AssignMode))
      return false;
    return true;
  }

  @Override
  public ZTCNetwork clone() {
    try {
      ZTCNetwork n = (ZTCNetwork) super.clone();
      n.relays = new ArrayList<>(relays);
      n.ipLocalRoutes = new ArrayList<>(ipLocalRoutes);
      n.ipAssignmentPools = new ArrayList<>(ipAssignmentPools);
      n.rules = new ArrayList<>(rules);
      n.gateways = new ArrayList<>(gateways);
      return n;
    } catch (CloneNotSupportedException e) {
      System.err.println("Unable to clone ZTCNetwork: '"+e.getMessage()+"'");
    }
    return null;
  }
  // this is needed to request a new network creation
  public void initNetworkId(String nwid) {
    Pattern p = Pattern.compile("(\\p{XDigit}{10})(______|\\p{XDigit}{6})");
    Matcher m = p.matcher(nwid);
    if (!m.matches())
      throw new IllegalArgumentException("Network id must be a 16 digit hexadecimal number");
    if (!m.group(1).equals(controllerInstanceId))
      throw new IllegalArgumentException("Network id must match Controller Id");
    this.nwid = nwid;
  }

}
