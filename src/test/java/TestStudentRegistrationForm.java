import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestStudentRegistrationForm {

   @BeforeAll
   static void beforeAll() {

      Configuration.browserSize = "1920x1080";
      Configuration.baseUrl = "https://demoqa.com";
      Configuration.pageLoadStrategy = "eager"; //По дефолту используется normal, но обычно выбирают eager для ускорения прохождения тестов

   }

   @Test
   void testRegistration() throws InterruptedException {

      open("/automation-practice-form");
      executeJavaScript("$('#fixedban').remove()");
      executeJavaScript("$('footer').remove()");

      $("#firstName").setValue("Тест");
      $("#lastName").setValue("Тестов");
      $("#userEmail").setValue("test@test.test");
      $("#genterWrapper").$(byText("Male")).click();
      $("#userNumber").setValue("9010000001");
      $("#dateOfBirthInput").click();   //Раскрытие календаря
      $(".react-datepicker__year-select").selectOption("1995");
      $(".react-datepicker__month-select").selectOption("May");
      $(".react-datepicker__day--011").click();
      $("#subjectsInput").setValue("Co").sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
      $("#hobbiesWrapper").$(byText("Sports")).shouldNotBe(selected).click();
      $("#uploadPicture").uploadFromClasspath("Designer.png");
      $("#currentAddress").setValue("620000, Тестовая обл., г. Тестбург, ул. Тестина, д. 10");
      $("#state").click();
      $("#stateCity-wrapper").$(byText("Haryana")).click();
      $("#city").click();
      $("#stateCity-wrapper").$(byText("Panipat")).click();
      $("#submit").click();

      //Просмотр результатов в модалке
      $(".modal-dialog").should(appear);
      $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
      $(".table-responsive").shouldHave(text("Тест"),
              text("Тестов"),
              text("test@test.test"),
              text("Male"),
              text("9010000001"),
              text("11 May,1995"),
              text("Commerce"),
              text("Sports"),
              text("Designer.png"),
              text("620000, Тестовая обл., г. Тестбург, ул. Тестина, д. 10"),
              text("Haryana"),
              text("Panipat"));
   }
}
