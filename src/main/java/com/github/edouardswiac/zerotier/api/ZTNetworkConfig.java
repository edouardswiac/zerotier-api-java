package com.github.edouardswiac.zerotier.api;


import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public final class ZTNetworkConfig {
  private String nwid;
  private String name;
  private Integer authorizedMemberCount;
  @SerializedName("private")
  private Boolean isPrivate;

  public ZTNetworkConfig() {}

  public String getNetworkId() {
    return nwid;
  }

  public String getName() {
    return name;
  }

  public Integer getAuthorizedMemberCount() {
    return authorizedMemberCount;
  }

  public Boolean isPrivate() {
    return isPrivate;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setIsPrivate(Boolean isPrivate) {
    this.isPrivate = isPrivate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ZTNetworkConfig that = (ZTNetworkConfig) o;
    return Objects.equals(nwid, that.nwid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nwid);
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("ZTNetworkConfig{");
    sb.append("nwid='").append(nwid).append('\'');
    sb.append(", name='").append(name).append('\'');
    sb.append(", authorizedMemberCount=").append(authorizedMemberCount);
    sb.append(", isPrivate=").append(isPrivate);
    sb.append('}');
    return sb.toString();
  }
}
