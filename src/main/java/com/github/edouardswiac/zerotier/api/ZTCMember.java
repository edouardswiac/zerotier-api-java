package com.github.edouardswiac.zerotier.api;

public class ZTCMember {
	private String		nwid;
	private long			clock;
	private String		address;
	private boolean		authorized;
	private boolean		activeBridge;
	private String		identity;
	private String[]	ipAssignents;
	private int				memberRevision;

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

	public String[] getIpAssignents() {
		return ipAssignents;
	}

	public void setIpAssignents(String[] ipAssignents) {
		this.ipAssignents = ipAssignents;
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
		if (ipAssignents != null) {
			sb.append(", assignments=[");
			boolean first = true;
			for (String s : ipAssignents) {
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
}
