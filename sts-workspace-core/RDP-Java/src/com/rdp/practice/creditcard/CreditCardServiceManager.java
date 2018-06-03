package com.rdp.practice.creditcard;

import jdk.nashorn.internal.objects.annotations.Constructor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CreditCardServiceManager {

	String filePath;
    HashMap<String, CreditCardDetailsVO> amexCardMap;
    HashMap<String, CreditCardDetailsVO> visaCardMap;
    HashMap<Integer, HashMap<String, CreditCardDetailsVO>> finalCreditCardMap;

    private enum CardType {
        AMEX, VISA
    }




	@Constructor
	public Map CreditCardDetails(String filePath) throws CreditCardServiceException {

		this.filePath = filePath;
	    this.amexCardMap = new HashMap<>();
		this.visaCardMap = new HashMap<>();
		this.finalCreditCardMap = new HashMap<>();



		String cardType = "";
		String cardNumber = "";
		String cardHolderName = "";
		double billAmount = 0;
		Date paymentDate = null;
		Date dueDate = null;

		double fineAmount = 0;
		char grade = '0';

		String dateOfPayment = "";
		String dateOfDue = "";


		try(BufferedReader br = new BufferedReader(new FileReader(filePath))){

		    String line;
            while ((line = br.readLine()) != null) {

                String str[] = line.split(",");

                cardNumber = str[0];
                cardHolderName = str[1];
                billAmount = Double.parseDouble(str[2]);
                dateOfDue = str[3];
                dateOfPayment = str[4];

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                // sdf.parse() returns a DATE class, but it's better to use CALENDAR
                try {
                    paymentDate = sdf.parse(dateOfPayment);
                } catch (ParseException e) {
                    throw new CreditCardServiceException("paymentDate should be in dd/MM/yyyy format only!!!!");
                }

                try {
                    dueDate = sdf.parse(dateOfDue);
                } catch (ParseException e) {
                    throw new CreditCardServiceException("dueDate should be in dd/MM/yyyy format only!!!!");
                }

            /*
             * if(!(cardNumber.startsWith("4") &&
             * cardNumber.length()==16)||(!((cardNumber.startsWith("34") ||
             * cardNumber.startsWith("37")) && cardNumber.length()==15))){ throw new
             * CreditCardServiceException("Invalid Credit card number!!!!"); }
             */

                if (cardNumber.startsWith("4") && cardNumber.length() == 16) {

                    cardType = "VISA";
                    System.out.println("Success::");

                }else if ((cardNumber.startsWith("34") || cardNumber.startsWith("37")) && cardNumber.length() == 15) {

                    cardType = "AMEX";
                    System.out.println("Success::");

                }else {
                    throw new CreditCardServiceException("Invalid Credit card number!!!!");
                }

                // int = long - long : possible data loss ?
                int noOfGapDays = (int) ((paymentDate.getTime() - dueDate.getTime()) / (1000 * 24 * 60 * 60));
                System.out.println("noOfGapDays" + noOfGapDays);

                // no fine if paid in time
                if (paymentDate.compareTo(dueDate) < 0) {

                    grade = 'A';            // good guy
                    fineAmount = 0;         // no fine
                }
                // else take a fine
                else {

                    grade = 'B';            // defaulter

                    // get fineAmount based on card type
                    if ("AMEX".equals(cardType)) {

                        if (noOfGapDays <= 5) {

                            fineAmount = (10 / 100) * billAmount;
                        }

                        else if (noOfGapDays > 5) {
                            fineAmount = (20 / 100) * billAmount;
                        }

                    }else if ("VISA".equals(cardType)) {

                        if (noOfGapDays <= 5) {

                            fineAmount = (10 / 100) * billAmount;
                        }

                        else if (noOfGapDays > 5 && billAmount <= 15000) {
                            fineAmount = (20 / 100) * billAmount;
                        }

                        else if (noOfGapDays > 5 && billAmount > 15000) {
                            fineAmount = (30 / 100) * billAmount;
                        }

                    }

                }

                CreditCardDetailsVO amexCardDetailsVO = new CreditCardDetailsVO();
                CreditCardDetailsVO visaCardDetailsVO = new CreditCardDetailsVO();

                if ("AMEX".equals(cardType)) {

                    // Ideally, refactor : BuilderPattern

                    amexCardDetailsVO.setBillAmount(billAmount);
                    amexCardDetailsVO.setCardHolderName(cardHolderName);
                    amexCardDetailsVO.setCardNumber(cardNumber);
                    amexCardDetailsVO.setCardType(cardType);
                    amexCardDetailsVO.setDueDate(dueDate);
                    amexCardDetailsVO.setFineAmount(fineAmount);
                    amexCardDetailsVO.setPaymentDate(paymentDate);
                    amexCardDetailsVO.setGrade(grade);


                    // add card to collection, if not already present

                    CreditCardDetailsVO selectedAmexCardDetailsVO = amexCardMap.get(cardNumber);

                    if (selectedAmexCardDetailsVO == null
                            || (paymentDate.compareTo(selectedAmexCardDetailsVO.getPaymentDate()) > 0)) {
                        amexCardMap.put(cardNumber, amexCardDetailsVO);
                    }

                }

                if ("VISA".equals(cardType)) {

                    visaCardDetailsVO.setBillAmount(billAmount);
                    visaCardDetailsVO.setCardHolderName(cardHolderName);
                    visaCardDetailsVO.setCardNumber(cardNumber);
                    visaCardDetailsVO.setCardType(cardType);
                    visaCardDetailsVO.setDueDate(dueDate);
                    visaCardDetailsVO.setFineAmount(fineAmount);
                    visaCardDetailsVO.setPaymentDate(paymentDate);
                    visaCardDetailsVO.setGrade(grade);

                    CreditCardDetailsVO selectedVisaCardDetailsVO = visaCardMap.get(cardNumber);

                    if (selectedVisaCardDetailsVO == null
                            || (paymentDate.compareTo(selectedVisaCardDetailsVO.getPaymentDate()) > 0)) {
                        visaCardMap.put(cardNumber, visaCardDetailsVO);
                    }

                }

            }

            // this should be outside the loop, as in each loop, the map gets overwritten with new CardMap

            finalCreditCardMap.put(1, amexCardMap);
            finalCreditCardMap.put(2, visaCardMap);

            for (String key : amexCardMap.keySet()) {

                System.out.println("------------------>");
                System.out.println("Iterating or looping through amex card map>");
                System.out.println("key" + key + "value of cardholder::" + amexCardMap.get(key).getCardHolderName()
                        + "value of payment date:" + amexCardMap.get(key).getPaymentDate());

            }

            for (String key : visaCardMap.keySet()) {

                System.out.println("------------------>");
                System.out.println("Iterating or looping through amex card map>");
                System.out.println("key" + key + "value of cardholder::" + visaCardMap.get(key).getCardHolderName()
                        + "value of payment date:" + visaCardMap.get(key).getPaymentDate());

            }

        } catch (FileNotFoundException e) {
            throw new CreditCardServiceException("File does not exists in the specified directory!!!!");
        } catch (IOException e) {
            System.err.println("Error reading file");
		    e.printStackTrace();
        }


		return finalCreditCardMap;
	}

	public static void main(String[] args) throws CreditCardServiceException {
		String datFilePath="D:\\Github\\Java\\sts-workspace-core\\RDP-Java\\Files\\CreditCardDetails.txt";
		new CreditCardServiceManager().CreditCardDetails(datFilePath);

	}

}

