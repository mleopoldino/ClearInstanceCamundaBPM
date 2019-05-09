package br.com.interfile.limparInstacias.model;

import java.io.Serializable;

public class ByActivity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1641524537248171085L;
	
	private String url;
	private String tenantId;
	private String activityId;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	
}
