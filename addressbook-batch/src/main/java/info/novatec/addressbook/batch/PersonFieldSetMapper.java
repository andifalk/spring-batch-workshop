package info.novatec.addressbook.batch;

import info.novatec.addressbook.entity.Address;
import info.novatec.addressbook.entity.Person;

import java.util.Date;
import java.util.HashSet;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 * {@link FieldSetMapper} for a {@link Person} entity.
 */
public class PersonFieldSetMapper implements FieldSetMapper<Person> {

	@Override
	public Person mapFieldSet(final FieldSet fieldSet) throws BindException {
		
		String firstName = fieldSet.readString(0);
		String lastName = fieldSet.readString(1);
		Date birthDate = fieldSet.readDate(2);
		
		Person person = new Person(firstName, lastName, birthDate, new HashSet<Address>());
		return person;
	}

}
