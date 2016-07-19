package services;
import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.inject.Inject;

import models.Product;
import models.ShoppingCartItem;

public class ShoppingCartService {

    private EntityManager entityManager;
    
    @Inject
    public ShoppingCartService(EntityManager em){
    	entityManager = em;
    }

  public ArrayList<ShoppingCartItem> getShoppingCartItems() {
	Query query = entityManager.createQuery("SELECT item FROM ShoppingCartItem item", ShoppingCartItem.class); 
    ArrayList<ShoppingCartItem> res = (ArrayList<ShoppingCartItem>) query.getResultList();
    if(res.isEmpty()) return new ArrayList<ShoppingCartItem>(0);
    return res;
  }

  public boolean AddProduct(Product product) {
	  entityManager.getTransaction( ).begin( );
	    ShoppingCartItem item = new ShoppingCartItem();
	    if(product == null) return false;
	    item.setProductId(product.getId());
	    entityManager.persist(item);
	    entityManager.getTransaction().commit();
	    entityManager.close();
	    return true;
  }
  
  //returns hash where key=product-name
  // and value=quantity in the cart
  // eg.: {
  //  key: "Apple," value: 4
  //  key: "Car", value: 3
  //}
  public HashMap<String, Integer> toHashProductNameQty(ArrayList<ShoppingCartItem> list) {
	  HashMap<String, Integer> res = new HashMap<>();
	  ProductService ps = new ProductService(entityManager);
  	  
	  if(list == null) return res;
	  
	  // get all product names
	  HashMap<Integer,String> productNamesGroupedById = ps.toHashProductIdWithProductName(ps.all());
	  
	  // loop to create hash
	  for(ShoppingCartItem item : list){
		  Integer productId = item.getProductId();
		  
		  if(productNamesGroupedById.containsKey(productId)){
			  String prodName = productNamesGroupedById.get(productId);
			  
			  if(res.containsKey(prodName)) {
				  Integer qty = res.get(prodName);
				  res.put(prodName, ++qty);
			  }else {
				  res.put(prodName, 1);
			  }
		  }
	  }
	  
	  return res;
  }
}
