package joseph.webservice;

import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

/*
In order to get this to run, just go in command line to the project folder
and run "mvnw spring-boot:run". That will make it run.
Then you can go to "http://localhost:8080/HelloWorld".
Another option is go to the project folder and run "mvnw clean package". Then if you go to the project
folder, go to the target folder, there will be something called
CapstoneRestService-0.1.0.jar   You can run that, and it will do the same as if you ran
"mvnw spring-boot:run", then you can hit the url previously given.
*/

@Controller
@EnableAutoConfiguration
public class TestController {

    @RequestMapping("/HelloWorld")
    @ResponseBody
    String helloWorld() {
        return "Hello World!";
    }
}