/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import domain.Product;
import java.math.BigDecimal;
import java.util.Objects;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author leesa178
 */
public class ProductCollectionsDAOTest {

	private ProductDAO dao;

	private Product product1;
	private Product product2;
	private Product product3;

	@BeforeAll
	public static void initialise() {
		JdbiDaoFactory.setJdbcUri("jdbc:h2:mem:tests;INIT=runscript from 'src/main/java/dao/schema.sql'");
	}

	@BeforeEach
	public void setUp() {
		dao = DaoFactory.getProductDAO();

		product1 = new Product("1100", "Jetski", "Red Jetski", "Boats", new BigDecimal(1000), new BigDecimal(10));
		product2 = new Product("2200", "Boat", "Blue Boat", "Boats", new BigDecimal(500), new BigDecimal(5));
		product3 = new Product("3300", "Anchor", "Gray Anchor", "Equipment", new BigDecimal(100), new BigDecimal(250));

		dao.saveProduct(product1);
		dao.saveProduct(product2);
	}

	@AfterEach
	public void tearDown() {
		dao.removeProduct(product1);
		dao.removeProduct(product2);
		dao.removeProduct(product3);
	}

	@Test
	public void testSaveProduct() {
		assertThat(dao.getProducts(), not(hasItem(product3)));
		assertThat(dao.getProducts(), hasSize(2));

		dao.saveProduct(product3);

		assertThat(dao.getProducts(), hasItem(product3));
		assertThat(dao.getProducts(), hasSize(3));
	}

	@Test
	public void testRemoveProduct() {
		assertThat(dao.getProducts(), hasItem(product1));
		assertThat(dao.getProducts(), hasSize(2));

		dao.removeProduct(product1);

		assertThat(dao.getProducts(), not(hasItem(product1))); // first product should be removed
		assertThat(dao.getProducts(), hasItem(product2)); // but not the second
		assertThat(dao.getProducts(), hasSize(1)); // verify size decreased
	}

	@Test
	public void testGetProducts() { // failing when unfocused
		assertThat(dao.getProducts(), hasItem(product1)); // test that first and second products are retrieved
		assertThat(dao.getProducts(), hasItem(product2));
		assertThat(dao.getProducts(), not(contains(product3))); // test that third product wasn't

	}

	@Test
	public void testGetCategories() {
		assertThat(dao.getCategories(), hasItem("Boats")); // Boats should be returned
		assertThat(dao.getCategories(), not(hasItem("Equipment"))); // Not Equipment though
	}

	@Test
	public void testSearchById() {
		Product product = dao.searchById("1100");
		assertThat(product, hasProperty("name", equalTo("Jetski"))); // Test that valid Id returns product

		Product productNull1 = dao.searchById("3300"); // Test that product3 is null
		assertThat(productNull1, is(nullValue()));

		Product productNull2 = dao.searchById("55"); // Test that a non existent product is null
		assertThat(productNull2, is(nullValue()));
	}

	@Test
	public void testFilterByCategory() {
		assertThat(dao.filterByCategory("Boats"), containsInAnyOrder(product1, product2));  // Test that filtering by "Boats" returns the correct Products contains(product1, product2)
		assertThat(dao.filterByCategory("Equipment"), hasSize(0)); // Ensure that no Products are returned for category not in dao
	}

}
