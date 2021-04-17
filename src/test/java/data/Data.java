package data;

import com.github.javafaker.Faker;

public class Data {

    static Faker faker = new Faker();

    public static String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            gender = "Other",
            mobile = faker.numerify("##########"),
            monthOfBirth = "May",
            yearOfBirth = "1988",
            dayOfBirth = "10",
            dayOfWeekOfBirth = "Tuesday",
            subject1 = "Chemistry",
            subject2 = "Commerce",
            hobby1 = "Sports",
            hobby2 = "Reading",
            hobby3 = "Music",
            picture = "1.jpg",
            currentAddress = faker.address().fullAddress(),
            state = "Uttar Pradesh",
            city = "Merrut",
            base_url = "https://demoqa.com/automation-practice-form";

}
