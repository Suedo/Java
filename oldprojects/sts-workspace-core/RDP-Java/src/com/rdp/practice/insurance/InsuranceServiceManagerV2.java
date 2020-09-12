package com.rdp.practice.insurance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsuranceServiceManagerV2 {
	
	ArrayList<InsuranceDetailsVO> eligibles;
	ArrayList<InsuranceDetailsVO> nonEligibles;
	ArrayList<InsuranceDetailsVO> duplicates;
	ArrayList<InsuranceDetailsVO> invalids;
	
	HashMap<String, List<InsuranceDetailsVO>> eligiblePolicyMap;
	HashMap<String, List<InsuranceDetailsVO>> nonEligiblePolicyMap;
	HashMap<String, List<InsuranceDetailsVO>> duplicatePolicyMap;
	HashMap<String, List<InsuranceDetailsVO>> invalidPolicyMap;
	
	HashMap<Integer, HashMap<String, List<InsuranceDetailsVO>> > finalOutputMap;
	
	String eligibleStatus;
	String policyStatus;
	String filepath;
	
	Date sanctionDate;
	
	public InsuranceServiceManagerV2(String filepath, String sanctionDate) {
		
	}
	
	public Map<Integer, HashMap<String, List<InsuranceDetailsVO>>> go(){
		
		
		
		
		return finalOutputMap;
	}
	
	/**
	 * 
	 * @return "ELG"/"NELG"
	 */
	private String computeEligibilityStatus(InsuranceDetailsVO policy){
		eligibleStatus = "ELG";	
		
		String[] p = policy.getPolicyCode().split("-");
		String policyType = p[0];
		String policyNumber = p[1];
		String sumAssured = p[2];
		
		float lAmt = policy.getLoanAmount();
		float accPrem = policy.getAccumulatedPrem();
		
		if(policyType.equals("FRM")){
			
		}else if(policyType.equals("NRS")){
			
		}
				
		
		
		return eligibleStatus;
	}
	
	/**
	 * 
	 * @return "DUP"/"INV"
	 */
	private String computePolicyStatus(InsuranceDetailsVO policy){
		String policyStatus = "DUP";
		return policyStatus;
		
	}
	
	private float computeNetPremium(String elibilityStatus, InsuranceDetailsVO policy){
		float netPremium = 0f;
		
		
		float X = (policy.getAccumulatedPrem()/policy.getPeriod());
		
		Calendar sanctionDate = new GregorianCalendar();
		Calendar endDate = new GregorianCalendar();
		
		sanctionDate.setTime(this.sanctionDate);
		endDate.setTime(policy.getEndDate());
		
		// https://stackoverflow.com/a/16559066
		int loanPeriod = ((endDate.YEAR - sanctionDate.YEAR)*12) + (endDate.MONTH - sanctionDate.MONTH);
		
		float interest = (float) ((policy.getLoanAmount()* 0.6)/policy.getPeriod());
		
		float Y = (policy.getLoanAmount() + interest)*loanPeriod;
		
		if(eligibleStatus.equals("NELG")) netPremium = policy.getAccumulatedPrem();
		else netPremium = X + Y;		
		
		return netPremium;
	}
	
	
	
	
	
	public static Map<Integer, HashMap<String, List<InsuranceDetailsVO>>> getInsuranceDetails(final String filePath,
			final String sanctionDate) throws InsuranceServiceException {
		
		InsuranceServiceManagerV2 i = new InsuranceServiceManagerV2(filePath, sanctionDate);
		return i.go();
		
	}
	
	
	
	public static void main(String[] args) {
		
	}

}
