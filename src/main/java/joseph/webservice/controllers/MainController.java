package joseph.webservice.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import joseph.webservice.pojos.ItemInfoResponse;
import joseph.webservice.pojos.LoginRequest;
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
@RequestMapping(value = "/items")
public class MainController {

	private final String getItems = "/getItems";

	private static final Logger log = Logger.getLogger( Dao.class.getName() );
	
    @GetMapping(value = getItems)
    @ResponseBody
    List<ItemInfoResponse> getAccounts() throws SQLException {
    	Dao dao = new Dao();
    	log.info("Recieved request: " + getItems);
        return dao.getItems();
    }
}







