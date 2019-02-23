package sapient.coding.interview;

import java.time.LocalDate;

public class Transaction implements Comparable<Transaction>{

	// input fields
	private String clientId;
	private String securityId;
	private TransactionType transactionType;
	private LocalDate transactionDate;
	private String marketValue;
	private PriorityFlag priorityFlag;
	
	// calculated fields
	private boolean isCLosed;
	private boolean isIntraday;
	private Integer processingFee;
	
	// client Id, transaction type, Transaction date, & priority flag.
	public static class recordKey{
		
		private String clientId;
		private TransactionType transactionType;
		private LocalDate transactionDate;
		private PriorityFlag priorityFlag;
		
		public recordKey(String clientId, TransactionType transactionType, LocalDate transactionDate, PriorityFlag priorityFlag) {
			this.clientId = clientId;
			this.transactionType = transactionType;
			this.transactionDate = transactionDate;
			this.priorityFlag = priorityFlag;
		}
		
		public recordKey getKey() {
			return new recordKey(clientId, transactionType, transactionDate, priorityFlag);
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((clientId == null) ? 0 : clientId.hashCode());
			result = prime * result + ((priorityFlag == null) ? 0 : priorityFlag.hashCode());
			result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
			result = prime * result + ((transactionType == null) ? 0 : transactionType.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			recordKey other = (recordKey) obj;
			if (clientId == null) {
				if (other.clientId != null)
					return false;
			} else if (!clientId.equals(other.clientId))
				return false;
			if (priorityFlag != other.priorityFlag)
				return false;
			if (transactionDate == null) {
				if (other.transactionDate != null)
					return false;
			} else if (!transactionDate.equals(other.transactionDate))
				return false;
			if (transactionType != other.transactionType)
				return false;
			return true;
		}

		
		
		
	}
	
	
	public Transaction() {
		this.isCLosed = false;
		this.isIntraday = false;
	}
	
	public String getExternalTransactionId() {
		return externalTransactionId;
	}

	public void setExternalTransactionId(String externalTransactionId) {
		this.externalTransactionId = externalTransactionId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getSecurityId() {
		return securityId;
	}

	public void setSecurityId(String securityId) {
		this.securityId = securityId;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(String marketValue) {
		this.marketValue = marketValue;
	}

	public PriorityFlag getPriorityFlag() {
		return priorityFlag;
	}

	public void setPriorityFlag(PriorityFlag priorityFlag) {
		this.priorityFlag = priorityFlag;
	}
	
	
	public boolean isCLosed() {
		return isCLosed;
	}

	public void setCLosed(boolean isOpen) {
		this.isCLosed = isOpen;
	}

	public boolean isIntraday() {
		return isIntraday;
	}

	public void setIntraday(boolean isIntraday) {
		this.isIntraday = isIntraday;
	}

	public Integer getProcessingFee() {
		return processingFee;
	}

	public void setProcessingFee(Integer processingFee) {
		this.processingFee = processingFee;
	}

//	eclipse generated equals and hashcode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clientId == null) ? 0 : clientId.hashCode());
		result = prime * result + ((securityId == null) ? 0 : securityId.hashCode());
		result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (clientId == null) {
			if (other.clientId != null)
				return false;
		} else if (!clientId.equals(other.clientId))
			return false;
		if (securityId == null) {
			if (other.securityId != null)
				return false;
		} else if (!securityId.equals(other.securityId))
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		return true;
	}
	
	private String externalTransactionId;
	@Override
	public String toString() {
		return "Transaction [externalTransactionId=" + externalTransactionId + ", clientId=" + clientId
				+ ", securityId=" + securityId + ", transactionType=" + transactionType + ", transactionDate="
				+ transactionDate + ", marketValue=" + marketValue + ", priorityFlag=" + priorityFlag + ", isCLosed="
				+ isCLosed + ", isIntraday=" + isIntraday + ", processingFee=" + processingFee + "]";
	}

	public int compareTo(Transaction other) {
		return this.hashCode() - other.hashCode();
	}

}
