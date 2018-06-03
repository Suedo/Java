package com.rdp.practice.hospitamgmt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 401944 on 9/5/2017.
 */
public class HospitalServiceManagerV2 {

//    private String ValidMRNRegex = "^(IN|OUT)(\\d+)$";
    private String PatientNameRegex = "^[\\w+ ]{1,}";
    private String PhysicianIDRegex = "^(\\d{4})-(GEN|NEU|ENT)$";

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


    private String validName(String entry) throws HospitalServiceException {

        if(!entry.matches(PatientNameRegex)){
            throw new HospitalServiceException("Patient Name should contain only alphabet and white space.");
        }
        return entry;
    }

    private String validMRN(String entry) throws HospitalServiceException {

        if(!(entry.startsWith("IN") || entry.startsWith("OUT"))){
            throw new HospitalServiceException("MRN should start with IN or OUT.");
        }

        String lastThree = entry.substring(entry.length() - 3);
        try {
            Integer.parseInt(lastThree);
        }catch (NumberFormatException e){
            throw new HospitalServiceException("MRN should followed by digits only.");
        }

        return entry;


    }

    private String validPhysianID(String entry) throws HospitalServiceException {

        if(!entry.contains("-")) throw new HospitalServiceException("Physician Id should contain -.");

        if(!entry.matches(this.PhysicianIDRegex)){
            throw new HospitalServiceException("Physician Id should be a combination of 4 digit number.");
        }

        return entry;

//        throw new HospitalServiceException("4 digit Physician number should followed by GEN/ENT/NEU.");

    }

    private Date validAdmissionDate(String entry) throws HospitalServiceException {

        try {
            return this.sdf.parse(entry);
        } catch (ParseException e) {
            throw new HospitalServiceException("Admission date should be in dd/MM/yyyy format only!!!");
        }


    }

    private Date validDischargeDate(String entry) throws HospitalServiceException {

        try {
            return this.sdf.parse(entry);
        } catch (ParseException e) {
            throw new HospitalServiceException("Discharge date should be in dd/MM/yyyy format only!!!");
        }
    }


    private String filePath;

    private HashSet<String> patientIDSet;
    private HashMap<String, Integer> patientCategoryCountMap;
    private HashMap<Date,ArrayList<HospitalPatientDetailsVO>> admissionDatePatientListMap;
    private HashMap<Integer, Map> finaloutputmap;

    private ArrayList<HospitalPatientDetailsVO> patientList;

    public HospitalServiceManagerV2(String filePath){
        this.filePath = filePath;
        this.patientIDSet = new HashSet<>();
        this.admissionDatePatientListMap = new HashMap<>();
        this.patientCategoryCountMap = new HashMap<>();
        this.finaloutputmap = new HashMap<>();
        this.patientList = new ArrayList<>();
    }

    public void go() throws HospitalServiceException{
        processEachPatient();
        buildAdmissionDateMapping();
        buildPatientCategoryCountMapping();
        createFinalMapping();
    }

    private void processEachPatient() throws HospitalServiceException{

        String line;
        String[] parsed;
        HospitalPatientDetailsVO currentPatient;

        try(BufferedReader r = new BufferedReader(new FileReader(this.filePath))){

            while ((line = r.readLine())!=null){

                parsed = line.split("\\|");


                // Validate if all fields are present
                if(parsed.length != 6) {
                    throw new HospitalServiceException("All fields are mandatory.");
                }
                for(String s : parsed){
                    if(s.trim().length() == 0){
                        throw new HospitalServiceException("All fields are mandatory.");
                    }
                }

                // patient for this iteration
                currentPatient = new HospitalPatientDetailsVO();

                // populate fields
                currentPatient.setPatientName(validName(parsed[0]));
                currentPatient.setMrnNumber(validMRN(parsed[1]));
                currentPatient.setGender(parsed[2]);
                currentPatient.setPhysicianID(validPhysianID(parsed[3]));
                currentPatient.setAdmissionDate(validAdmissionDate(parsed[4]));
                currentPatient.setDischargeDate(validDischargeDate(parsed[5]));


                // this has caveats: ideally, should be using CALENDAR
                int numOfDays = (int)((currentPatient.getDischargeDate().getTime() - currentPatient.getAdmissionDate().getTime())/(1000*60*60*24));
                if(numOfDays < 0){
                    throw new HospitalServiceException("Discharge Date should be greater than Admission Date");
                }
                double billAmount = getBillingAmount(numOfDays,currentPatient.getPhysicianID().split("-")[1]);

                currentPatient.setBillAmount(billAmount);


                // patient id should be unique
                if(!(this.patientIDSet.add(currentPatient.getPhysicianID()))){
                    throw new HospitalServiceException("Physician ID should be unique :: " + currentPatient.getPhysicianID());
                }

                this.patientList.add(currentPatient);

            }

        }catch (FileNotFoundException e){
            throw new HospitalServiceException("File does not exists in the system!!!");
        }catch (IOException e){
            System.err.println("IOException while reading data from file");
            e.printStackTrace();
        }

    }

    private void buildAdmissionDateMapping() {

        for(HospitalPatientDetailsVO patient : patientList){
            Date admissionDate = patient.getAdmissionDate();

            admissionDatePatientListMap.putIfAbsent(admissionDate,new ArrayList<>());
            admissionDatePatientListMap.get(admissionDate).add(patient);
        }

        System.out.println("Admission date map built");

        for(Date key:admissionDatePatientListMap.keySet()){
//            System.out.println("-------------");
//            System.out.println("looping through admission date map");
            System.out.println(key.toString()+" : value of patientname: " + admissionDatePatientListMap.get(key).size());

        }
    }

    private void buildPatientCategoryCountMapping(){

        int GEN = 0, NEU = 0, ENT = 0;

        for(HospitalPatientDetailsVO p : patientList){
            if(p.getPhysicianID().split("-")[1].equals("GEN")) GEN++;
            else if(p.getPhysicianID().split("-")[1].equals("NEU")) NEU++;
            else if(p.getPhysicianID().split("-")[1].equals("ENT")) ENT++;
        }

        patientCategoryCountMap.put("GEN", GEN);
        patientCategoryCountMap.put("NEU", NEU);
        patientCategoryCountMap.put("ENT", ENT);

        System.out.println("GEN, NEU, ENT: " + GEN + "," + NEU + "," + ENT);
    }

    private void createFinalMapping() {

        this.finaloutputmap.put(1, admissionDatePatientListMap);
        this.finaloutputmap.put(2,patientCategoryCountMap);

    }

    private double getBillingAmount(int numdays, String category){
        if(category.equals("GEN")) return numdays*1250;
        else if(category.equals("ENT")) return numdays*1500;
        else /*(category.equals("NEU"))*/ return numdays*1750;
    }


    public static void main(String[] args) throws HospitalServiceException {
        new HospitalServiceManagerV2("Files/HospitalPatientDetails.txt").go();
    }

}

/* OUTPUT:

Admission date map built
Sat Sep 16 00:00:00 IST 2017 : value of patientname: 1
Mon Feb 20 00:00:00 IST 2012 : value of patientname: 3
Sun Sep 16 00:00:00 IST 2012 : value of patientname: 2
Sat Feb 20 00:00:00 IST 2016 : value of patientname: 1
Sun Oct 16 00:00:00 IST 2016 : value of patientname: 1
GEN, NEU, ENT: 3,2,3

*/