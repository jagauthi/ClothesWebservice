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
	private final String removeFromCart = "/removeFromCart";

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
     * Adds the items from the specified user's cart
     *
     * @param  addToCartRequest 	User and their cart
     * @return Number affected rows (int)
     */
    @RequestMapping(method = RequestMethod.POST, value = addToCart)
    @ResponseBody
    int addToCart(@RequestBody UserItemsRequest addToCartRequest) throws SQLException {
    	Dao dao = new Dao();
    	log.info("Recieved request to " + addToCart + " with " + addToCartRequest.toString());
        return dao.addToCart(addToCartRequest);
    }
    
    /**
     * Gets the user's cart
     *
     * @param  user Username
     * @return 	User's cart (List of items)
     */
    @GetMapping(value = getCartForUser)
    @ResponseBody
    List<ItemInfo> getCartForUser(@RequestParam(value="username") String user) throws SQLException {
    	Dao dao = new Dao();
    	log.info("Recieved request: " + getCartForUser);
        return dao.getCartForUser(user);
    }
    
    /**
     * Removes the items from the specified user's cart, 
     * and just returns their updated cart. 
     *
     * @param  addToCartRequest 	User and their cart
     * @return User's updated cart (List of items)
     */
    @RequestMapping(method = RequestMethod.POST, value = removeFromCart)
    @ResponseBody
    List<ItemInfo> removeFromCart(@RequestBody UserItemsRequest removeFromCartRequest) throws SQLException {
    	Dao dao = new Dao();
    	log.info("Recieved request to " + removeFromCart + " with " + removeFromCartRequest.toString());
        return dao.removeFromCart(removeFromCartRequest);
    }
}







