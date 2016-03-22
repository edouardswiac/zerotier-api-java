package com.github.edouardswiac.zerotier.api;

public final class ZTCRule {
  int ruleNo;
  String nodeId;
  String sourcePort;
  String destPort;
  int vlanId;
  int vlanPcp;
  int etherType;
  String macSource;
  String macDest;
  String ipSource;
  String ipDest;
  int ipTos;
  int ipProtocol;
  int ipSourcePort;
  int ipDestPort;
  String action;

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
