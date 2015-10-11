package info.novatec.addressbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application configuration.
 */
@SpringBootApplication
public class Application {
	
	/**
	 * Main entry point.
	 * @param args cmdline args
	 */
    public static void main(final String[] args) {
    	SpringApplication.run(Application.class, args);

    }
    
}
