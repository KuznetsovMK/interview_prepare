package lesson_1.task_1;

public class Main {
    public static void main(String[] args) {
        var person =
                new Person.Builder()
                        .addFirstName("FirstName")
                        .addLastName("LastName")
                        .addMiddleName("MiddleName")
                        .addCountry("Russia")
                        .addAddress("Moscow")
                        .addPhone("+7 (999) 999-99-99")
                        .addAge(5)
                        .addGender("M")
                        .build();

        System.out.println(person);
    }
}
