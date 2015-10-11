package info.novatec.addressbook.repository;

import info.novatec.addressbook.entity.Person;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository for {@link Person} entity.
 */
public interface PersonRepository extends JpaRepository<Person, Long>, 
	JpaSpecificationExecutor<Person>  {
	
	/**
	 * Retrieves {@link Person} by given first and last name.
	 * @param firstName first name
	 * @param lastName last name
	 * @return {@link Person} or null if none found
	 */
	Person findByFirstNameAndLastName(String firstName, String lastName);
	
	/**
	 * Retrieves {@link Person} by given id together with loaded addresses.
	 * @param id id for {@link Person}
	 * @return {@link Person} or null if none found
	 */
	@Query("SELECT p FROM #{#entityName} p LEFT JOIN FETCH p.addresses WHERE p.id = :id")
	Person findOneWithAddresses(@Param("id") Long id);
	
	/**
	 * Retrieves list of {@link Person}s living in given city.
	 * @param city city {@link Person}s are living in
	 * @return list of {@link Person}s or empty list if none found
	 */
	@Query("SELECT p FROM #{#entityName} p LEFT JOIN p.addresses adr WHERE adr.city = :city")
	List<Person> findAllLivingInCity(@Param("city") String city);

}
