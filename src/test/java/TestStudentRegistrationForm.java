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

      open("/automation-practice-form");  //Открыть браузер на странице
      executeJavaScript("$('#fixedban').remove()");   //Скрыть рекламный баннер
      executeJavaScript("$('footer').remove()");   //Скрыть футер, закрывающий кнопку

      $("#firstName").setValue("Тест");   //Заполнение имени
      $("#lastName").setValue("Тестов");   //Заполнение фамилии
      $("#userEmail").setValue("test@test.test");   //Заполнение имейла
      $("#genterWrapper").$(byText("Male")).click();   //Выбор пола
      $("#userNumber").setValue("9010000001");   //Заполнение телефона
      $("#dateOfBirthInput").click();   //Раскрытие календаря
      $(".react-datepicker__year-select").selectOption("1995");   //Выбор года
      $(".react-datepicker__month-select").selectOption("May");   //Выбор месяца
      $(".react-datepicker__day--011").click();   //Выбор дня
      $("#subjectsInput").setValue("Co").sendKeys(Keys.ARROW_DOWN, Keys.ENTER);  //Ввод первых букв, раскрытие вариантов и выбор одного из них
      $("#hobbiesWrapper").$(byText("Sports")).shouldNotBe(selected).click();   //Проверка, что чек-бокс Sports не активирован, и его активация
      $("#uploadPicture").uploadFromClasspath("Designer.png"); //Загрузка картинки
      $("#currentAddress").setValue("620043, Свердловская обл., г. Екатеринбург, ул. Репина, д. 105");   //Заполнение адреса
      $("#state").click(); //Раскрытие дропдауна со штатами
      $("#state input").setValue("Haryana").pressEnter(); //Выбор указанного штата в дропдауне
      $("#city").click(); //Раскрытие дропдауна с городами
      $("#city input").setValue("Panipat").pressEnter(); //Выбор указанного города в дропдауне
      $("#submit").click();   //Подтверждение данных

      //Просмотр результатов в модалке
      $(".modal-content").shouldBe(visible);
      $(".table").shouldHave(text("Тест"));
      $(".table").shouldHave(text("Тестов"));
      $(".table").shouldHave(text("test@test.test"));
      $(".table").shouldHave(text("Male"));
      $(".table").shouldHave(text("9010000001"));
      $(".table").shouldHave(text("11 May,1995"));
      $(".table").shouldHave(text("Commerce"));
      $(".table").shouldHave(text("Sports"));
      $(".table").shouldHave(text("Designer.png"));
      $(".table").shouldHave(text("620043, Свердловская обл., г. Екатеринбург, ул. Репина, д. 105"));
      $(".table").shouldHave(text("Haryana"));
      $(".table").shouldHave(text("Panipat"));
   }
}
