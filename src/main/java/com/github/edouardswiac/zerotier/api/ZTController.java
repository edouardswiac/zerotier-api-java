package com.github.edouardswiac.zerotier.api;


public final class ZTController {
	private Boolean controller;
	private int     apiVersion;
	private long    clock;
	private String  instanceId;

	public ZTController() {
  }

  public Boolean getController() {
		return controller;
	}

	public int getApiVersion() {
		return apiVersion;
	}

	public long getClock() {
		return clock;
	}

  public String getInstanceId() {
		return instanceId;
	}

	@Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("ZTController{");
    sb.append("controller=").append(controller);
    sb.append(", apiVersion='").append(apiVersion).append('\'');
    sb.append(", instanceId='").append(instanceId).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
