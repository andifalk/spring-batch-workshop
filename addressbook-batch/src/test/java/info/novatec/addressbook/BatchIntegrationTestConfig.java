package info.novatec.addressbook;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main application configuration.
 */
@Configuration
@ComponentScan
@EnableJpaRepositories("info.novatec.addressbook.repository")
@EnableBatchProcessing
@EnableAutoConfiguration
@ImportResource({
	"classpath:spring-threading-test.xml",
	"classpath:spring-service.xml", 
	"classpath:spring-batch-core.xml", 
	"classpath:spring-batch-beans.xml", 
	"classpath:spring-batch-import-job.xml", 
	"classpath:spring-batch-export-job.xml", 
	"classpath:spring-batch-test.xml" })
public class BatchIntegrationTestConfig {

}
