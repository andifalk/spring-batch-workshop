package info.novatec.addressbook.boundary;

import info.novatec.addressbook.control.PersonService;
import info.novatec.addressbook.entity.Address;
import info.novatec.addressbook.entity.Person;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Standard implementation for {@link PersonManagementService}.
 */
public class PersonManagementServiceImpl implements PersonManagementService {
	
	private PersonService personService;

	@Override
	public Person createPerson(final String firstName, final String lastName, 
			final Date birthDate) {
		Person person = new Person(firstName, lastName, birthDate, new HashSet<Address>());
		return personService.save(person);
	}

	@Override
	public Person createPerson(final String firstName, final String lastName,
			final Date birthDate, final Address... address) {
		Person person = new Person(firstName, lastName, birthDate, new HashSet<Address>());
		for (int i = 0; i < address.length; i++) {
			person.addAddress(address[i]);
		}
		return personService.save(person);
	}

	@Override
	public void delete(final Person person) {
		personService.delete(person);
	}

	@Override
	public long count() {
		return personService.count();
	}

	@Override
	public boolean exists(final Long id) {
		return personService.exists(id);
	}

	@Override
	public List<Person> findAll() {
		return personService.findAll();
	}
	
	@Override
	public List<Person> findAllOfAge() {
		return personService.findAllOfAge();
	}
	
	@Override
	public List<Person> findAllMinors() {
		return personService.findAllMinors();
	}
	
	@Override
	public List<Person> findAllOfAgeWithLastName(final String lastName) {
		return personService.findAllOfAgeWithLastName(lastName);
	}

	@Override
	public Person findOne(final Long id) {
		return personService.findOne(id);
	}

	@Override
	public Person findOneWithAddresses(final Long id) {
		return personService.findOneWithAddresses(id);
	}
	
	@Override
	public Person findByFirstNameAndLastName(final String firstName, final String lastName) {
		return personService.findByFirstNameAndLastName(firstName, lastName);
	}
	
	@Override
	public List<Person> findAllLivingInCity(final String city) {
		return personService.findAllLivingInCity(city);
	}

	/**
	 * Sets the {@link PersonService}.
	 * @param personService the {@link PersonService}
	 */
	public void setPersonService(final PersonService personService) {
		this.personService = personService;
	}

}
