package info.novatec.addressbook.batch;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecution;

/**
 * {@link JobExecutionListener} just to log job events.
 */
public class LoggingJobListener implements JobExecutionListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingJobListener.class);

	@Override
	public void beforeJob(final JobExecution jobExecution) {
		LOGGER.info("beforeJob {}", jobExecution.getJobInstance().getJobName());
	}

	@Override
	public void afterJob(final JobExecution jobExecution) {

		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            Collection<StepExecution> stepExecutions = jobExecution.getStepExecutions();
			for (StepExecution stepExecution : stepExecutions) {
				if (stepExecution.getSkipCount() > 0) {
					jobExecution
							.setExitStatus(new ExitStatus(ExitStatus.COMPLETED
									.getExitCode() + " WITH SKIPS"));
					break;
				}
			}
         }

		LOGGER.info("afterJob {} with status {}", 
				jobExecution.getJobInstance().getJobName(),
				jobExecution.getExitStatus());

	}

}
