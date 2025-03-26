/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package gui;

import dao.ProductDAO;
import domain.Product;
import helpers.SimpleListModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author leesa178
 */
public class ProductViewerTest {

	private ProductDAO dao;
	private DialogFixture fixture;
	private Robot robot;

	private Product p1;
	private Product p2;

	@BeforeEach
	public void setUp() {
		robot = BasicRobot.robotWithNewAwtHierarchy();
		robot.settings().delayBetweenEvents(175);

		Collection<Product> products = new ArrayList<>();
		p1 = new Product("111", "JetSki", "Red Jetski", "Boat", new BigDecimal(4500), new BigDecimal(3));
		p2 = new Product("222", "BasketBall", "Orange Basketball", "Sport", new BigDecimal(20), new BigDecimal(1));
		products.add(p1);
		products.add(p2);

		dao = mock(ProductDAO.class);

		when(dao.getProducts()).thenReturn(products);

		when(dao.searchById("111")).thenReturn(p1);

		Mockito.doAnswer((call) -> {
			products.remove(p1);
			return null;
		}).when(dao).removeProduct(p1);

	}

	@AfterEach
	public void tearDown() {
		fixture.cleanUp();
	}

	@Test
	public void testViewProducts() {
		ProductViewer dialog = new ProductViewer(null, true, dao);

		fixture = new DialogFixture(robot, dialog);
		fixture.show().requireVisible();
		fixture.click();
		verify(dao).getProducts();
		fixture.list("lstProducts").requireItemCount(2);
		SimpleListModel model = (SimpleListModel) fixture.list("lstProducts").target().getModel();

		// Verify JList contents
		assertThat(model, containsInAnyOrder(p1, p2));
	}

	@Test
	public void testSearchProducts() {
		ProductViewer dialog = new ProductViewer(null, true, dao);

		fixture = new DialogFixture(robot, dialog);
		fixture.show().requireVisible();
		fixture.click();

		fixture.list("lstProducts").requireItemCount(2); // Verify contains 2 items

		SimpleListModel model1 = (SimpleListModel) fixture.list("lstProducts").target().getModel();

		assertThat(model1, hasItem(p1));
		assertThat(model1, hasItem(p2));

		// Enter id and click search
		fixture.textBox("txtSearchId").enterText("111");
		fixture.button("btnSearch").click();

		verify(dao).searchById("111");

		SimpleListModel model = (SimpleListModel) fixture.list("lstProducts").target().getModel();

		// Verify list content
		assertThat(model, hasItem(p1));
		assertThat(model, not(hasItem(p2)));
	}

	@Test
	public void testRemoveProduct() {
		ProductViewer dialog = new ProductViewer(null, true, dao);

		fixture = new DialogFixture(robot, dialog);
		fixture.show().requireVisible();
		fixture.click();
		verify(dao).getProducts();
		fixture.list("lstProducts").requireItemCount(2); // Verify contains 2 items
		SimpleListModel model = (SimpleListModel) fixture.list("lstProducts").target().getModel();

		assertThat(model, hasItem(p1)); // Ensure that p1 is originally in JList

		//Select p1 and click delete
		fixture.list("lstProducts").selectItem(p1.toString());
		fixture.button("btnDelete").click();
		fixture.optionPane().requireVisible().yesButton().click();

		verify(dao).removeProduct(p1);

		assertThat(model, not(hasItem(p1))); // Ensure that p1 is no longer there

	}

}
