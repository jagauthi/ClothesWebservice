package joseph.webservice.controllers;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import joseph.webservice.pojos.LoginRequest;
import joseph.webservice.utils.Dao;

/*
In order to get this to run, just go in command line to the project folder
and run "mvnw spring-boot:run". That will make it run.
Then you can go to "http://localhost:8080/healthCheck".

Another option is go to the project folder and run "mvnw clean package". Then if you go to the project
folder, go to the target folder, there will be something called
ClothesWebservice-0.1.0.jar   You can run that, and it will do the same as if you ran
"mvnw spring-boot:run", then you can hit the url previously given.
*/

@Controller
@EnableAutoConfiguration
public class LoginController {

	private final String login = "/login";
	private final String healthCheck = "/healthCheck";
	private final String getAccount = "/getAccount";
	private final String getAccounts = "/getAccounts";
	private final String addAccount = "/addAccount";

	private static final Logger log = Logger.getLogger( Dao.class.getName() );

    @GetMapping(value = healthCheck)
    @ResponseBody
    Map<String, String> healthCheck() throws SQLException {
    	log.info("Recieved request: " + healthCheck);
    	Map<String, String> response = new HashMap<String, String>();
    	response.put("Healthcheck", "Success");
    	response.put("Current time", new Date().toString());
        return response;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = login)
    @ResponseBody
    LoginRequest login(@RequestBody LoginRequest loginRequest) throws SQLException {
    	Dao dao = new Dao();
    	log.info("Recieved request to " + login + " with " + loginRequest.toString());
        return dao.login(loginRequest);
    }

    @GetMapping(value = getAccounts)
    @ResponseBody
    List<LoginRequest> getAccounts() throws SQLException {
    	Dao dao = new Dao();
    	log.info("Recieved request: " + getAccounts);
        return dao.getAccounts();
    }

    @GetMapping(value = getAccount)
    @ResponseBody
    LoginRequest getAccount(@RequestParam(value="username") String user) throws SQLException {
    	Dao dao = new Dao();
    	log.info("Recieved request: " + getAccount);
        return dao.getAccount(user);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = addAccount)
    @ResponseBody
    int addAccount(@RequestBody LoginRequest loginRequest) throws SQLException {
    	Dao dao = new Dao();
    	log.info("Recieved request to " + addAccount + " with " + loginRequest.toString());
        return dao.addAccount(loginRequest);
    }
}







