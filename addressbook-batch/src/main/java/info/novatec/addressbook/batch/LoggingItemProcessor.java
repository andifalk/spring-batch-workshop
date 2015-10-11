package info.novatec.addressbook.batch;

import info.novatec.addressbook.entity.Person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

/**
 * {@link ItemProcessor} just to log processing of an item.
 */
public class LoggingItemProcessor implements ItemProcessor<Person, Person> {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingItemProcessor.class);
	
	@Override
	public Person process(final Person item) throws Exception {
		LOGGER.info("Processing {}", item);
		return item;
	}

}
