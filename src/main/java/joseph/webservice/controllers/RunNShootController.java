package joseph.webservice.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import joseph.webservice.runnshoot.dao.RunNShootDao;

@RequestMapping(value = "/runnshoot")
@Controller
public class RunNShootController {
	
	private static final Logger LOGGER = Logger.getLogger( RunNShootController.class.getName() );
	
	@Inject
	RunNShootDao dao;
	
	/**
     * 
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getAllAccounts")
    @ResponseBody
    List<List<String>> getAllAccounts() throws Exception {
    	LOGGER.info("Recieved request: /getAllAccounts");
        try {
			return dao.getAccounts();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error getting all accounts", e);
			throw e;
		}
    }
    
    /**
     * 
     */
    @RequestMapping(method = RequestMethod.POST, value = "/addAccount")
    @ResponseBody
    int addAccount(@RequestBody String username) throws SQLException {
    	LOGGER.info("Recieved request to /addAccount with " + username.toString());
        return dao.addAccount(username, "pass");
    }
	
	/**
     * 
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getCharacters/{userId}")
    @ResponseBody
    List<String> getCharacterForUser(@PathVariable String userId) throws Exception {
    	LOGGER.info("Recieved request: /getCharacters/" + userId);
        try {
			return dao.getCharactersForUser(userId);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error getting characters for " + userId, e);
			throw e;
		}
    }
	
	/**
     * 
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getCharacterInfo/{name}")
    @ResponseBody
    String getCharacterInfo(@PathVariable String name) throws Exception {
    	LOGGER.info("Recieved request: /getCharacterInfo/" + name);
        try {
			return dao.getCharacterInfo(name);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error getting character info for " + name, e);
			throw e;
		}
    }

}
