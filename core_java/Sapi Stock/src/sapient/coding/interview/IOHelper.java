package sapient.coding.interview;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IOHelper {

	public static Stream<Transaction> getTransactionsFromFile(String filePath, String delim) {

		try {
			TransactionParser parser = new TransactionParser(delim);
			Stream<String> lines = Files.lines(Paths.get(filePath));

			// skip the headers
			return lines.skip(1).map(e -> parser.getTransactionFromRecord(e));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void printReport(List<Transaction> data, String opDelim) {

		// client Id, transaction type, Transaction date, & priority flag.
		Function<Transaction, List<Object>> key = tx -> Arrays.<Object>asList(tx.getClientId(), tx.getTransactionType(),
				tx.getTransactionDate(), tx.getPriorityFlag());

		Map<List<Object>, Integer> op = data.stream()
				.collect(Collectors.groupingBy(key, Collectors.summingInt(Transaction::getProcessingFee)));

//		op.forEach((k, v) -> System.out.println(k + " : " + v));

		try (BufferedWriter writer = new BufferedWriter(new FileWriter("Files/op.txt"));) {

			String header = Arrays.asList(
					"External Transaction Id,Client Id,Security Id,Transaction Type,Transaction Date,Market Value,Priority Flag"
							.split(","))
					.stream().collect(Collectors.joining(opDelim));
			writer.write(header);
			writer.newLine();
			op.forEach((k, v) -> formatOP(k, v, opDelim, writer));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void formatOP(List<Object> key, Integer fee, String delim, BufferedWriter w) {

		String op = String.format("%s%s%s%s%s%s%s%s%d", (String) key.get(0), delim, ((TransactionType) key.get(1)),
				delim, ((LocalDate) key.get(2)), delim, (PriorityFlag) key.get(3), delim, fee);

		try {
			w.write(op);
			w.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
