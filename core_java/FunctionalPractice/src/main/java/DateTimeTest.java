import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DateTimeTest {

    private static final DateTimeFormatter format = DateTimeFormat.forPattern("YYYY-MM-dd");
    private static final String regex = "^(Cannot parse \"\\d{4}-\\d{2}-\\d{2}\": Value )(\\d+)( for (\\w+) must be in the range (\\[\\d+,\\d+\\]))$";
    private static final Pattern pat = Pattern.compile(regex);

    public static String validator(String d) {


        try {
            if (!d.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                return "Invalid format: " + d;
            }
            format.parseDateTime(d);
            return "Validation passed : " + d;

        } catch (IllegalArgumentException jodaExcep) {  // thrown by JodaTime stuff

            Matcher matcher = pat.matcher(jodaExcep.getMessage());

            // group will not work without an initial .matches() or .find()
            if (matcher.matches()) {
                return String.format("Invalid value for %s : %2s, valid range: %s", matcher.group(4), matcher.group(2), matcher.group(5));
            } else return "this should not happen";


        } catch (DateTimeParseException e) { // thrown by java.time stuff


            String returnMsg = "Invalid format";

            if (e.getCause() != null) {

                String[] arr = e.getCause().getMessage().split("\\([\\w -/]+\\):");
                String text = arr[0].trim();
                String errorValue = arr[1].trim();

                if (text.equals("Invalid value for DayOfMonth")) {
                    returnMsg = "Invalid value for Day Of Month: " + errorValue;
                } else if (text.equals("Invalid value for MonthOfYear")) {
                    returnMsg = "Invalid value for Month Of Year: " + errorValue;
                }
            }

            //            return String.format("Invalid date: %10s, %s", d, returnMsg);
            if (e.getCause() != null) {
                return "at if: " + e.getCause().getMessage();
            } else {
                return "at else: " + e.getMessage();
            }

        }

    }

    public static void main(String[] args) throws Exception {

        String validDateFilePath = "src/Files/ValidDates.txt";
        String invalidDateFilePath = "src/Files/InvalidDates.txt";

        System.out.println("Trying valid date file ...");
        Files.lines(Paths.get(validDateFilePath)).map(DateTimeTest::validator).forEach(System.out::println);

        System.out.println("\n\nTrying Invalid date file ...");
        Files.lines(Paths.get(invalidDateFilePath)).map(DateTimeTest::validator).forEach(System.out::println);


    }

}
