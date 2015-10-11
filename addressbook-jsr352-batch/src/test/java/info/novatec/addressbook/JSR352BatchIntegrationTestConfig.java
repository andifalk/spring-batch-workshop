package info.novatec.addressbook;

import info.novatec.addressbook.jsr352.JSR352MapRepository;
import info.novatec.addressbook.jsr352.JSR352Repository;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Main application configuration.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class JSR352BatchIntegrationTestConfig {

	@Bean
	JSR352Repository createJsr352Repository() {
		return new JSR352MapRepository();
	}
}
