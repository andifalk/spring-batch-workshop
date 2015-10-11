package info.novatec.addressbook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Address entity.
 */
@SuppressWarnings("serial")
@Entity
public class Address extends AbstractPersistable<Long> {
	@Size(min = 0, max = 30)
	@Column(nullable = true, length = 30)
	private String street;

	@Size(min = 1, max = 30)
	@Column(nullable = true, length = 20)
	private String postOfficeBox;

	@NotNull
	@Size(min = 1, max = 10)
	@Column(nullable = false, length = 10)
	private String zip;
	
	@NotNull
	@Size(min = 1, max = 30)
	@Column(nullable = false, length = 30)
	private String city;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Country country;

	/**
	 * Constructor.
	 * @param street to set
	 * @param postOfficeBox to set
	 * @param zip to set
	 * @param city to set
	 * @param country to set
	 */
	public Address(final String street, 
			final String postOfficeBox, final String zip,
			final String city, final Country country) {
		super();
		this.street = street;
		this.postOfficeBox = postOfficeBox;
		this.zip = zip;
		this.city = city;
		this.country = country;
	}
	
	/**
	 * Default constructor for JPA.
	 */
	public Address() {
		super();
	}
	
	/**
	 * Gets the street.
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}
	
	/**
	 * Gets the post office box.
	 * @return the post office box
	 */
	public String getPostOfficeBox() {
		return postOfficeBox;
	}
	
	/**
	 * Gets the zip code.
	 * @return the zip code
	 */
	public String getZip() {
		return zip;
	}
	
	/**
	 * Gets the city.
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Gets the country.
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}
	
}
