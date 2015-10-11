package info.novatec.addressbook.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

/**
 * {@link StepExecutionListener} just to log step events.
 */
public class LoggingStepListener implements StepExecutionListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingStepListener.class);

	@Override
	public void beforeStep(final StepExecution stepExecution) {
		LOGGER.info("beforeStep {}", stepExecution.getStepName());

	}

	@Override
	public ExitStatus afterStep(final StepExecution stepExecution) {
		LOGGER.info("afterStep {} with status {}", stepExecution.getStepName(),
				stepExecution.getExitStatus().getExitCode());
		return stepExecution.getExitStatus();
	}

}
