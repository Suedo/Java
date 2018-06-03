package com.rdp.practice.insurance;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsuranceServiceManager {

	public static Map<Integer, HashMap<String, List<InsuranceDetailsVO>>> getInsuranceDetails(final String filePath,
			final String sanctionDate) throws InsuranceServiceException {

		// Write the code here

		BufferedReader br = null;
		FileReader fr = null;
		String scurrentLine = "";

		HashMap<String, List<InsuranceDetailsVO>> eligibleIneligiblePolicyMap = new HashMap<String, List<InsuranceDetailsVO>>();
		HashMap<String, List<InsuranceDetailsVO>> duplicateInvalidMap = new HashMap<String, List<InsuranceDetailsVO>>();

		List<InsuranceDetailsVO> listelg = new ArrayList<InsuranceDetailsVO>();
		List<InsuranceDetailsVO> listNelg = new ArrayList<InsuranceDetailsVO>();
		List<InsuranceDetailsVO> listDup = new ArrayList<InsuranceDetailsVO>();
		List<InsuranceDetailsVO> listInv = new ArrayList<InsuranceDetailsVO>();
		List<InsuranceDetailsVO> listDupAddelg = new ArrayList<InsuranceDetailsVO>();
		List<InsuranceDetailsVO> listDupAddnelg = new ArrayList<InsuranceDetailsVO>();
		HashMap<Integer, HashMap<String, List<InsuranceDetailsVO>>> finalMap = new HashMap<Integer, HashMap<String, List<InsuranceDetailsVO>>>();

		try {
			fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			String policyNo = null;
			String pan = null;
			String startDate = null;
			String accuPrem = null;
			float netPrem = 0;
			String period = null;
			String loanAmount = null;
			String status = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			int loanPeriod = 0;

			try {
				while ((scurrentLine = br.readLine()) != null) {
					boolean flagDup = false;
					boolean flagInv = false;
					InsuranceDetailsVO ivo = new InsuranceDetailsVO();
					String[] insuranceDetailsArray = scurrentLine.split(",");
					policyNo = insuranceDetailsArray[0];
					pan = insuranceDetailsArray[1];
					startDate = insuranceDetailsArray[2];
					period = insuranceDetailsArray[3];
					accuPrem = insuranceDetailsArray[4];
					loanAmount = insuranceDetailsArray[5];

					///// fetch the parts of policy code
					String[] policyNoDetails = policyNo.split("-");
					String policyType = policyNoDetails[0];
					String policyNumber = policyNoDetails[1];
					String sumAssured = policyNoDetails[2];

					double sumAssuredParsed = Double.parseDouble(sumAssured);
					int policynoParsed = Integer.parseInt(policyNumber);

					Date startDateParsed = sdf.parse(startDate);
					cal.setTime(startDateParsed);

					Date sanctionDateParsed = sdf.parse(sanctionDate);

					int periodParsed = Integer.parseInt(period);
					cal.add(Calendar.MONTH, periodParsed);
					Date endDate = cal.getTime();

					double accuPremParsed = Double.parseDouble(accuPrem);
					double loanAmountParsed = Double.parseDouble(loanAmount);
					/// validation portion starts
					if (policyNo.isEmpty() || pan.isEmpty() || startDate.isEmpty() || period.isEmpty()
							|| accuPrem.isEmpty() || loanAmount.isEmpty()) {
						throw new InsuranceServiceException("none of the fields should be empty");
					}
					if (policynoParsed == 0) {
						throw new InsuranceServiceException("the policy number should be nonzero value");
					}
					for (int i = 0; i < policyNumber.length(); i++) {

						if (!Character.isDigit(policyNumber.charAt(i))) {
							throw new InsuranceServiceException("the policy number should only contain digits");
						}
					}
					if (!(policyType.equals("FRM") || policyType.equals("NRS"))) {
						throw new InsuranceServiceException("the policy type should be either FRM or NRS");
					}
					if (accuPremParsed > sumAssuredParsed) {
						throw new InsuranceServiceException(
								"the accumulated premium amount should be less than sum assured");
					}
					if (!pan.startsWith("FR")) {
						throw new InsuranceServiceException("the pan number should always start with FR");
					}
					String[] panDetails = pan.split("-");
					String panNumber = panDetails[1];

					for (int i = 0; i < panNumber.length(); i++) {

						if (!Character.isDigit(panNumber.charAt(i))) {
							throw new InsuranceServiceException(
									"the pan number after identifier should only consists of digits");
						}

					}
					/// validation portion ends

					///// LOGIC PORTION STARTS

					// IF FRM policy start find eligibility criteria
					if (policyType.equals("FRM")) {
						if (loanAmountParsed > 0.4 * accuPremParsed) {
							status = "NELG";
						}

						if (loanAmountParsed < 0.4 * accuPremParsed) {
							status = "ELG";
						}

					}

					// IF NRS policy start find eligibility criteria
					if (policyType.equals("NRS")) {
						if (loanAmountParsed < 0.6 * accuPremParsed) {
							status = "ELG";
						} else if (loanAmountParsed > 0.6 * accuPremParsed
								&& loanAmountParsed < 0.7 * sumAssuredParsed) {
							status = "ELG";
						}

						else if (loanAmountParsed > 0.6 * accuPremParsed && loanAmountParsed > 0.7 * sumAssuredParsed) {
							status = "NELG";
						}

					}

					/// logic portion starts of netpremium calculation

					if (status.equals("NELG")) {
						netPrem = (float) accuPremParsed;
					}

					else if (status.equals("ELG")) {

						Calendar calstart = Calendar.getInstance();
						Calendar calEnd = Calendar.getInstance();
						calstart.setTime(sanctionDateParsed);
						calEnd.setTime(endDate);
						/// loan period fetch Loan period = months between end
						/// date and sanction date
						loanPeriod = (calEnd.get(Calendar.YEAR) - calstart.get(Calendar.YEAR)) * 12
								+ (calEnd.get(Calendar.MONTH) - calstart.get(Calendar.MONTH));
						double x = accuPremParsed / periodParsed;
						double interest = (loanAmountParsed * 0.6) / periodParsed;// i
																					// have
																					// forgotten
																					// the
																					// exact
																					// logic
																					// of
																					// interest
						double y = (loanAmountParsed + interest) * loanPeriod;
						netPrem = (float) (x + y);
					}

					//// setter methods in vo object
					ivo.setAccumulatedPrem((float) accuPremParsed);
					ivo.setEndDate(endDate);
					ivo.setLoanAmount((float) loanAmountParsed);
					ivo.setNetPrem(netPrem);
					ivo.setPeriod(periodParsed);
					ivo.setPolicyCode(policyNo);
					ivo.setPanNumber(pan);
					ivo.setStartDate(startDateParsed);

					//// logic of putting in innermap 1 when the vo will not be
					//// present in either duplicate or invalid map

					/// logic of invlaid policy no to set the flag as true

					if (loanAmountParsed > sumAssuredParsed) {
						flagInv = true;
					}

					/// logic of duplicate policy no to set the flag as true
					for (InsuranceDetailsVO ivodemo : listelg) {

						String policyNoDemo = ivodemo.getPolicyCode();
						String policynoFetched = policyNoDemo.substring(policyNoDemo.indexOf('-') + 1,
								policyNoDemo.lastIndexOf('-'));
						if (policyNumber.equals(policynoFetched)) {
							flagDup = true;
						}
					}

					for (InsuranceDetailsVO ivodemo : listNelg) {

						String policyNoDemo = ivodemo.getPolicyCode();
						String policynoFetched = policyNoDemo.substring(policyNoDemo.indexOf('-') + 1,
								policyNoDemo.lastIndexOf('-'));
						if (policyNumber.equals(policynoFetched)) {
							flagDup = true;
						}
					}

					if (!flagDup && !flagInv) {
						if (status.equals("ELG")) {
							listelg.add(ivo);
						}
						if (status.equals("NELG")) {
							listNelg.add(ivo);
						}
					}

					//// add into duplicate and inv lists
					if (flagDup) {
						listDup.add(ivo);
					}
					if (flagInv) {
						listInv.add(ivo);
					}
				}

				//// ectract the duplicate vos from eligible /non eligible lists
				for (InsuranceDetailsVO ivoelg : listelg) {

					for (InsuranceDetailsVO ivoDup : listDup) {
						if (ivoelg.getPolicyCode().equals(ivoDup.getPolicyCode())) {
							listDupAddelg.add(ivoelg);
						}
					}
				}
				for (InsuranceDetailsVO ivonelg : listNelg) {

					for (InsuranceDetailsVO ivoDup : listDup) {
						if (ivonelg.getPolicyCode().equals(ivoDup.getPolicyCode())) {
							listDupAddnelg.add(ivonelg);
						}
					}
				}
				// if duplicate remove from elg or nelg
				listelg.removeAll(listDupAddelg);
				listNelg.removeAll(listDupAddnelg);
				listDup.addAll(listDupAddelg);
				listDup.addAll(listDupAddnelg);
				eligibleIneligiblePolicyMap.put("ELG", listelg);
				eligibleIneligiblePolicyMap.put("NELG", listNelg);
				duplicateInvalidMap.put("DUP", listDup);
				duplicateInvalidMap.put("INV", listInv);
				finalMap.put(1, eligibleIneligiblePolicyMap);
				finalMap.put(2, duplicateInvalidMap);

				System.out.println(finalMap);

			} catch (IOException e) {
				throw new InsuranceServiceException("I/O Exception occured!!!!");
				// e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			throw new InsuranceServiceException("File doesnot exist in specified directory!!!!");
			// e.printStackTrace();
		} catch (ParseException e) {
			throw new InsuranceServiceException("Date should be parsed in given format");
			// e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				throw new InsuranceServiceException("I/O Exception occured!!!!");
			}

		}

		return finalMap;
	}

	public static void main(String args[]) throws InsuranceServiceException {

		InsuranceServiceManager insuranceServiceManager = new InsuranceServiceManager();
		String filePath = "D:\\Workspace_practice_axis2-jdk1.4-Next\\RDPExamPractice\\src\\com\\rdp\\practice\\insurance\\InsuranceRatesAndValues.dat";
		insuranceServiceManager.getInsuranceDetails(filePath, "10/02/2014");
	}

}