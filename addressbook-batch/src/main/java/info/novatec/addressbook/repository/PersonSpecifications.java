package info.novatec.addressbook.repository;

import java.util.Calendar;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import info.novatec.addressbook.entity.Person;
import info.novatec.addressbook.entity.Person_;

import org.springframework.data.jpa.domain.Specification;

/**
 * Specifications for {@link Person} taken from the concept in 
 * Eric Evans' book "Domain Driven Design".
 */
public final class PersonSpecifications {
	private static final int OF_AGE = -18;
	
	private PersonSpecifications() {
		super();
	}

	/**
	 * Specification to query {@link Person}s that are <em>of age</em>, i.e. over 18 years old.
	 * @return {@link Specification}
	 */
	public static Specification<Person> isOfAge() {
		return new Specification<Person>() {
			@Override
			public Predicate toPredicate(final Root<Person> root,
					final CriteriaQuery<?> query, final CriteriaBuilder cb) {
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.YEAR, OF_AGE);
				return cb.lessThan(root.get(Person_.birthDate), cal.getTime());
			}
		};
	}
	
	/**
	 * Specification to query {@link Person}s that are <em>minor</em>, i.e. under 18 years old.
	 * @return {@link Specification}
	 */
	public static Specification<Person> isMinor() {
		return new Specification<Person>() {
			@Override
			public Predicate toPredicate(final Root<Person> root,
					final CriteriaQuery<?> query, final CriteriaBuilder cb) {
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.YEAR, OF_AGE);
				return cb.greaterThan(root.get(Person_.birthDate), cal.getTime());
			}
		};
	}
	
	/**
	 * Specification to query {@link Person}s that have the given last name.
	 * @param lastName the last name
	 * @return {@link Specification}
	 */
	public static Specification<Person> lastNameIs(final String lastName) {
		return new Specification<Person>() {
			@Override
			public Predicate toPredicate(final Root<Person> root,
					final CriteriaQuery<?> query, final CriteriaBuilder cb) {
				return cb.like(root.get(Person_.lastName), lastName + "%");
			}
		};
	}
}
