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
GameWebservice-0.1.0.jar   You can run that, and it will do the same as if you ran
"mvnw spring-boot:run", then you can hit the url previously given.
*/

@CrossOrigin
@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/game")
public class MainController {

	private final String getItems = "/getItems";
	private final String addCharacter = "/addCharacter";
	private final String deleteCharacter = "/deleteCharacter";

	private static final Logger log = Logger.getLogger( Dao.class.getName() );
    
    /**
     * Retrieves the catalog of items
     *
     * @return List of items
     */
    @GetMapping(value = getItems)
    @ResponseBody
    List<ItemInfo> getItems() throws SQLException {
    	Dao dao = new Dao();
    	log.info("Recieved request: " + getItems);
        return dao.getItems();
    }
    
    /**
     * Adds the character
     *
     * @param  addToCartRequest 	User and their cart
     * @return Number affected rows (int)
     */
    @RequestMapping(method = RequestMethod.POST, value = addCharacter)
    @ResponseBody
    int addCharacter(@RequestBody UserCharacterRequest addCharacterRequest) throws SQLException {
    	Dao dao = new Dao();
    	log.info("Recieved request to " + addCharacter + " with " + addCharacterRequest.toString());
        return dao.addCharacter(addCharacterRequest);
    }
    
    /**
     * Removes the items from the specified user's cart, 
     * and just returns their updated cart. 
     *
     * @param  removeFromCartRequest 	User and their cart
     * @return User's updated cart (List of CartItem)
     */
    @RequestMapping(method = RequestMethod.POST, value = deleteCharacter)
    @ResponseBody
    int deleteCharacter(@RequestBody UserCharacterRequest removeFromCartRequest) throws SQLException {
    	Dao dao = new Dao();
    	log.info("Recieved request to " + deleteCharacter + " with " + removeFromCartRequest.toString());
        return dao.deleteCharacter(removeFromCartRequest);
    }
}







