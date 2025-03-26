/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.Customer;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 *
 * @author leesa178
 */
public interface CustomerJdbiDAO extends CustomerDAO {

	@Override
	@SqlUpdate("insert into Customer(username, first_name, surname, password, email_address, shipping_address) values (:username, :firstName, :surname, :password, :emailAddress, :shippingAddress)")
	public void createCustomer(@BindBean Customer c);

	@Override
	@SqlQuery("select exists (select * from Customer where USERNAME = :username AND PASSWORD = :password)")
	public boolean validateLogin(@Bind("username") String username, @Bind("password") String password);

	@Override
	@SqlQuery("select * from Customer where USERNAME = :username")
	@RegisterBeanMapper(Customer.class)
	public Customer getCustomerByUsername(@Bind("username") String username);

	@Override
	@SqlUpdate("delete from Customer where USERNAME = :username")
	public void deleteCustomer(@Bind("username") String username);

}
