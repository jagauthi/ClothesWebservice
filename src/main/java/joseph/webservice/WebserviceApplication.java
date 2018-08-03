
package joseph.webservice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
In order to get this to run, just go in command line to the project folder
and run "mvnw spring-boot:run". That will make it run.
Then you can go to "http://localhost:8080/HelloWorld".
Another option is go to the project folder and run "mvnw clean package". Then if you go to the project
folder, go to the target folder, there will be something called
GameWebservice-0.1.0.jar   You can run that, and it will do the same as if you ran
"mvnw spring-boot:run", then you can hit the url previously given.
*/

@SpringBootApplication
public class WebserviceApplication {
	
	public WebserviceApplication() throws InterruptedException {
		
	}

	public static void main(String[] args) throws InterruptedException {
		//new WebserviceApplication();
		SpringApplication.run(WebserviceApplication.class, args);
	}
}
