package info.novatec.addressbook;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import info.novatec.addressbook.boundary.PersonManagementService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Integration test verifying {@link PersonManagementService}.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BatchIntegrationTestConfig.class)
@TransactionConfiguration (transactionManager = "jpaTransactionManager")
public class BatchIntegrationTest {
	private static final String IMPORT_PERSONS_CSV_FILENAME = "import-persons.csv";
	private static final String IMPORT_PERSONS_CSV_WITH_ERRORS_FILENAME 
		= "import-persons-with-errors.csv";

	private static final String EXPORTED_PERSONS_XML_FILENAME = "exported-persons.xml";
	private static final String EXPORTED_PERSONS_CSV_FILENAME = "exported-persons.csv";

	private static final long EXPECTED_NUMBER_OF_IMPORTED_PERSONS = 14L;
	private static final long EXPECTED_NUMBER_OF_IMPORTED_PERSONS_WITH_SKIPS = 11L;

	@Autowired
	private PersonManagementService personManagementService;
	
	@Autowired
	@Qualifier("jobLauncherImportJob")
    private JobLauncherTestUtils importJobLauncherTestUtils;

	@Autowired
	@Qualifier("jobLauncherExportJob")
    private JobLauncherTestUtils exportJobLauncherTestUtils;
	
	@PersistenceContext
	private EntityManager entityManager;
	

	private TransactionTemplate transactionTemplate;

	private Path tempDirectoryPath;
	
	/**
	 * Initializes temporary test directory. 
	 * @throws IOException not expected
	 */
	@Before
	public void createTestDirectory() throws IOException {
		tempDirectoryPath = Files.createTempDirectory("batchintegration_test");
	}
	
	/**
	 * Verifies the batch job for importing persons from a csv file.
	 * @throws Exception not expected
	 */
	@Test
	public void verifyPersonImportJobComplete() throws Exception {
		JobParameters jobParameters = new JobParametersBuilder()
											.addString("csvImportFilePath", 
													IMPORT_PERSONS_CSV_FILENAME)
											.toJobParameters();
		JobExecution jobExecution = importJobLauncherTestUtils.launchJob(jobParameters);
		
		assertThat("Job execution should have expected batch status",
				jobExecution.getStatus(), is(BatchStatus.COMPLETED));
		assertThat("Job execution should have expected exit status",
				jobExecution.getExitStatus().getExitCode(), is("COMPLETED"));
		
		long countOfImportedPersons = transactionTemplate.execute(new TransactionCallback<Long>() {
			@Override
			public Long doInTransaction(final TransactionStatus status) {
				return personManagementService.count();
			}
		});
		assertThat("Should have imported expected number of persons",
				countOfImportedPersons, is(EXPECTED_NUMBER_OF_IMPORTED_PERSONS));
	}

	/**
	 * Verifies the batch job for importing persons from a csv file.
	 * Some invalid datasets should be skipped.
	 * @throws Exception not expected
	 */
	@Test
	public void verifyPersonImportJobWithSkips() throws Exception {
		JobParameters jobParameters = new JobParametersBuilder()
											.addString("csvImportFilePath", 
													IMPORT_PERSONS_CSV_WITH_ERRORS_FILENAME)
											.toJobParameters();
		JobExecution jobExecution = importJobLauncherTestUtils.launchJob(jobParameters);
		
		assertThat("Job execution should have expected batch status",
				jobExecution.getStatus(), is(BatchStatus.COMPLETED));
		assertThat("Job execution should have expected exit status",
				jobExecution.getExitStatus().getExitCode(), is("COMPLETED WITH SKIPS"));
		
		long countOfImportedPersons = transactionTemplate.execute(new TransactionCallback<Long>() {
			@Override
			public Long doInTransaction(final TransactionStatus status) {
				return personManagementService.count();
			}
		});
		assertThat("Should have imported expected number of persons",
				countOfImportedPersons, is(EXPECTED_NUMBER_OF_IMPORTED_PERSONS_WITH_SKIPS));
	}

