/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author leesa178
 */
public class DaoFactory {

	public static CustomerDAO getCustomerDAO() {
		return JdbiDaoFactory.getCustomerDAO();
		//return new CustomerCollectionsDAO();
	}

	public static ProductDAO getProductDAO() {
		return JdbiDaoFactory.getProductDAO();
		//return new ProductCollectionsDAO();
	}

	public static SaleDAO getSaleDAO() {
		return JdbiDaoFactory.getSaleDAO();
	}

}
