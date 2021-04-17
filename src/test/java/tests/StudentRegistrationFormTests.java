package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import page.StudentRegistrationFormPage;

public class StudentRegistrationFormTests extends TestBase{
    StudentRegistrationFormPage studentRegistrationFormPage = new StudentRegistrationFormPage();

    @Test
    @Tag("smoke")
    public void fillSuccessfulRegistrationForm() {
        studentRegistrationFormPage.openPage()
                .fillForm()
                .checkData();
    }

    @Test
    @Tag("regress")
    public void fillNegativeRegistrationForm() {
        studentRegistrationFormPage.openPage()
                .fillForm()
                .checkDataFail();
    }
}
