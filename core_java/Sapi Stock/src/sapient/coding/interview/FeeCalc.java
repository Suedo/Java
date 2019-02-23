package sapient.coding.interview;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

public class FeeCalc {

	private TreeSet<Transaction> runningSet = new TreeSet<Transaction>();
	private ArrayList<Transaction> finalList = new ArrayList<>();

	private void calculateFeeForIntradayTransaction(Transaction tx) {
		tx.setProcessingFee(20); // 10 each for buy and sell
	}

	private void calculateFeeForNormalTransaction(Transaction tx) {

		TransactionType txType = tx.getTransactionType();
		if (tx.getPriorityFlag().equals(PriorityFlag.Y)) {
			tx.setProcessingFee(500);
		} else {
			if (txType.equals(TransactionType.SELL) || txType.equals(TransactionType.WITHDRAW)) {
				tx.setProcessingFee(10);
			} else {
				tx.setProcessingFee(50); // BUY or DEPOSIT
			}
		}

	}

	public List<Transaction> processTxs(Stream<Transaction> txList) {
		txList.forEach(e -> checkIntraday(e)); // first terminal command of stream that started with Files.get

		for (Transaction t : runningSet) { // runningSet only has normals now
			calculateFeeForNormalTransaction(t);
			finalList.add(t);
		}
		
		return finalList;


	}

	private void checkIntraday(Transaction tx) {

		// add returns false means tx already encountered. possible intraday
		if (!runningSet.add(tx)) {

			Transaction other = runningSet.floor(tx);
			TransactionType txType = tx.getTransactionType();
			
			// if true, intraday confirmed
			if ((txType.equals(TransactionType.BUY) || txType.equals(TransactionType.SELL))
					&& tx.getTransactionType().isOppositeTrade(other.getTransactionType())) {

				calculateFeeForIntradayTransaction(tx);

				tx.setCLosed(true);
				tx.setIntraday(true);
				finalList.add(tx);

				runningSet.remove(other);
			}

		}

	}
}
