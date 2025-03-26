/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.Product;
import java.util.Collection;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 *
 * @author leesa178
 */
public interface ProductJdbiDAO extends ProductDAO {

	@Override
	@SqlQuery("SELECT * FROM Product WHERE category = :category")
	@RegisterBeanMapper(Product.class)
	public Collection<Product> filterByCategory(@Bind("category") String category);

	@Override
	@SqlQuery("SELECT DISTINCT category FROM Product order by category")
	public Collection<String> getCategories();

	@Override
	@SqlQuery("SELECT * FROM Product ORDER BY product_id")
	@RegisterBeanMapper(Product.class)
	public Collection<Product> getProducts();

	@Override
	@SqlUpdate("DELETE FROM Product WHERE product_id = :productId")
	public void removeProduct(@BindBean Product product);

	@Override
	@SqlUpdate("INSERT INTO Product(Product_id, Name, Description, Category, List_price, Quantity_in_stock) values (:productId, :name, :description, :category, :listPrice, :quantityInStock)")
	public void saveProduct(@BindBean Product product);

	@Override
	@SqlQuery("SELECT * FROM Product WHERE product_id = :productId")
	@RegisterBeanMapper(Product.class)
	public Product searchById(@Bind("productId") String id);



}
