/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.Customer;

/**
 *
 * @author leesa178
 */
public interface CustomerDAO extends CredentialsValidator{

	public void createCustomer(Customer c);

	public boolean validateLogin(String username, String password);

	public Customer getCustomerByUsername(String username);

	public void deleteCustomer(String username);

}
