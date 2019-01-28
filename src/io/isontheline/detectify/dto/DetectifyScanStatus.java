package io.isontheline.detectify.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DetectifyScanStatus {
	private String scanProfileToken;
	private Timestamp created;
	private String phase;
	private String state;
	
	@JsonProperty("scan_profile_token")
	public String getScanProfileToken() {
		return scanProfileToken;
	}

	@JsonProperty("scan_profile_token")
	public void setScanProfileToken(String scanProfileToken) {
		this.scanProfileToken = scanProfileToken;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
