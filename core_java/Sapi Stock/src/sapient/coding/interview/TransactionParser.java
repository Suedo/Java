package sapient.coding.interview;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class TransactionParser {

	private String delim = ","; // CSV by default

	public TransactionParser(String delim) {
		this.delim = delim;
	}

	public Transaction getTransactionFromRecord(String dataRecord) {
		Transaction transaction = new Transaction();

		String[] arr = dataRecord.split(this.delim);

		assert arr.length == 7 : "ERROR: Data record does not have 7 fields"; // dev only

//		System.out.println(">> " + Arrays.asList(arr));

		transaction.setExternalTransactionId(arr[0]);
		transaction.setClientId(arr[1]);
		transaction.setSecurityId(arr[2]);
		transaction.setTransactionType(getType(arr[3]));
		transaction.setTransactionDate(getDate(arr[4]));
		transaction.setMarketValue(arr[5]);
		transaction.setPriorityFlag(getPriority(arr[6]));

		return transaction;
	}

	private TransactionType getType(String txType) {
		if (TransactionType.BUY.toString().equalsIgnoreCase(txType))
			return TransactionType.BUY;
		if (TransactionType.SELL.toString().equalsIgnoreCase(txType))
			return TransactionType.SELL;
		if (TransactionType.WITHDRAW.toString().equalsIgnoreCase(txType))
			return TransactionType.WITHDRAW;
		return TransactionType.DEPOSIT;
	}

	private LocalDate getDate(String date) {
//		System.out.println("trying to parse" + date);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(date, formatter);
	}

	private PriorityFlag getPriority(String flag) {
		if (PriorityFlag.Y.toString().equalsIgnoreCase(flag))
			return PriorityFlag.Y;
		return PriorityFlag.N;

	}
}
