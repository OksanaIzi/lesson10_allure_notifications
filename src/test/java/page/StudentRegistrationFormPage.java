package page;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;
import static data.Data.*;

public class StudentRegistrationFormPage {

    @Step("Открываем страницу {base_url}")
    public StudentRegistrationFormPage openPage() {
        open(base_url);
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        return this;
    }

    @Step("Заполняем данными форму")
    public StudentRegistrationFormPage fillForm() {
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(mobile);
        setDate(yearOfBirth, monthOfBirth, dayOfBirth, dayOfWeekOfBirth);
        $("#subjectsContainer").click();
        $("#subjectsInput").setValue(subject1).pressEnter();
        $("#hobbiesWrapper").$(byText(hobby1)).click();
        $("#uploadPicture").uploadFromClasspath(picture);
        $("#currentAddress").val(currentAddress);
        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();
        $("#submit").click();
        return this;
    }

    public void setDate(String year, String month, String day, String dayOfWeek){
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(String.format("[aria-label='Choose %s, %s %sth, %s']",
                dayOfWeek, month, day, year)).click();
    }

    @Step("Проверяем успешность заполнения данных")
    public void checkData(){
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".modal-content").shouldHave(text(firstName),
                text(lastName),
                text(email),
                text(gender),
                text(mobile),
                text(subject1),
                text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth),
                text(hobby1),
                text(picture),
                text(currentAddress),
                text(state + " " + city));
    }

    @Step("Проверяем успешность заполнения данных")
    public StudentRegistrationFormPage checkDataFail() {
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".modal-content").shouldHave(text(firstName),
                text(lastName),
                text(email),
                text(gender),
                text(mobile),
                text(subject1),
                text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth),
                text(hobby1),
                text(picture),
                text(currentAddress),
                text(state + " hjdfhd " + city));
        return this;
    }
}
