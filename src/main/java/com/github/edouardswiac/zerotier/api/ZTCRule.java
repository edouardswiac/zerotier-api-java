package com.github.edouardswiac.zerotier.api;

public final class ZTCRule {
  private int ruleNo;
  private String nodeId;
  private String sourcePort;
  private String destPort;
  private int vlanId;
  private int vlanPcp;
  private int etherType;
  private String macSource;
  private String macDest;
  private String ipSource;
  private String ipDest;
  private int ipTos;
  private int ipProtocol;
  private int ipSourcePort;
  private int ipDestPort;
  private String action;

  public int getRuleNo() {
    return ruleNo;
  }
  public String getNodeId() {
    return nodeId;
  }
  public String getSourcePort() {
    return sourcePort;
  }
  public String getDestPort() {
    return destPort;
  }
  public int getVlanId() {
    return vlanId;
  }
  public int getVlanPcp() {
    return vlanPcp;
  }
  public int getEtherType() {
    return etherType;
  }
  public String getMacSource() {
    return macSource;
  }
  public String getMacDest() {
    return macDest;
  }
  public String getIpSource() {
    return ipSource;
  }
  public String getIpDest() {
    return ipDest;
  }
  public int getIpTos() {
    return ipTos;
  }
  public int getIpProtocol() {
    return ipProtocol;
  }
  public int getIpSourcePort() {
    return ipSourcePort;
  }
  public int getIpDestPort() {
    return ipDestPort;
  }
  public String getAction() {
    return action;
  }

}
