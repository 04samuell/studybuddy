/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

var productsApi = '/api/products';
var categoriesApi = '/api/categories';
var categoriesFilterApi = ({category}) => `/api/categories/${category}`;

const app = Vue.createApp({

	data() {
		return {
			products: new Array(),
			categories: new Array()

		};
	},

	mounted() {
		// semicolon separated statement
		this.getProducts();
		this.getCategories();

	},

	methods: {
		// comma separated function declarations
		getProducts() {

			// send GET request
			axios.get(productsApi)
				.then(response => {

					this.products = response.data;
				})
				.catch(error => {
					console.error(error);
					alert("An error occurred - check the console for details.");
				});

		},

		getCategories() {

			// send GET request
			axios.get(categoriesApi)
				.then(response => {

					this.categories = response.data;
				})
				.catch(error => {
					console.error(error);
					alert("An error occurred - check the console for details.");
				});

		},

		filterByCategory(category) {

			axios.get(categoriesFilterApi({'category': category}))
				.then(response => {
					this.products = response.data;
				})
				.catch(error => {
					console.error(error);
					alert("An error occurred - check the console for details.");
				});
		},

		buyProduct(product) {
			sessionStore.commit("selectProduct", product);
			window.location = "product-view.html";
		}

	},

	// other modules
	mixins: [],
	mixins:[NumberFormatter, BasicAccessAuthentication]

});

// other component imports go here

// import the navigation menu
import { navigationMenu } from './navigation-menu.js';

// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);

// import the number formatter
import { NumberFormatter } from './number-formatter.js';

import { sessionStore } from './session-store.js'
app.use(sessionStore);

// import authentication module
import { BasicAccessAuthentication } from './authentication.js';

// mount the page - this needs to be the last line in the file
app.mount("main");


