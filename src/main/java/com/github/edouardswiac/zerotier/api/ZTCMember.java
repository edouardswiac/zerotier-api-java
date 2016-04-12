package com.github.edouardswiac.zerotier.api;

import java.util.ArrayList;
import java.util.List;

public class ZTCMember implements Cloneable {
  private String				nwid;
  private long					clock;
  private String				address;
  private boolean				authorized;
  private boolean				activeBridge;
  private String				identity;
  private List<String>	ipAssignments;
  private int						memberRevision;

  public boolean isAuthorized() {
    return authorized;
  }

  public void setAuthorized(boolean authorized) {
    this.authorized = authorized;
  }

  public boolean isActiveBridge() {
    return activeBridge;
  }

  public void setActiveBridge(boolean activaBridge) {
    this.activeBridge = activaBridge;
  }

  public List<String> getIpAssignments() {
    return ipAssignments;
  }

  public void setIpAssignments(List<String> ipAssignments) {
    this.ipAssignments = ipAssignments;
  }

  public String getNwid() {
    return nwid;
  }

  public long getClock() {
    return clock;
  }

  public String getAddress() {
    return address;
  }

  public String getIdentity() {
    return identity;
  }

  public int getMemberRevision() {
    return memberRevision;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("ZTCMember{");
    sb.append("nwid=").append(nwid);
    sb.append(", address=='").append(address).append('\'');
    sb.append(", identity='").append(identity).append('\'');
    sb.append(", revision='").append(memberRevision).append('\'');
    if (ipAssignments != null) {
      sb.append(", assignments=[");
      boolean first = true;
      for (String s : ipAssignments) {
        if (!first)
          sb.append(", ");
        else
          first = false;
        sb.append('\'').append(s).append('\'');
      }
      sb.append(memberRevision).append(']');
    }
    sb.append('}');
    return sb.toString();
  }

  @Override
  public ZTCMember clone() {
    ZTCMember m;
    try {
      m = (ZTCMember) super.clone();
      m.ipAssignments = new ArrayList<String>(ipAssignments); // may be changed, deep clone!
      return m;
    } catch (CloneNotSupportedException e) {
      System.err.println(e.getMessage());;
    }
    return null;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof ZTCMember))
      return false;
    ZTCMember m = (ZTCMember) o;
    if ((nwid != null && !nwid.equals(m.nwid)) || !(nwid == null && m.nwid == null))
      return false;
    if ((address != null && !address.equals(m.address)) || !(address == null && m.address == null))
      return false;
    if (authorized != m.authorized)
      return false;
    if (activeBridge != m.activeBridge)
      return false;
    if ((identity != null && !identity.equals(m.identity)) || !(identity == null && m.identity == null))
      return false;
    if (memberRevision != m.memberRevision)
      return false;
    if (ipAssignments.size() != m.ipAssignments.size())
      return false;
    for (int i=0; i<ipAssignments.size(); i++)
      if ((ipAssignments.get(i) != null && !ipAssignments.get(i).equals(m.ipAssignments.get(i))) 
      || !(ipAssignments.get(i) == null && m.ipAssignments.get(i) == null))
        return false;
    //clock ??

    return true;
  }
}
