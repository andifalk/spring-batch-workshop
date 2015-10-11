package info.novatec.addressbook.entity;

import java.io.Serializable;

/**
 * Address entity.
 */
@SuppressWarnings("serial")
public class Address implements Serializable {

	private String street;

	private String postOfficeBox;

	private String zip;
	
	private String city;

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
