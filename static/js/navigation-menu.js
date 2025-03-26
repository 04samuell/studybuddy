"use strict";

export const navigationMenu = {

	computed: {
		signedIn() {
			return this.customer != null;
		},
		...Vuex.mapState({
			customer: 'customer'
		})
	},

	template:
	`
	<nav>
		<h2 v-if="signedIn">Welcome {{customer.firstName}}</h2>
		<a href=".">Home</a>
		<a href="viewProducts.html" v-if="signedIn">Browse Products</a>
		<a href="cart.html" v-if="signedIn">View Cart</a>
		<a href="#" v-if="signedIn" @click="signOut()">Sign Out</a>
		<a href="signin.html" v-if="!signedIn">Sign In</a>
	</nav>
	`,

	methods:{
		signOut() {
			sessionStorage.clear();
			window.location = '.';
		}
	}
};