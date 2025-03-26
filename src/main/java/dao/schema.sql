/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */

CREATE TABLE IF NOT EXISTS customer (
	customer_id INTEGER AUTO_INCREMENT (1000),
	username VARCHAR(50) NOT NULL,
	first_name VARCHAR(50) NOT NULL,
	surname VARCHAR(50) NOT NULL,
	password VARCHAR(50) NOT NULL,
	shipping_address VARCHAR(50) NOT NULL,
	email_address VARCHAR(50) NOT NULL,
	PRIMARY KEY (customer_id)
);

CREATE TABLE IF NOT EXISTS product (
	product_id VARCHAR(50),
	name VARCHAR(50) NOT NULL,
	description VARCHAR(500) NOT NULL,
	category VARCHAR(50) NOT NULL,
	list_price NUMERIC(7, 2),
	quantity_in_Stock NUMERIC(5, 0),
	PRIMARY KEY (product_id),
);

CREATE TABLE IF NOT EXISTS sale (
	sale_id INTEGER AUTO_INCREMENT (1000),
	customer_id INTEGER NOT NULL,
	date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	status VARCHAR(50) NOT NULL,
	PRIMARY KEY (sale_id),
	FOREIGN KEY(customer_id) REFERENCES customer(customer_id)
);

CREATE TABLE IF NOT EXISTS sale_item (
	sale_id INTEGER NOT NULL,
	product_id VARCHAR(50) NOT NULL,
	quantity_purchased NUMERIC(5, 0),
	sale_price NUMERIC(7, 2),
	PRIMARY KEY (sale_id, product_id),
	FOREIGN KEY (sale_id) REFERENCES sale(sale_id),
	FOREIGN KEY (product_id) REFERENCES product(product_id)
);

