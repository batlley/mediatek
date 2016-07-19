package controllers;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import models.Product;
import ninja.Result;
import ninja.Results;
import ninja.params.PathParam;
import services.ProductService;

@Singleton
public class ProductController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Inject
	Provider<EntityManager> entitiyManagerProvider;

    @Inject
    private ProductService productService;
    
    // list all products
    public Result list() {
    	LOGGER.debug("list()");
    	ProductService ps = new ProductService(entitiyManagerProvider.get());
        ArrayList<Product> products = productService.all();
        return Results.html().render("products", products);
    }

    // show product details 
    public Result show(@PathParam("id") int id) {
    	LOGGER.debug("show(id={})", id);
        Product product = productService.findById(id);
        return Results.html().render("product", product);
    }

}
