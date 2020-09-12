package com.rdp.practice.hospitamgmt;

import java.util.Date;

/* HospitalPatient Detail Value Object - DO NOT CHANGE*/
class HospitalPatientDetailsVO {

	private String physicianID;
	private String mrnNumber;
	private String patientName;
	private Date admissionDate;
	private Date dischargeDate;
	private double billAmount;
	private String gender;

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
		HospitalPatientDetailsVO other = (HospitalPatientDetailsVO) obj;

		if (mrnNumber == null) {
			if (other.mrnNumber != null) {
				return false;
			}
		} else if (!mrnNumber.equals(other.mrnNumber)) {
			return false;
		}
		if (physicianID == null) {
			if (other.physicianID != null) {
				return false;
			}
		} else if (!physicianID.equals(other.physicianID)) {
			return false;
		}

		if (patientName != other.patientName) {
			return false;
		}
		if (patientName != other.patientName) {
			return false;
		}

		return true;
	}

	

	

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public Date getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getPhysicianID() {
		return physicianID;
	}



	public void setPhysicianID(String physicianID) {
		this.physicianID = physicianID;
	}





	public String getMrnNumber() {
		return mrnNumber;
	}





	public void setMrnNumber(String mrnNumber) {
		this.mrnNumber = mrnNumber;
	}

}