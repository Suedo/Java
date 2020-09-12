package com.rdp.practice.creditcard;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by 401944 on 9/4/2017.
 */
public class CreditCardServiceMangerV2 {


    String filePath;
    HashMap<String, CreditCardDetailsVO> amexCardMap;
    HashMap<String, CreditCardDetailsVO> visaCardMap;
    HashMap<Integer, HashMap<String, CreditCardDetailsVO>> finalCreditCardMap;

    private enum CardType {
        AMEX, VISA, NONE
    }

    public CreditCardServiceMangerV2(String filePath){
        this.filePath = filePath;
        this.amexCardMap =  new HashMap<>();
        this.visaCardMap = new HashMap<>();
        this.finalCreditCardMap = new HashMap<>();
    }

    public void go() throws Exception{
        processEachCard();
        createFinalMapping();

        for (String key : finalCreditCardMap.get(1).keySet()) {

            System.out.println("------------------------------------------------------");
            System.out.println("AMEX: key: " + key + ", value of cardholder: " + amexCardMap.get(key).getCardHolderName()
                    + ", value of payment date: " + amexCardMap.get(key).getPaymentDate());

        }

        for (String key : finalCreditCardMap.get(2).keySet()) {

            System.out.println("------------------------------------------------------");
            System.out.println("VISA: key: " + key + ", value of cardholder: " + visaCardMap.get(key).getCardHolderName()
                    + ", value of payment date: " + visaCardMap.get(key).getPaymentDate());

        }
    }

    private void processEachCard() throws Exception {

        String line;
        CreditCardDetailsVO currentCard;

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            while ((line = br.readLine()) != null) {

                currentCard = buildCard(line.split(","));
                addCardToMapping(currentCard);

            }

        }catch (FileNotFoundException e) {
            throw new CreditCardServiceException("File does not exists in the specified directory!!!!");
        } catch (IOException e) {
            System.err.println("Error reading file");
            e.printStackTrace();
        }

    }

    private void addCardToMapping(CreditCardDetailsVO card) {
	
	    String cardType = card.getCardType();
	
	    assert ( cardType.equals("AMEX") || cardType.equals("VISA")) :
	            "ERROR ADDING CARD TO MAPPING! Must be either AMEX or VISA card";
	
	    // This would have been simpler with a HashSet
	    if(cardType.equals("AMEX")){
	        if(!amexCardMap.containsKey(card.getCardNumber())) {
	            amexCardMap.put(card.getCardNumber(), card);
	            System.out.println("adding AMEX card: " + card.getCardNumber());
	        }
	    }else{
	        if(!visaCardMap.containsKey(card.getCardNumber())) {
	            visaCardMap.put(card.getCardNumber(), card);
	            System.out.println("adding VISA card: " + card.getCardNumber());
	        }
	    }
	}

	//    private HashMap<Integer, HashMap<String, CreditCardDetailsVO>> createFinalMapping(){
    private void createFinalMapping(){
        this.finalCreditCardMap.put(1, amexCardMap);
        this.finalCreditCardMap.put(2, visaCardMap);
    }

    private CreditCardDetailsVO buildCard(String[] str) throws Exception{
	
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	    // create a new card for this iteration
	    CreditCardDetailsVO currentCard = new CreditCardDetailsVO();
	
	    // add values to the card
	    currentCard.setCardNumber(str[0]);
	    currentCard.setCardHolderName(str[1]);
	    currentCard.setBillAmount(Double.parseDouble(str[2]));
	    currentCard.setDueDate(parseDueDate(sdf,str[3]));
	    currentCard.setPaymentDate(parsePaymentDate(sdf,str[4]));
	
	    CardType typeOfCard = validateCard(currentCard.getCardNumber());
	    if( typeOfCard.compareTo(CardType.NONE) == 0 ) throw new CreditCardServiceException("Invalid Credit card number!!!!");
	    else currentCard.setCardType(typeOfCard.toString());
	
	    double fineAmount = getFineAmount(typeOfCard,currentCard);
	    assert (fineAmount >= 0.0) : "ERROR! negative fineAmount is not possible" ;
	    currentCard.setFineAmount(fineAmount);
	
	    if (fineAmount > 0) currentCard.setGrade('A');
	    else currentCard.setGrade('B');
	
	    // All done, return finished product
	    return currentCard;
	
	}

	private CardType validateCard(String cardNumber){

        if (cardNumber.startsWith("4") && cardNumber.length() == 16) return CardType.VISA;
        if ((cardNumber.startsWith("34") || cardNumber.startsWith("37")) && cardNumber.length() == 15) return CardType.AMEX;
        return CardType.NONE;

    }

    private double getFineAmount(CardType cardType, CreditCardDetailsVO card){

        double fineAmount = -1.0;
        double billAmount = card.getBillAmount();

        // int = long - long : possible data loss ?
        int noOfGapDays = (int) ((card.getPaymentDate().getTime() - card.getDueDate().getTime()) / (1000 * 24 * 60 * 60));
        System.out.println("Number of gap days: " + noOfGapDays);

        // no fine if paid in time
        if (card.getPaymentDate().compareTo(card.getDueDate()) < 0) return 0;

        // if not paid in time, calculate a fine amount
        if (cardType == CardType.AMEX){

            fineAmount = (noOfGapDays <= 5) ? ((10 / 100) * billAmount) : ((20 / 100) * billAmount);

        }else if (cardType == CardType.VISA){

            if (noOfGapDays <= 5) fineAmount = (10 / 100) * billAmount;
            /* noOfGapDays > 5 and : */
            else if (billAmount <= 15000) fineAmount = (20 / 100) * billAmount;
            else if (billAmount > 15000) fineAmount = (30 / 100) * billAmount;

        }

        return fineAmount;
    }

    private Date parsePaymentDate(SimpleDateFormat sdf, String date) throws Exception{
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            throw new CreditCardServiceException("paymentDate should be in dd/MM/yyyy format only!!!!");
        }
    }

    private Date parseDueDate(SimpleDateFormat sdf, String date) throws Exception {
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            throw new CreditCardServiceException("dueDate should be in dd/MM/yyyy format only!!!!");
        }
    }

    public static void main(String[] args) throws Exception {
         String datFilePath="D:\\Github\\Java\\sts-workspace-core\\RDP-Java\\Files\\CreditCardDetails.txt"; // office
        // String datFilePath = "D:\\code D\\Java\\sts-workspace-core\\RDP-Java\\Files\\CreditCardDetails.txt"; // home win10
        new CreditCardServiceMangerV2(datFilePath).go();

    }
}
