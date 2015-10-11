package info.novatec.addressbook.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Person entity.
 */
@SuppressWarnings("serial")
@Entity
public class Person extends AbstractPersistable<Long> {

	@NotNull
	@Size(min = 1, max = 30)
	@Column(nullable = false, length = 30)
	private String firstName;
	
	@NotNull
	@Size(min = 1, max = 30)
	@Column(nullable = false, length = 30)
	private String lastName;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	@Valid
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
