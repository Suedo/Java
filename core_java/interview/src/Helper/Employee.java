package Helper;

public class Employee {

    private Integer id;
    private String first_name;
    private String last_name;
    private String email;
    private String gender;
    private String age;
    private String country;
    private String language;
    private String company;

    public Employee(Integer id, String first_name, String last_name, String email, String gender, String age, String country, String language, String company) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.country = country;
        this.language = language;
        this.company = company;
    }

    public static Employee generate(String s){

        String[] args = s.split(",");
        assert args.length == 9 : "Only 9 arguments allowed";

        int id = Integer.parseInt(args[0]);
        return new Employee(id, args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8]);

    }

}
