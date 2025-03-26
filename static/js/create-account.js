/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

/* global Vue, axios */

var registerApi = 'api/register';

const app = Vue.createApp({

	data() {
		return {
			// models map (comma separated key/value pairs)
			customer: new Object()
		};
	},

	mounted() {
		// semicolon separated statements
	},

	methods: {
		// comma separated function declarations
		createAccount(username, firstName, surname, emailAddress, shippingAddress, password) {

			// add customer attributes to global variable
			this.customer.username = username;
			this.customer.firstName = firstName;
			this.customer.surname = surname;
			this.customer.emailAddress = emailAddress;
			this.customer.shippingAddress = shippingAddress;
			this.customer.password = password;

			// post the result to save the customer
			axios.post(registerApi, this.customer)
				.then(() => {
					window.location = 'index.html';
				})
				.catch(error => {
					alert(error.response.data.message);
				});
		}

	},

	// other modules
	mixins: [BasicAccessAuthentication]

});

// other component imports go here


// import data store
import { sessionStore } from './session-store.js'
	app.use(sessionStore);

// import the navigation menu
import { navigationMenu } from './navigation-menu.js';
app.component('navmenu', navigationMenu);

// import authentication module
import { BasicAccessAuthentication } from './authentication.js';

// mount the page - this needs to be the last line in the file
app.mount("main");


