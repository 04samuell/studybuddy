/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

"use strict";

class SaleItem {
	constructor(product, quantityPurchased) {
		this.product = product;
		this.quantityPurchased = quantityPurchased;
		this.salePrice = product.listPrice * quantityPurchased; // * product.quantityPurchased???
	}
}

class Sale {
	constructor(customer, items) {
		this.customer = customer;
		this.items = items;
	}
}

var salesApi = '/api/sales';

const app = Vue.createApp({

	data() {
		return {
			// models (comma separated key/value pairs)

		};
	},

	computed: Vuex.mapState({
		product: 'selectedProduct',
		items: 'items',
		customer: 'customer'
	}),

	mounted() {
		// semicolon separated statements
	},

	methods: {
		// comma separated function declarations

		addItemToCart() {
			if (!Number.isInteger(this.quantity)) {
				alert("Quantity must be an integer");
				return;
			}

			if (this.quantity <= 0) {
				alert("Quantity must be postitve!");
				return;
			}

			if (this.quantity > this.product.quantityInStock) {
				alert("We don't have enough products");
				return;
			}

			sessionStore.commit("addItem", new SaleItem(this.product, this.quantity));
			window.location = 'viewProducts.html';
		},

		// check out all items in cart
		checkOut() {

			if(this.items.length === 0) {
				alert("Check out failed! No items in cart");
				return;
			}

			let sale = new Sale(this.customer, this.items);

			// post the result to save the sale
			axios.post(salesApi, sale)
				.then(() => {
					sessionStore.commit("clearItems");
					window.location = 'order-confirmation.html';
				})
				.catch(error => {
					//alert(error.response.data.message);
					alert("Error processing request. Do you have duplicate items in your cart?");
				});
		},

		getItemTotal(item) {
			return item.product.listPrice * item.quantityPurchased;
		},

		getCartTotal() {
			var grandTotal = 0;
			for(var item of this.items) {
				grandTotal += this.getItemTotal(item);
			}

			return grandTotal;
		}

	},

	// other modules
	mixins: [NumberFormatter, BasicAccessAuthentication]

});



/* other component imports go here */

// import data store
import { sessionStore } from './session-store.js'
	app.use(sessionStore);

// import the number formatter
import { NumberFormatter } from './number-formatter.js';

// import the navigation menu
import { navigationMenu } from './navigation-menu.js';
app.component('navmenu', navigationMenu);

// import authentication module
import { BasicAccessAuthentication } from './authentication.js';

// mount the page - this needs to be the last line in the file
app.mount("main");