	/**
	 * Verifies the batch job for exporting persons to csv file.
	 * @throws Exception not expected
	 */
	@Test
	public void verifyPersonExportJob() throws Exception {
		
		long countOfImportedPersons = transactionTemplate.execute(new TransactionCallback<Long>() {
			@Override
			public Long doInTransaction(final TransactionStatus status) {
				personManagementService.createPerson("Hans", "Mustermann", new Date());
				personManagementService.createPerson("Herbert", "Maier", new Date());
				personManagementService.createPerson("Sabine", "Mann", new Date());
				personManagementService.createPerson("Bernd", "Salz", new Date());
				personManagementService.createPerson("Robert", "Bein", new Date());
				personManagementService.createPerson("Steffi", "Huber", new Date());
				personManagementService.createPerson("Volker", "Dauner", new Date());
				personManagementService.createPerson("Gerd", "Nannen", new Date());
				personManagementService.createPerson("Siegfried", "Sieger", new Date());
				personManagementService.createPerson("Peter", "Müller", new Date());
				personManagementService.createPerson("Dieter", "Basler", new Date());
				personManagementService.createPerson("Sieglinde", "Meyer", new Date());
				personManagementService.createPerson("Otto", "Waalkes", new Date());
				personManagementService.createPerson("Chantal", "Dummy", new Date());
				
				return personManagementService.count();
			}
		});
		assertThat("Should have exported expected number of persons",
				countOfImportedPersons, is(14L));

		
		JobParameters jobParameters = new JobParametersBuilder()
											.addString("csvExportFilePath", 
													Paths.get(tempDirectoryPath.toString(), 
													EXPORTED_PERSONS_CSV_FILENAME).toString())
											.addString("xmlExportFilePath", 
													Paths.get(tempDirectoryPath.toString(), 
													EXPORTED_PERSONS_XML_FILENAME).toString())
											.toJobParameters();
		JobExecution jobExecution = exportJobLauncherTestUtils.launchJob(jobParameters);
		
		assertThat("Job execution should have expected batch status",
				jobExecution.getStatus(), is(BatchStatus.COMPLETED));
		assertThat("Job execution should have expected exit status",
				jobExecution.getExitStatus().getExitCode(), is("COMPLETED"));
		
		assertThat("Batch job should have created expected xml file", Files.exists(
				Paths.get(tempDirectoryPath.toString(), EXPORTED_PERSONS_XML_FILENAME)), is(true));
		assertThat("Batch job should have created non-empty xml file", 
				FileUtils.sizeOf(Paths.get(tempDirectoryPath.toString() 
						, EXPORTED_PERSONS_XML_FILENAME).toFile()), 
				is(greaterThan(0L)));

		assertThat("Batch job should have created expected csv file", Files.exists(
				Paths.get(tempDirectoryPath.toString(), EXPORTED_PERSONS_CSV_FILENAME)), is(true));
		assertThat("Batch job should have created non-empty csv file", 
				FileUtils.sizeOf(Paths.get(tempDirectoryPath.toString() 
						, EXPORTED_PERSONS_CSV_FILENAME).toFile()), 
				is(greaterThan(0L)));


	}
	
	/**
	 * Verifies that the export of persons fails when required parameter is missing.
	 * @throws Exception not expected
	 */
	@Test(expected = JobParametersInvalidException.class)
	public void verifyFailPersonExportJobForMissingParameter() throws Exception {
		
		JobParameters jobParameters = new JobParametersBuilder()
											.toJobParameters();
		exportJobLauncherTestUtils.launchJob(jobParameters);
	}	

	/**
	 * Verifies that the import of persons fails when required parameter is missing.
	 * @throws Exception not expected
	 */
	@Test(expected = JobParametersInvalidException.class)
	public void verifyFailPersonImportJobForMissingParameter() throws Exception {
		
		JobParameters jobParameters = new JobParametersBuilder()
											.toJobParameters();
		importJobLauncherTestUtils.launchJob(jobParameters);
	}
	
	/**
	 * Deletes temporary test directory with all contents.
	 */
	@After
	public void cleanDirectory() {
		//TODO: Uncomment to remove temporary test data
		//FileUtils.deleteQuietly(tempDirectoryPath.toFile());
		resetDatabase();
	}

	/**
	 * Sets the transaction manager to initialize {@link TransactionTemplate}.
	 * @param platformTransactionManager the transaction manager
	 */
	@Autowired
	@Qualifier("jpaTransactionManager")
	public void setPlatformTransactionManager(
			final PlatformTransactionManager platformTransactionManager) {
		this.transactionTemplate = new TransactionTemplate(platformTransactionManager);
	}
	
    /**
    * Executes a SQL statement within a transaction. The execution fails, if an statement 
    * cannot be inserted.
    * 
    * @param statement to execute.
    * @param transactionTemplate to execute statements within transaction context.
    * @param entityManager required for SQL statement execution.
    */
    private void executeSqlWriteStatement(final String statement) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(final TransactionStatus status) {
                try {
                    Query createNativeQuery = entityManager.createNativeQuery(statement);
                    createNativeQuery.executeUpdate();
                } catch (PersistenceException e) {
                    fail("Execution of statement '" + statement + "' failed: " + e.getMessage());
                }
            }
        });
    }

    /**
    * Resets (Cleans and initializes) the database tables before the next test is being executed.
    * The cleaning is done by deleting all data from the tables.
    * The execution fails, if an insert statement cannot be inserted.
    * 
    * @param transactionTemplate to execute statements within transaction context.
    * @param entityManager required for SQL statement execution.
    */
    private void resetDatabase() {
        executeSqlWriteStatement("TRUNCATE SCHEMA public AND COMMIT NO CHECK");
    }
	
}
