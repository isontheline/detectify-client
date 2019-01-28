package io.isontheline.detectify.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DetectifyReport {
	private String token;
	private String scanProfileToken;
	private String scanProfileName;
	private Timestamp created;
	private Timestamp started;
	private Timestamp stopped;
	private String url;
	private Double cvss;
	private Integer highLevelFindings;
	private Integer mediumLevelFindings;
	private Integer lowLevelFindings;
	private Integer informationFindings;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@JsonProperty("scan_profile_token")
	public String getScanProfileToken() {
		return scanProfileToken;
	}

	@JsonProperty("scan_profile_token")
	public void setScanProfileToken(String scanProfileToken) {
		this.scanProfileToken = scanProfileToken;
	}

	@JsonProperty("scan_profile_name")
	public String getScanProfileName() {
		return scanProfileName;
	}

	@JsonProperty("scan_profile_name")
	public void setScanProfileName(String scanProfileName) {
		this.scanProfileName = scanProfileName;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getStarted() {
		return started;
	}

	public void setStarted(Timestamp started) {
		this.started = started;
	}

	public Timestamp getStopped() {
		return stopped;
	}

	public void setStopped(Timestamp stopped) {
		this.stopped = stopped;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Double getCvss() {
		return cvss;
	}

	public void setCvss(Double cvss) {
		this.cvss = cvss;
	}

	@JsonProperty("high_level_findings")
	public Integer getHighLevelFindings() {
		return highLevelFindings;
	}

	@JsonProperty("high_level_findings")
	public void setHighLevelFindings(Integer highLevelFindings) {
		this.highLevelFindings = highLevelFindings;
	}

	@JsonProperty("medium_level_findings")
	public Integer getMediumLevelFindings() {
		return mediumLevelFindings;
	}

	@JsonProperty("medium_level_findings")
	public void setMediumLevelFindings(Integer mediumLevelFindings) {
		this.mediumLevelFindings = mediumLevelFindings;
	}

	@JsonProperty("low_level_findings")
	public Integer getLowLevelFindings() {
		return lowLevelFindings;
	}

	@JsonProperty("low_level_findings")
	public void setLowLevelFindings(Integer lowLevelFindings) {
		this.lowLevelFindings = lowLevelFindings;
	}

	@JsonProperty("information_findings")
	public Integer getInformationFindings() {
		return informationFindings;
	}

	@JsonProperty("infomration_findings")
	public void setInformationFindings(Integer informationFindings) {
		this.informationFindings = informationFindings;
	}
}
