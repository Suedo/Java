package com.rdp.practice.hospitamgmt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HospitalServiceManager {

	public static Map<Integer, Map> fetchDetailsOfHospital(String filePath) throws HospitalServiceException{
		
		
		Map<Integer, Map> finalHospitalDetailsMap=new HashMap<Integer, Map>();
		
		
		HashMap<String, List<HospitalPatientDetailsVO>> admissionDateMap=new HashMap<String, List<HospitalPatientDetailsVO>>();
		HashMap<String, Integer> patientPhysicianMap=new HashMap<String, Integer>();
		

		int entCount = 0;
		int neuCount = 0;
		int genCount = 0;
		
		
		String physicianCategory="";
		String mrnNumber="";
	    String patientName="";
		Date admissionDate=null;
		Date dischargeDate=null;
		double billAmount=0;
		double feesAmount=0;
		String gender="";

		String dateOfAdmission="";
		String dateOfDischarge="";
		String category="";
		String physicianNumber = "";
		
		String sCurrentLine="";
		
		File file=new File(filePath);
		
		//if(file.exists()){
		BufferedReader br=null;
		FileReader fr=null;
		
		HospitalServiceManager hospitalServiceManager=new HospitalServiceManager();
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		try {
			fr=new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new HospitalServiceException("File doesnot exists in the system!!!");
		}
		
		
		br=new BufferedReader(fr);
		
		try {
			while((sCurrentLine=br.readLine())!=null){
				
			String values[] =sCurrentLine.split("\\|");	

			if(values.length != 6){
				throw new HospitalServiceException("All fields are mandatory.");
			}
				
				patientName=values[0];
				mrnNumber=values[1];
				gender=values[2];
				physicianCategory=values[3];
				dateOfAdmission=values[4];
				dateOfDischarge=values[5];
				
				if (values[0].trim().length() != 0
						&& values[1].trim().length() != 0
						&& values[2].trim().length() != 0
						&& values[3].trim().length() != 0
						&& values[4].trim().length() != 0
						&& values[5].trim().length() != 0) {
					throw new HospitalServiceException(
							"All fields are mandatory.");
				}
				
				for(int i=0; i<patientName.length(); i++){
					if(!(Character.isLetter(patientName.charAt(i)) || Character.isWhitespace(patientName.charAt(i)))){
						throw new HospitalServiceException("Patient Name should contain only alphabet and white space.");
					}
				}
				
				
				try {
					admissionDate=sdf.parse(dateOfAdmission);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					throw new HospitalServiceException("Admission date should be in dd/MM/yyyy format only!!!");
				}
				
				try {
					dischargeDate=sdf.parse(dateOfDischarge);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					throw new HospitalServiceException("Discharge date should be in dd/MM/yyyy format only!!!");
				}
				
				if(admissionDate.after(dischargeDate)){
					throw new HospitalServiceException("Admission date should not be greater than Discharge date.");
				}
				if(!(mrnNumber.startsWith("IN") || mrnNumber.startsWith("OUT"))){
					throw new HospitalServiceException("MRN should start with IN or OUT.");
				}
				if(mrnNumber.startsWith("IN")){
					for(int i = 2; i < mrnNumber.length(); i++){
						if(!Character.isDigit(mrnNumber.charAt(i))){
							throw new HospitalServiceException("MRN should followed by digits only.");
						}
					}
				}
				if(mrnNumber.startsWith("OUT")){
					for(int i = 3; i < mrnNumber.length(); i++){
						if(!Character.isDigit(mrnNumber.charAt(i))){
							throw new HospitalServiceException("MRN should followed by digits only.");
						}
					}
				}
				
				String physicianValues[]=physicianCategory.split("-");
				if(physicianValues.length != 2){
					throw new HospitalServiceException("Physician Id should contain -.");
				}
				physicianNumber = physicianValues[0];
				category=physicianValues[1];
				
				
				for(int j = 0; j < physicianNumber.length(); j++){
					if(!Character.isDigit(physicianNumber.charAt(j))){
						throw new HospitalServiceException("Physician Id should be a combination of 4 digit number.");
					}
				}
				
				if(physicianNumber.length() != 4){
					throw new HospitalServiceException("Physician Id should be a combination of 4 digit number.");
				}
				if(!(category.equals("GEN") || category.equals("NEU") || category.equals("ENT"))){
					throw new HospitalServiceException("4 digit Physician number should followed by GEN/ENT/NEU.");
				}
				

				int noOfDays=(int) ((dischargeDate.getTime()-admissionDate.getTime())/(1000*60*60*24));
				if(category.equals("GEN")){
					billAmount = noOfDays * 1250;
				}
				if(category.equals("ENT")){
					billAmount = noOfDays * 1500;
				}
				if(category.equals("NEU")){
					billAmount = noOfDays * 1750;
				}
				
				//put values for 1st map
				HospitalPatientDetailsVO hospitalPatientDetailsVO=new HospitalPatientDetailsVO();
				
				hospitalPatientDetailsVO.setPatientName(patientName);
				hospitalPatientDetailsVO.setMrnNumber(mrnNumber);
				hospitalPatientDetailsVO.setGender(gender);
				hospitalPatientDetailsVO.setPhysicianID(physicianCategory);
				hospitalPatientDetailsVO.setAdmissionDate(admissionDate);
				hospitalPatientDetailsVO.setDischargeDate(dischargeDate);
				hospitalPatientDetailsVO.setBillAmount(billAmount);				
								
				
				List<HospitalPatientDetailsVO> existingPatientList = admissionDateMap.get(dateOfAdmission);
				if(existingPatientList == null){
					List<HospitalPatientDetailsVO> patientList = new ArrayList<HospitalPatientDetailsVO>();
					patientList.add(hospitalPatientDetailsVO);
					admissionDateMap.put(dateOfAdmission, patientList);
					if(category.equals("GEN")){
						genCount += 1;
					}
					if(category.equals("NEU")){
						neuCount += 1;
					}
					if(category.equals("ENT")){
						entCount += 1;
					}
				}else{
					boolean dupFlag = false;
					for(HospitalPatientDetailsVO vo : existingPatientList){
						if(vo.getMrnNumber().equals(mrnNumber)){
							dupFlag = true;
						}
					}
					if(!dupFlag){
						existingPatientList.add(hospitalPatientDetailsVO);
						admissionDateMap.put(dateOfAdmission, existingPatientList);
						if(category.equals("GEN")){
							genCount += 1;
						}
						if(category.equals("NEU")){
							neuCount += 1;
						}
						if(category.equals("ENT")){
							entCount += 1;
						}
					}
				}

				patientPhysicianMap.put("GEN", genCount);
				patientPhysicianMap.put("NEU", neuCount);
				patientPhysicianMap.put("ENT", entCount);				
				

			}
			
			
			System.out.println("size of entlist::"+entCount);
			System.out.println("size of NeuList::"+neuCount);
			System.out.println("size of GenList::"+genCount);
			
			
			for(String key:admissionDateMap.keySet()){
				System.out.println("-------------");
				System.out.println("looping through admission date map");
				System.out.println("key::"+key+"value of patientname::"+admissionDateMap.get(key).size());
				
			}
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new HospitalServiceException("i/O exception has occured!!!");
			
			
		}

		finalHospitalDetailsMap.put(1, admissionDateMap);
		finalHospitalDetailsMap.put(2, patientPhysicianMap);
	

		return finalHospitalDetailsMap;
	}	
	
	
	

	
	private float calculateFeesAmount(Date date1, Date date2,String category){
		float billamount=0.00f;
		
		// Write your code here
		int gapOfDays=(int) ((date2.getTime()-date1.getTime())/(1000*60*60*24));
		billamount=gapOfDays*1750;
		return billamount;
		
	}
	
	
	
	
	public static void main(String[] args) throws HospitalServiceException {
		HospitalServiceManager hospitalServiceManager=new HospitalServiceManager();
		String filePath="Files/HospitalPatientDetails.txt";
		hospitalServiceManager.fetchDetailsOfHospital(filePath);
		

	}

}
