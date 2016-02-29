package com.github.edouardswiac.zerotier.api;

import java.util.Objects;

public final class ZTNet {
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
	private String[] multicastSubscriptions;
	private String[] assignedAddresses;
	private String portDeviceName;

	public ZTNet() {
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

	public String[] getMulticastSubscriptions() {
		return multicastSubscriptions;
	}

	public String[] getAssignedAddresses() {
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ZTNet ztNet = (ZTNet) o;
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
