package info.novatec.addressbook;

import java.util.Date;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import info.novatec.addressbook.entity.Address;
import info.novatec.addressbook.entity.Person;
import info.novatec.addressbook.repository.PersonRepository;

/**
 * Main application configuration.
 */
@EnableJpaRepositories("info.novatec.addressbook.repository")
@EnableBatchProcessing
@ImportResource({ "classpath:spring-threading.xml",
	"classpath:spring-service.xml", 
	"classpath:spring-batch-core.xml", 
	"classpath:spring-batch-beans.xml",  
	"classpath:spring-batch-export-job.xml" })
@SpringBootApplication
public class Application {
	
	private static final Logger LOG = LoggerFactory.getLogger(Application.class);
	
	@Value("${xml.export.path}")
	private String xmlExportPath;

	@Value("${csv.export.path}")
	private String csvExportPath;

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
    private JobLauncher exportJobLauncherTestUtils;
	
	@Autowired
	private Job job;
	
	/**
	 * Main entry point.
	 * @param args cmdline args
	 */
    public static void main(final String[] args) {
    	SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public CommandLineRunner commandLineRunner() {
    	return (args) -> {
    		personRepository.save(new Person("Kai", "Hansen", new Date(), new HashSet<Address>()));
    		personRepository.save(new Person("Achim", "Maier", new Date(), new HashSet<Address>()));

    		LOG.info("Number of persons: {}", personRepository.count());
    		
    		exportJobLauncherTestUtils.run(job, new JobParametersBuilder()
    				.addString("xmlExportFilePath", xmlExportPath)
    				.addString("csvExportFilePath", csvExportPath)
    				.toJobParameters());
    	};
    }
    
}
