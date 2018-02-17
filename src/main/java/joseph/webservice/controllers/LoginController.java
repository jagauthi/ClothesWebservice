package joseph.webservice.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import joseph.webservice.pojos.LoginPacket;
import joseph.webservice.utils.Dao;

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
public class LoginController {

	private final String login = "/login";
	private final String getAccount = "/getAccount";
	private final String getAccounts = "/getAccounts";
	private final String addAccount = "/addAccount";

	private static final Logger log = Logger.getLogger( Dao.class.getName() );
    
    @RequestMapping(method = RequestMethod.POST, value = login)
    @ResponseBody
    LoginPacket login(@RequestBody LoginPacket loginRequest) {
    	Dao dao = new Dao();
    	log.info("Recieved request to " + login + " with " + loginRequest.toString());
        return dao.login(loginRequest);
    }

    @GetMapping(value = getAccounts)
    @ResponseBody
    List<LoginPacket> getAccounts() {
    	Dao dao = new Dao();
    	log.info("Recieved request: " + getAccounts);
        return dao.getAccounts();
    }

    @GetMapping(value = getAccount)
    @ResponseBody
    LoginPacket getAccount(@RequestParam(value="user") String user) {
    	Dao dao = new Dao();
    	log.info("Recieved request: " + getAccount);
        return dao.getAccount(user);
    }
    
    @RequestMapping(value = addAccount)
    @ResponseBody
    int addAccount(@RequestParam(value="user") String user, @RequestParam(value="pass") String pass) {
    	Dao dao = new Dao();
    	log.info("Recieved request: " + addAccount);
        return dao.addAccount(user, pass);
    }
    
    @PostMapping(value = "/swaplol", consumes = "application/json")
    @ResponseBody
    LoginPacket swaplol(@RequestBody LoginPacket dataPacket) {
    	Dao dao = new Dao();
    	log.info("Lol who's using this :P");
        return dataPacket.swap();
    }
}







