package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name = "shopping_cart_item")
public class ShoppingCartItem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	@Column(name="product_id", unique=false)
    private Integer product_id;
    

	 public ShoppingCartItem() {
	 }
	 
	 public void setProductId(int id) {
		 this.product_id = id;
	 }
	 
	 public Integer getProductId() {
		 return this.product_id;
	 }
}
