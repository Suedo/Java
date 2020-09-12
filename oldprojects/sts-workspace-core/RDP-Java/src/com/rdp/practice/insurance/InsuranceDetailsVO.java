package com.rdp.practice.insurance;

import java.util.Date;

/* Insurance Detail Value Object - DO NOT CHANGE*/
class InsuranceDetailsVO {
	private String policyCode;
	private String panNumber;
	private Date startDate;
	private float accumulatedPrem;
	private int period;
	private float netPrem;
	private float loanAmount;
	private Date  endDate; 

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		InsuranceDetailsVO other = (InsuranceDetailsVO) obj;
		
		if (policyCode == null) {
			if (other.policyCode != null) {
				return false;
			}
		} else if (!policyCode.equals(other.policyCode)) {
			return false;
		}
		
	
		return true;
	}


	public String getPolicyCode() {
		return policyCode;
	}


	public void setPolicyCode(String policyCode) {
		this.policyCode = policyCode;
	}


	public String getPanNumber() {
		return panNumber;
	}


	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public float getAccumulatedPrem() {
		return accumulatedPrem;
	}


	public void setAccumulatedPrem(float accumulatedPrem) {
		this.accumulatedPrem = accumulatedPrem;
	}


	public int getPeriod() {
		return period;
	}


	public void setPeriod(int period) {
		this.period = period;
	}


	public float getNetPrem() {
		return netPrem;
	}


	public void setNetPrem(float netPrem) {
		this.netPrem = netPrem;
	}


	public float getLoanAmount() {
		return loanAmount;
	}


	public void setLoanAmount(float loanAmount) {
		this.loanAmount = loanAmount;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



}