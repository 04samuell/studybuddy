/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import domain.Customer;
import dao.CustomerCollectionsDAO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;

/**
 *
 * @author leesa178
 */
public class CustomerCollectionsDAOTest {

	private CustomerDAO dao;

	private Customer c1;
	private Customer c2;
	private Customer c3;

	@BeforeAll
	public static void initialise() {
		JdbiDaoFactory.setJdbcUri("jdbc:h2:mem:tests;INIT=runscript from 'src/main/java/dao/schema.sql'");
	}

	@BeforeEach
	public void setUp() {
		dao = DaoFactory.getCustomerDAO();

		c1 = new Customer("sal123", "Sal", "Woodman", "myPassword", "123 Somewhere lane", "salwoodman@mail.com");
		c2 = new Customer("jane22", "Jane", "Scott", "secretpassword", "55 Morning Place", "jane@mail.com");
		c3 = new Customer("bob90", "Bob", "Boris", "1234567", "43A Another St", "bigtimebob@mail.com");

		dao.createCustomer(c1);
		dao.createCustomer(c2);
	}

	@AfterEach
	public void tearDown() {
		dao.deleteCustomer("sal123");
		dao.deleteCustomer("jane22");
		dao.deleteCustomer("bob90");
	}

	@Test
	public void testCreateCustomer() {
		assertThat(dao.getCustomerByUsername("bob90"), is(nullValue())); // Test that bob is not in dao

		dao.createCustomer(c3); // Add bob

		assertThat(dao.getCustomerByUsername("bob90"), is(not(nullValue()))); // Test bob exists in dao
		assertThat(dao.getCustomerByUsername("bob90"), hasProperty("surname", equalTo("Boris"))); // Test bob's properties where correctly added
	}

	@Test
	public void testValidateLogin() {
		assertTrue(dao.validateLogin("sal123", "myPassword")); // test that sals correct username and password returns true
		assertFalse(dao.validateLogin("sal123", "mypassword")); // test that checker is case sensitive
		assertFalse(dao.validateLogin("sal333", "myPassword")); // test wrong username
	}

	@Test
	public void testGetCustomerByUsername() {
		Customer validCustomer = dao.getCustomerByUsername("sal123"); // Test that valid username returns correct customer
		assertThat(validCustomer, hasProperty("emailAddress", equalTo("salwoodman@mail.com")));

		Customer nullCustomer1 = dao.getCustomerByUsername("notACustomerUsername"); // Test that an incorrect username returns null
		assertThat(nullCustomer1, is(nullValue()));

		Customer nullCustomer2 = dao.getCustomerByUsername("bob90"); // Test that a non saved customer is null
		assertThat(nullCustomer2, is(nullValue()));

	}

	@Test
	public void testDeleteAccount() {
		assertThat(dao.getCustomerByUsername("jane22"), is(notNullValue())); // Test jane is in dao

		dao.deleteCustomer("jane22"); //remove jane

		assertThat(dao.getCustomerByUsername("jane22"), is(nullValue())); // Jane should be removed
		assertThat(dao.getCustomerByUsername("sal123"), hasProperty("password", equalTo("myPassword"))); // But sal should still be there

	}

}
