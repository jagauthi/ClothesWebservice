package joseph.webservice.classes;

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

    @GetMapping(value = "/getAccount")
    @ResponseBody
    DataPacket getAccount() {
    	Dao dao = new Dao();
        return dao.getAccount("joseph");
    }

    @GetMapping(value = "/getAccounts")
    @ResponseBody
    DataPacket[] getAccounts() {
    	Dao dao = new Dao();
        return dao.getAccounts();
    }
    
    @RequestMapping(value = "/addAccount")
    @ResponseBody
    int addAccount(@RequestParam(value="user") String user, @RequestParam(value="pass") String pass) {
    	Dao dao = new Dao();
        return dao.addAccount(user, pass);
    }
    
    @PostMapping(value = "/swaplol", consumes = "application/json")
    @ResponseBody
    DataPacket swaplol(@RequestBody DataPacket dataPacket) {
    	Dao dao = new Dao();
        return dataPacket.swap();
    }
}







