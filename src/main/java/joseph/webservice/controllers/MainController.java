package joseph.webservice.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import joseph.webservice.pojos.*;
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

@CrossOrigin
@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/items")
public class MainController {

	private final String getItems = "/getItems";
	private final String addToCart = "/addToCart";
	private final String getCartForUser = "/getCartForUser";

	private static final Logger log = Logger.getLogger( Dao.class.getName() );
	
    @GetMapping(value = getItems)
    @ResponseBody
    List<ItemInfo> getAccounts() throws SQLException {
    	Dao dao = new Dao();
    	log.info("Recieved request: " + getItems);
        return dao.getItems();
    }
    
    @RequestMapping(method = RequestMethod.POST, value = addToCart)
    @ResponseBody
    int addToCart(@RequestBody UserItemsRequest addToCartRequest) throws SQLException {
    	Dao dao = new Dao();
    	log.info("Recieved request to " + addToCart + " with " + addToCartRequest.toString());
        return dao.addToCart(addToCartRequest);
    }
    
    @GetMapping(value = getCartForUser)
    @ResponseBody
    List<ItemInfo> getCartForUser(@RequestParam(value="username") String user) throws SQLException {
    	Dao dao = new Dao();
    	log.info("Recieved request: " + getCartForUser);
        return dao.getCartForUser(user);
    }
}







