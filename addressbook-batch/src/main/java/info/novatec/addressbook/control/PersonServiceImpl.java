package info.novatec.addressbook.control;

import static org.springframework.data.jpa.domain.Specifications.where;
import info.novatec.addressbook.entity.Person;
import info.novatec.addressbook.repository.PersonRepository;
import info.novatec.addressbook.repository.PersonSpecifications;

import java.util.ArrayList;
import java.util.List;

/**
 * Standard implementation for {@link PersonService}.
 */
public class PersonServiceImpl implements PersonService {
	private PersonRepository personRepository;

	@Override
	public Person save(final Person entity) {
		return personRepository.save(entity);
	}

	@Override
	public List<Person> save(final Iterable<Person> entities) {
		return personRepository.save(entities);
	}

	@Override
	public Person findOne(final Long id) {
		return personRepository.findOne(id);
	}

	@Override
	public Person findOneWithAddresses(final Long id) {
		return personRepository.findOneWithAddresses(id);
	}

	@Override
	public boolean exists(final Long id) {
		return personRepository.exists(id);
	}

	@Override
	public List<Person> findAll() {
		return personRepository.findAll();
	}
	
	@Override
	public List<Person> findAllOfAge() {
		List<Person> persons = new ArrayList<Person>();
		
		for (Person person : personRepository.findAll(PersonSpecifications.isOfAge())) {
			persons.add(person);
		}
		
		return persons;
	}

	@Override
	public List<Person> findAll(final Iterable<Long> ids) {
		return personRepository.findAll(ids);
	}

	@Override
	public List<Person> findAllMinors() {
		return personRepository.findAll(PersonSpecifications.isMinor());
	}
	
	@Override
	public List<Person> findAllOfAgeWithLastName(final String lastName) {
		return personRepository.findAll(
				where(PersonSpecifications.isOfAge()).and(
						PersonSpecifications.lastNameIs(lastName)));
	}

	@Override
	public Person findByFirstNameAndLastName(final String firstName, final String lastName) {
		return personRepository.findByFirstNameAndLastName(firstName, lastName);
	}

	@Override
	public List<Person> findAllLivingInCity(final String city) {
		return personRepository.findAllLivingInCity(city);
	}

	@Override
	public long count() {
		return personRepository.count();
	}

	@Override
	public void delete(final Long id) {
		personRepository.delete(id);
	}


	@Override
	public void delete(final Person entity) {
		personRepository.delete(entity);
	}

	@Override
	public void delete(final Iterable<Person> entities) {
		personRepository.delete(entities);
	}

	@Override
	public void deleteAll() {
		personRepository.deleteAll();
	}

	/**
	 * Sets the {@link PersonRepository}.
	 * @param personRepository the {@link PersonRepository}
	 */
	public void setPersonRepository(final PersonRepository personRepository) {
		this.personRepository = personRepository;
	}


}
