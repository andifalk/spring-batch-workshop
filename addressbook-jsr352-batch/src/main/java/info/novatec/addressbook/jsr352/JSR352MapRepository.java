package info.novatec.addressbook.jsr352;

import info.novatec.addressbook.entity.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Map based implementation for {@link JSR352Repository}.
 */
public class JSR352MapRepository implements JSR352Repository {
	
	private Map<String, Person> personMap = new HashMap<>();
	
	@Override
	public Person save(final Person person) {
		personMap.put(person.getFirstName() + person.getLastName(), person);
		return person;
	}

	@Override
	public List<Person> findAll() {
		return new ArrayList<Person>(personMap.values());
	}

	@Override
	public long count() {
		return personMap.size();
	}
	
	

}
