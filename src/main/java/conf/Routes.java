package conf;

import controllers.ApplicationController;
import controllers.ProductController;
import controllers.ShoppingCartController;
import ninja.AssetsController;
import ninja.Router;
import ninja.application.ApplicationRoutes;

public class Routes implements ApplicationRoutes {

    @Override
    public void init(Router router) {  
        
    	// index / root
        router.GET().route("/").with(ApplicationController.class, "index");
        // assets (static files)
        router.GET().route("/assets/webjars/{fileName: .*}").with(AssetsController.class, "serveWebJars");
        router.GET().route("/assets/{fileName: .*}").with(AssetsController.class, "serveStatic");
      
        // product routes  
        router.GET().route("/products/list").with(ProductController.class, "list");
        router.GET().route("/products/{id}").with(ProductController.class, "show");
        
        // shopping card routes
        router.GET().route("/shopping_cart/").with(ShoppingCartController.class, "show");
        //shopping_cart/add/4
        router.GET().route("/shopping_cart/add/{product_id}").with(ShoppingCartController.class, "addProduct");
        
        
        ////////////////////////
        //JSON routes
        
        //shopping_cart/addProduct.json?product_id=5
        router.GET().route("/shopping_cart/addProduct{filename: .json}").with(ShoppingCartController.class, "jsonAddProductId");
    }

}
