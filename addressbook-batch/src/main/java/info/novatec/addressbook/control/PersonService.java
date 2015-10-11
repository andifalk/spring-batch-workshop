package info.novatec.addressbook.control;

import info.novatec.addressbook.entity.Person;

import java.util.List;

/**
 * Service for managing persistence of {@link Person}s.
 */
public interface PersonService {

	/**
	 * Saves all given {@link Person}s.
	 * 
	 * @param persons {@link Person}s to save
	 * @return the saved {@link Person}s
	 */
	Iterable<Person> save(Iterable<Person> persons);

	/**
	 * Saves a given {@link Person}. Use the returned instance for further 
	 * operations as the save operation 
	 * might have changed the entity instance completely.
	 * 
	 * @param person {@link Person} to save
	 * @return the saved {@link Person}
	 */
	Person save(Person person);

	/**
	 * Deletes all {@link Person}s managed by this service.
	 */
	void deleteAll();

	/**
	 * Deletes the given {@link Person}s.
	 * 
	 * @param persons {@link Person}s to delete
	 */
	void delete(Iterable<Person> persons);

	/**
	 * Deletes a given {@link Person}.
	 * 
	 * @param person {@link Person} to delete
	 */	
	void delete(Person person);

	/**
	 * Deletes the {@link Person} with the given id.
	 * 
	 * @param id must not be {@literal null}.
	 */
	void delete(Long id);

	/**
	 * Returns the number of {@link Person}s available.
	 * 
	 * @return the number of {@link Person}s
	 */
	long count();
	
	/**
	 * Returns whether a {@link Person} with the given id exists.
	 * 
	 * @param id must not be {@literal null}.
	 * @return true if a {@link Person} with the given id exists, {@literal false} otherwise
	 */	
	boolean exists(Long id);

	/**
	 * Returns all instances of the {@link Person} type with the given IDs.
	 * 
	 * @param ids ids for {@link Person}s
	 * @return all {@link Person}s having given ids
	 */
	List<Person> findAll(Iterable<Long> ids);

	/**
	 * Returns all instances of the {@link Person} type.
	 * 
	 * @return all {@link Person}s
	 */
	List<Person> findAll();
	
	/**
	 * Returns all instances of the {@link Person} type that are 'of age', i.e. older than 18 years.
	 * 
	 * @return all {@link Person}s
	 */
	List<Person> findAllOfAge();	

	/**
	 * Returns all instances of the {@link Person} type that are 'minor', 
	 * i.e. younger than 18 years.
	 * 
	 * @return all {@link Person}s
	 */
	List<Person> findAllMinors();	

	/**
	 * Returns all instances of the {@link Person} type that are 'of age', i.e. older than 18 years 
	 * and have the given last name.
	 * 
	 * @param lastName last name
	 * @return all {@link Person}s
	 */
	List<Person> findAllOfAgeWithLastName(String lastName);	

	/**
	 * Retrieves a {@link Person} by its id.
	 * 
	 * @param id must not be {@literal null}.
	 * @return the {@link Person} with the given id or {@literal null} if none found
	 */
	Person findOne(Long id);

	/**
	 * Retrieves a {@link Person} by its id together with {@link Address}es.
	 * 
	 * @param id must not be {@literal null}.
	 * @return the {@link Person} with the given id or {@literal null} if none found
	 */
	Person findOneWithAddresses(Long id);
	
	/**
	 * Retrieves a {@link Person} for given first name and last name.
	 * 
	 * @param firstName first name
	 * @param lastName last name
	 * @return the {@link Person} or {@literal null} if none found
	 */
	Person findByFirstNameAndLastName(String firstName, String lastName);
	
	/**
	 * Retrieves a list of {@link Person}s living in given city.
	 * 
	 * @param city city {@link Person}s are living in
	 * @return the list of {@link Person}s or {@literal empty} if none found
	 */
	List<Person> findAllLivingInCity(String city);



}
