package Helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileWorks {

    private static String folder = "E:\\codeD\\GitHub\\Java\\core_java\\interview\\Files\\";
    private static String empFile = "employees.txt";

    static {
        System.out.println("executing static block");

        try {
            List<Employee> empList = Files.lines(
                    Paths.get("E:\\codeD\\GitHub\\Java\\core_java\\interview\\Files\\employees.txt"))
                    .skip(1)    // header
                    .map(Employee::generate)
                    .collect(Collectors.toList());

            System.out.println("Read " + empList.size() + " records from employee data file");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
