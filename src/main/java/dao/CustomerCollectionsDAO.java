/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import domain.Customer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author leesa178
 */
public class CustomerCollectionsDAO implements CustomerDAO {

	private static final Map<String, Customer> customers = new HashMap<>();

	@Override
	//change to return boolena if added
	public void createCustomer(Customer c) {
		String username = c.getUsername();
		if (!customers.containsKey(username)) {
			customers.put(username, c);
		}
	}

	@Override
	public boolean validateLogin(String username, String password) {
		Customer c = customers.get(username);
		if (c == null) {
			return false;
		}

		return c.getPassword().equals(password);
	}

	@Override
	public Customer getCustomerByUsername(String username) {
		return customers.get(username);
	}

	@Override
	public void deleteCustomer(String username) {
		customers.remove(username);
	}

}
