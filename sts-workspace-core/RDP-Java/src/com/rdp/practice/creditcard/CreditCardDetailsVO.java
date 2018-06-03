package com.rdp.practice.creditcard;

import java.util.Date;

/* CrediCard Detail Value Object - DO NOT CHANGE*/
class CreditCardDetailsVO {

	private String cardType;
	private String cardNumber;
	private String cardHolderName;
	private double billAmount;
	private Date paymentDate;
	private Date dueDate;
	private double fineAmount;
	private char grade;

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

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
		CreditCardDetailsVO other = (CreditCardDetailsVO) obj;

		if (cardNumber == null) {
			if (other.cardNumber != null) {
				return false;
			}
		} else if (!cardNumber.equals(other.cardNumber)) {
			return false;
		}
		if (cardType == null) {
			if (other.cardType != null) {
				return false;
			}
		} else if (!cardType.equals(other.cardType)) {
			return false;
		}

		if (cardHolderName != other.cardHolderName) {
			return false;
		}
		if (cardHolderName != other.cardHolderName) {
			return false;
		}

		return true;
	}

	public double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(double fineAmount) {
		this.fineAmount = fineAmount;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public char getGrade() {
		return grade;
	}

	public void setGrade(char grade) {
		this.grade = grade;
	}

}