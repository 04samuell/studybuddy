package web;

import dao.CustomerDAO;
import dao.DaoFactory;
import dao.ProductDAO;
import dao.SaleDAO;
import domain.Customer;
import domain.Product;
import io.jooby.Jooby;
import io.jooby.ServerOptions;
import io.jooby.StatusCode;
import io.jooby.gson.GsonModule;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.Set;

public class Server extends Jooby {

	private static final CustomerDAO customerDao = DaoFactory.getCustomerDAO();
	private static final ProductDAO productDao = DaoFactory.getProductDAO();
	private static final SaleDAO saleDao = DaoFactory.getSaleDAO();

	public Server() {

		error(StatusCode.SERVER_ERROR, (ctx, cause, code) -> {
			ctx.getRouter().getLog().error(cause.getMessage(), cause);
			ctx.send(Paths.get("static/500.html"));
		});

		mount(new StaticAssetModule());
		install(new GsonModule());
		install(new BasicAccessAuth(customerDao, Set.of("/api/.*"), Set.of("/api/register")));
		mount(new ProductModule(productDao));
		mount(new CustomerModule(customerDao));
		mount(new SaleModule(saleDao));


	}

	public static void main(String[] args) {
		System.out.println("\nStarting Server.");
		new Server()
			//.setServerOptions(new ServerOptions().setPort(8087))
			.start();
	}

}
