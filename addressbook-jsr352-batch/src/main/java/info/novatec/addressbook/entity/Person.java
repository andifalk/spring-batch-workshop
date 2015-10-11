package info.novatec.addressbook.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Person entity.
 */
@SuppressWarnings("serial")
public class Person implements Serializable {

	private String firstName;
	
	private String lastName;

	private Date birthDate;
	
	private Set<Address> addresses = new HashSet<>();
	
	/**
	 * Default constructor for JPA.
	 */
	public Person() {
		super();
	}

	/**
	 * Constructor.
	 * @param firstName first name
	 * @param lastName last name
	 * @param birthDate birth date
	 * @param addresses {@link Address}es
	 */
	public Person(final String firstName, final String lastName, final Date birthDate,
			final Set<Address> addresses) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.addresses = addresses;
	}

	/**
	 * Gets the first name.
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Gets the last name.
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Gets the birth date.
	 * @return the birth date
	 */
	public Date getBirthDate() {
		return birthDate;
	}
	
	/**
	 * Gets the set of {@link Address}es.
	 * @return the addresses
	 */
	public Set<Address> getAddresses() {
		return addresses;
	}
	
	/**
	 * Adds an {@link Address}.
	 * @param address to add
	 */
	public void addAddress(final Address address) {
		if (this.addresses == null) {
			this.addresses = new HashSet<>();
		}
		
		this.addresses.add(address);
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
					.append("firstName", firstName)
					.append("lastName", lastName)
					.append("birthDate", birthDate)
					.build();
	}
	
	
}
