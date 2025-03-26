package web;

import io.jooby.Jooby;
import io.jooby.Route;
import java.nio.file.Paths;

public class StaticAssetModule extends Jooby {

	public StaticAssetModule() {

		// handle favicons (silent 404)
		get("/favicon.ico", Route.FAVICON);

		// serve anything that matches a file in the static folder
		assets("/*", Paths.get("static"));

		// home page
		//assets("/", Paths.get("static/index.html"));
// html files

		assets("/", Paths.get("static/index.html"));
		assets("/index.html", Paths.get("static/index.html"));
		assets("/signin.html", Paths.get("static/signin.html"));
		assets("/500.html", Paths.get("static/500.html"));
		assets("/cart.html", Paths.get("static/cart.html"));
		assets("/createAccount.html", Paths.get("static/createAccount.html"));
		assets("/product-view.html", Paths.get("static/product-view.html"));
		assets("/viewProducts.html", Paths.get("static/viewProducts.html"));
		assets("/order-confirmation.html", Paths.get("static/order-confirmation.html"));

		// css files
		assets("/css/style.css", Paths.get("static/css/style.css"));

		// JavaScript files
		assets("/js/session-store.js", Paths.get("static/js/session-store.js"));
		assets("/js/navigation-menu.js", Paths.get("static/js/navigation-menu.js"));
		assets("/js/sign-in.js", Paths.get("static/js/sign-in.js"));
		assets("/js/create-account.js", Paths.get("static/js/create-account.js"));
		assets("/js/number-formatter.js", Paths.get("static/js/number-formatter.js"));
		assets("/js/index.js", Paths.get("static/js/index.js"));
		assets("/js/cart.js", Paths.get("static/js/cart.js"));
		assets("/js/view-products.js", Paths.get("static/js/view-products.js"));
		assets("/js/authentication.js", Paths.get("static/js/authentication.js"));

		// external JavaScript files
		assets("/js/external/vue.global.js", Paths.get("static/js/external/vue.global.js"));
		assets("/js/external/vuex.global.js", Paths.get("static/js/external/vuex.global.js"));
		assets("/js/external/vuex-persistedstate.js", Paths.get("static/js/external/vuex-persistedstate.js"));
		assets("/js/external/axios.js", Paths.get("static/js/external/axios.js"));

	}
}
