import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;

public class TestStudentRegistrationForm {

   @Test
   void testRegistration() throws InterruptedException {

      Configuration.pageLoadStrategy = "eager"; //По дефолту используется normal, но обычно выбирают eager для ускорения прохождения тестов
      open("https://demoqa.com/automation-practice-form");  //Открыть браузер на странице
      executeJavaScript("$('#fixedban').remove()");   //Скрыть рекламный баннер
      executeJavaScript("$('footer').remove()");   //Скрыть футер, закрывающий кнопку



   }
}
