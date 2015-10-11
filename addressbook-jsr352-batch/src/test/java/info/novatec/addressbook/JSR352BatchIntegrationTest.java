package info.novatec.addressbook;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Properties;

import javax.batch.runtime.JobExecution;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.test.JsrTestUtils;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Integration test verifying batch job for importing persons using JSR 352 standard.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JSR352BatchIntegrationTestConfig.class)
public class JSR352BatchIntegrationTest {
	private static final String IMPORT_PERSONS_CSV_FILENAME = "import-persons.csv";
	
	/**
	 * Verifies the batch job for importing persons from a csv file.
	 * @throws Exception not expected
	 */
	@Test
	public void verifyPersonImportJobComplete() throws Exception {
		
		Properties properties = new Properties();
		properties.put("csvImportFilePath", IMPORT_PERSONS_CSV_FILENAME);
		JobExecution jobExecution = JsrTestUtils.runJob("importBatchJob", properties, 10000);
		
		assertThat("Job execution should have expected batch status",
				jobExecution.getBatchStatus(), is(javax.batch.runtime.BatchStatus.COMPLETED));
		assertThat("Job execution should have expected exit status",
				jobExecution.getExitStatus(), is("COMPLETED"));
		
	}
	
	
}
