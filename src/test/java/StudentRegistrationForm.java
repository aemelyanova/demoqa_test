import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class StudentRegistrationForm {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1980x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }
    @Test
    void StudentRegistrationFormTests() {

        // загружаем файл с фото
        File file = new File("src/test/resources/photovalera.jpg");

        // открываем нужный сайт и выполняем проверку по названию заголовка "Student Registration Form"
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        // убрать рекламу
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        // заполняем форму студента: Name (First Name, Last Name) и Email
        $("#firstName").setValue("Valera");
        $("#lastName").setValue("Petrov");
        $("#userEmail").setValue("valerka@gmail.com");

        // выбираем пол
        $("#genterWrapper").$(byText("Male")).click();

        // вводим номер телефона 10 цифр
        $("#userNumber").setValue("1122334455");

        // вводим дату рождения 10.01.1995
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(byText("January")).click();
        $(".react-datepicker__year-select").click();
        $(byText("1995")).click();
        $(".react-datepicker__day--010").click();

        // выбираем предмет для изучения
        $("#subjectsInput").setValue("Computer Science").click();
        $(".subjects-auto-complete__menu").click();

        // в разделе "Hobbies" выбираем категорию
        $("#hobbiesWrapper").$(byText("Music")).click();

        // загружаем изображение
        $("#uploadPicture").uploadFile(file);

        // ввести текущий адрес
        $("#currentAddress").setValue("189 The Grove Drive, Los Angeles, CA 90036");

        //выбрать штат и город
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Haryana")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Panipat")).click();

        //отправить анкету
        $("#submit").click();

        //проверка
        $(".modal-content").shouldBe(visible);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Valera" + " " + "Petrov"), text("valerka@gmail.com"),
                text("Male"), text("1122334455"), text("10 January,1995"), text("Computer Science"),
                text("Music"), text("photovalera.jpg"), text("189 The Grove Drive, Los Angeles, CA 90036"),
                text("Haryana" + " " + "Panipat"));

    }
}


