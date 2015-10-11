package info.novatec.addressbook.boundary;

import info.novatec.addressbook.entity.Address;
import info.novatec.addressbook.entity.Person;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * Service for managing {@link Person}s and {@link Address}es.
 */
public interface PersonManagementService {
	
	/**
	 * Creates a new {@link Person} for given parameters.
	 * @param firstName first name
	 * @param lastName last name
	 * @param birthDate birth date
	 * @return the created {@link Person}
	 */
	@Transactional
	Person createPerson(String firstName, String lastName, Date birthDate);

	/**
	 * Creates a new {@link Person} for given parameters and {@link Address}es.
	 * @param firstName first name
	 * @param lastName last name
	 * @param birthDate birth date
	 * @param address {@link Address}es for {@link Person} to create
	 * @return the created {@link Person}
	 */
	@Transactional
	Person createPerson(String firstName, String lastName, Date birthDate, Address... address);
	
	/**
	 * Deletes a given {@link Person}.
	 * 
	 * @param person {@link Person} to delete
	 */	
	@Transactional
	void delete(Person person);
	
	/**
	 * Returns the number of {@link Person}s available.
	 * 
	 * @return the number of {@link Person}s
	 */
	@Transactional(readOnly = true)
	long count();
	
	/**
	 * Returns whether a {@link Person} with the given id exists.
	 * 
	 * @param id must not be {@literal null}.
	 * @return true if a {@link Person} with the given id exists, {@literal false} otherwise
	 */	
	@Transactional(readOnly = true)
	boolean exists(Long id);
	
	/**
	 * Returns all instances of the {@link Person} type.
	 * 
	 * @return all {@link Person}s
	 */
	@Transactional(readOnly = true)
	List<Person> findAll();

	/**
	 * Returns all instances of the {@link Person} type that are 'of age', i.e. older than 18 years.
	 * 
	 * @return all {@link Person}s
	 */
	@Transactional(readOnly = true)
	List<Person> findAllOfAge();
	
	/**
	 * Returns all instances of the {@link Person} type that are 'minor', 
	 * i.e. younger than 18 years.
	 * 
	 * @return all {@link Person}s
	 */
	@Transactional(readOnly = true)
	List<Person> findAllMinors();	
	
	/**
	 * Returns all instances of the {@link Person} type that are 'of age', i.e. older than 18 years 
	 * and have the given last name.
	 * 
	 * @param lastName last name
	 * @return all {@link Person}s
	 */
	@Transactional(readOnly = true)
	List<Person> findAllOfAgeWithLastName(String lastName);	

	/**
	 * Retrieves a {@link Person} by its id.
	 * 
	 * @param id must not be {@literal null}.
	 * @return the {@link Person} with the given id or {@literal null} if none found
	 */
	@Transactional(readOnly = true)
	Person findOne(Long id);	

	/**
	 * Retrieves a {@link Person} by its id together with {@link Address}es.
	 * 
	 * @param id must not be {@literal null}.
	 * @return the {@link Person} with the given id or {@literal null} if none found
	 */
	@Transactional(readOnly = true)
	Person findOneWithAddresses(Long id);
	
	/**
	 * Retrieves a {@link Person} for given first name and last name.
	 * 
	 * @param firstName first name
	 * @param lastName last name
	 * @return the {@link Person} or {@literal null} if none found
	 */
	@Transactional(readOnly = true)
	Person findByFirstNameAndLastName(String firstName, String lastName);

	/**
	 * Retrieves a list of {@link Person}s living in given city.
	 * 
	 * @param city city {@link Person}s are living in
	 * @return the list of {@link Person}s or {@literal empty} if none found
	 */
	@Transactional(readOnly = true)
	List<Person> findAllLivingInCity(String city);

}
