/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

/* global Vue, axios */

var customerApi = ({username}) => `/api/customers/${username}`;

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
		signIn() {
			const username = this.username;

			this.createToken(this.username, this.password); //this.customer.username how to make access correct customer object?

			axios.get(customerApi({username}))
				.then(response => {
					this.customer = response.data;
					sessionStore.commit("signIn", this.customer);
					window.location = 'index.html';
				})
				.catch(error => {
					if (error.response && error.response.status === 404) { // 404 error so username wasn't found
						alert("No customer with supplied username");
					} else if(error.response && error.response.status == 401) {
						alert("Incorrect details");
					} else {
						console.error(error);
						alert("An error occurred - check the console for details.");
					}

				});
		}

	},

	// other modules
	mixins: [BasicAccessAuthentication]

});

// other component imports go here


import { navigationMenu } from './navigation-menu.js';
app.component('navmenu', navigationMenu);

// import data store
import { sessionStore } from './session-store.js'
	app.use(sessionStore);

// import authentication module
import { BasicAccessAuthentication } from './authentication.js';

// mount the page - this needs to be the last line in the file
app.mount("main");