/*

PSEUDOCODE:

process each card
	parse date: payment date and due date (throw error if any)
	sysout('success') if no error
	calculateFine()
		if paid in time : no fine, set grade = A
		else : calculate fine based on card type, set grade = B

	If validated though above steps, add state to card instance
	add card to collection, if not already present


// once all cards are mapped:
create a final mapping : <Integer, CardMap> : where int : 1 or 2, based on AmexMap or VisaMap respectively


---------------------------------------------------------------------------------------------------------------------------------------


/* OUTPUT:

"C:\Program Files\Java\jdk1.8.0_121\bin\java" "-javaagent:C:\Program Files (x86)\JetBrains\IntelliJ IDEA Community Edition 2017.1\lib\idea_rt.jar=49543:C:\Program Files (x86)\JetBrains\IntelliJ IDEA Community Edition 2017.1\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_121\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_121\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_121\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_121\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_121\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_121\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_121\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_121\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_121\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_121\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_121\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_121\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_121\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_121\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_121\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_121\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_121\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_121\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_121\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_121\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_121\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_121\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_121\jre\lib\rt.jar;D:\Github\Java\sts-workspace-core\RDP-Java\out\production\RDP-Java" com.rdp.practice.creditcard.CreditCardServiceManager
Success::
noOfGapDays2
Success::
noOfGapDays-1
Success::
noOfGapDays20
Success::
noOfGapDays7
Success::
noOfGapDays10
Success::
noOfGapDays30
Success::
noOfGapDays2
Success::
noOfGapDays4
------------------>
Iterating or looping through amex card map>
key373344345346347value of cardholder::Aninditavalue of payment date:Wed Apr 26 00:00:00 IST 2017
------------------>
Iterating or looping through amex card map>
key343344345346347value of cardholder::Pritam Debnathvalue of payment date:Tue Oct 10 00:00:00 IST 2017
------------------>
Iterating or looping through amex card map>
key4123412541264127value of cardholder::Lipika Basuvalue of payment date:Mon Jun 27 00:00:00 IST 2016
------------------>
Iterating or looping through amex card map>
key4123412541264128value of cardholder::Deepvalue of payment date:Wed May 24 00:00:00 IST 2017

*/