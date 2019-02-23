package sapient.coding.interview;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 *
 */
public class App {
	
	public static void main(String[] args) {
		String ipFile = "Files/ip.csv";
		Stream<Transaction> data = IOHelper.getTransactionsFromFile(ipFile, ",");
		List<Transaction> processedTxs = new FeeCalc().processTxs(data);
		IOHelper.printReport(processedTxs,",");


	}
}
