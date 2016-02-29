package com.github.edouardswiac.zerotier.api;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public final class ZTCNetwork {
	private String																nwid;
	protected String															name;
	@SerializedName("private") protected boolean	privat;
	protected boolean															enableBroadcast;
	protected boolean															allowPassiveBridging;
	protected String															v4AssignMode;
	protected String															v6AssignMode;
	protected int																	multicastLimit;
	private long																	creationTime;
	private int																		revision;
	private int																		memberRevisionCounter;
	private long																	clock;
	private int																		authorizedMemberCount;
	protected List<ZTCRelay>											relays;
	protected List<String>												ipLocalRoutes;
	protected List<ZTCAssignmet>									ipAssignmentPools;
	protected List<ZTCRule>												rules;

	private String																controllerInstanceId;
	private List<String>													gateways;

	// this constructor is needed to request a new network creation
	public ZTCNetwork(String nwid) {
		if (!nwid.matches("\\p{XDigit}{10}(______|\\p{XDigit}{6})"))
			throw new IllegalArgumentException("Network id must be a 16 digit hexadecimal number");
		this.nwid = nwid;
	}
	
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

	public List<ZTCAssignmet> getIpAssignmentPools() {
		return ipAssignmentPools;
	}

	public void setIpAssignmentPools(List<ZTCAssignmet> ipAssignmentPools) {
		this.ipAssignmentPools = ipAssignmentPools;
	}

	public List<ZTCRule> getRules() {
		return rules;
	}

	public void setRules(List<ZTCRule> rules) {
		this.rules = rules;
	}

}
