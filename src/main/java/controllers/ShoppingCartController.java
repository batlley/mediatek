package controllers;

import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import models.Product;
import models.ShoppingCartItem;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.params.Param;
import ninja.params.PathParam;
import services.ProductService;
import services.ShoppingCartService;

@Singleton
public class ShoppingCartController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartController.class);
	
    @Inject
	Provider<EntityManager> entitiyManagerProvider;
    
    // show cart details
    public Result show() {
    	LOGGER.debug("show()");
    	
    	ShoppingCartService cs = new ShoppingCartService(entitiyManagerProvider.get());
        ArrayList<ShoppingCartItem> items = cs.getShoppingCartItems();
        HashMap<String, Integer> productNameQty = cs.toHashProductNameQty(items);
        
        return Results.html().render("productsQtyGroupedByProductName", productNameQty);
    }
    
    // add a product (returns: html)
    public Result addProduct(Context context, @PathParam("product_id") int product_id){
    	LOGGER.debug("addProduct(product_id={})",product_id);
    	
    	ProductService ps = new ProductService(entitiyManagerProvider.get());
    	ShoppingCartService cs = new ShoppingCartService(entitiyManagerProvider.get());
    	Product product = ps.findById(product_id);
    	if(product != null)
    		cs.AddProduct(product);
    	return Results.html().render("productAdded");
    }
    
    // add a product (returns: JSON)
    public Result jsonAddProductId( @Param("product_id") int product_id){
    	LOGGER.debug("jsonAddProductId(product_id={})",product_id);
    	
    	ProductService ps = new ProductService(entitiyManagerProvider.get());
    	ShoppingCartService cs = new ShoppingCartService(entitiyManagerProvider.get());
    	Product product = ps.findById(product_id);
    	if(product != null){
    	  cs.AddProduct(product);
    	  String customJson = "{\"result\": \"success\"}"; 
    	  return Results.ok().json().renderRaw(customJson);
    	}else
    	{
    	  String customJson = "{\"result\": \"failed\"}"; 
      	  return Results.notFound().json().renderRaw(customJson);
    	}
    }

}
