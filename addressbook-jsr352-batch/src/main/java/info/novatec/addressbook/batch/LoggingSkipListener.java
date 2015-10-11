package info.novatec.addressbook.batch;

import info.novatec.addressbook.entity.Person;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.SkipListener;

/**
 * Skip listener to log skipped items.
 */
public class LoggingSkipListener  
	implements SkipListener<Person, Person> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingSkipListener.class);
	
	@Override
	public void onSkipInRead(final Throwable t) {
		LOGGER.warn("Skipped person in read. Cause: {}", ExceptionUtils.getRootCauseMessage(t));
	}

	@Override
	public void onSkipInWrite(final Person item, final Throwable t) {
		LOGGER.warn("Skipped person {} in write. Cause: {}", item,
				ExceptionUtils.getRootCauseMessage(t));
	}

	@Override
	public void onSkipInProcess(final Person item, final Throwable t) {
		LOGGER.warn("Skipped person {} in process. Cause: {}", item,
				ExceptionUtils.getRootCauseMessage(t));
	}

}
