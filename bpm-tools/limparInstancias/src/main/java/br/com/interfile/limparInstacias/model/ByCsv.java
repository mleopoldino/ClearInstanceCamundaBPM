package br.com.interfile.limparInstacias.model;

import java.io.Serializable;

public class ByCsv implements Serializable {

	/**
	 * 
	 */
	public static final long serialVersionUID = -3000041467974434979L;
	
	public String procId;

	public ByCsv(String procId) {
		super();
		this.procId = procId;
	}	

	public String getProcId() {
		return procId;
	}

	public void setProcId(String procId) {
		this.procId = procId;
	}

	@Override
    public String toString() {
        return "Csv{procId='" + procId + "\'}";
    }
}
