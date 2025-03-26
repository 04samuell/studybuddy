/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package gui;

import dao.ProductDAO;
import domain.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author leesa178
 */
public class ProductEditorTest {

	private ProductDAO dao;
	private DialogFixture fixture;
	private Robot robot;

	@BeforeEach
	public void setUp() {
		robot = BasicRobot.robotWithNewAwtHierarchy();
		robot.settings().delayBetweenEvents(45);

		Collection<String> categories = new ArrayList<>();
		categories.add("Sport");
		categories.add("Utilities");

		dao = mock(ProductDAO.class);

		when(dao.getCategories()).thenReturn(categories);
	}

	@AfterEach
	public void tearDown() {
		fixture.cleanUp();
	}

	@Test
	public void testSave() {
		ProductEditor dialog = new ProductEditor(null, true, dao);

		fixture = new DialogFixture(robot, dialog);
		fixture.show().requireVisible();
		fixture.click();

		// enter some details into the UI components
		fixture.textBox("txtId").enterText("11111");
		fixture.textBox("txtName").enterText("Ping Pong Ball");
		fixture.textBox("txtDescription").enterText("5pk of Orange Ping Pong Balls");
		fixture.comboBox("cmbCategory").selectItem("Sport");
		fixture.textBox("txtPrice").enterText("12");
		fixture.textBox("txtQuantity").enterText("1");

		// click the save button
		fixture.button("btnSave").click();

		ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);

		// verify that the DAO.save method was called, and capture the passed student
		verify(dao).saveProduct(argument.capture());

		// retrieve the passed student from the captor
		Product savedProduct = argument.getValue();

		// test that the student's details were properly saved
		assertThat("Ensure the ID was saved", savedProduct, hasProperty("productId", equalTo("11111")));
		assertThat("Ensure the Name was saved", savedProduct, hasProperty("name", equalTo("Ping Pong Ball")));
		assertThat("Ensure the Description was saved", savedProduct, hasProperty("description", equalTo("5pk of Orange Ping Pong Balls")));
		assertThat("Ensure the Category was saved", savedProduct, hasProperty("category", equalTo("Sport")));
		assertThat("Ensure the Price was saved", savedProduct, hasProperty("listPrice", equalTo(new BigDecimal(12))));
		assertThat("Ensure the Quantity was saved", savedProduct, hasProperty("quantityInStock", equalTo(new BigDecimal(1))));

	}

}
