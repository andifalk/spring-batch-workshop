package info.novatec.addressbook.jsr352;

import info.novatec.addressbook.entity.Person;

import java.util.List;

/**
 * Simple Repository for storing and finding {@link Person}s.
 */
public interface JSR352Repository {
	
	/**
	 * Save given {@link Person}.
	 * @param person to save
	 * @return Saved person
	 */
	Person save(Person person);
	
	/**
	 * Finds all persistent {@link Person}s.
	 * @return the persons
	 */
	List<Person> findAll();
	
	/**
	 * Counts the number of persistent {@link Person}s.
	 * @return the count
	 */
	long count();
}
