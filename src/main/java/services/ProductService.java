package services;

import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.inject.Inject;

import models.Product;

public class ProductService {

    private EntityManager entityManager;
    
    @Inject
    public ProductService(EntityManager em){
    	entityManager = em;
    }

  public Product createProduct(String name) {
	
	entityManager.getTransaction( ).begin( );
    Product p = new Product(name);
    p.setName(name);
    entityManager.persist(p);
    entityManager.getTransaction().commit();
    entityManager.close();
    
    return p;
  }

  public void removeProduct(int id) {
    Product p = findById(id);
    if (p != null) {
    	entityManager.remove(p);
    }
  }

  public Product findById(int id) {
    return entityManager.find(Product.class, id);
  }

  public ArrayList<Product> all() {
    Query query = entityManager.createQuery("SELECT p FROM Product p", Product.class);
    ArrayList<Product> res = (ArrayList<Product>) query.getResultList();
    
    return res;
  }
  
  //returns hash where key=product-id
  // and value=product-name
  // eg.: {
  //  key: 4 value: "Apple"
  //  key: 2, value: "Car"
  //}
  public HashMap<Integer,String> toHashProductIdWithProductName(ArrayList<Product> products) {
	  HashMap<Integer, String> res = new HashMap<>();
	  
	  if(products == null) return res;
	 
	  for(Product product : products) {
		 Integer prodId = product.getId();
		 String prodName = product.getName();
		 res.put(prodId, prodName);
	  }
	  return res;
  }
}
