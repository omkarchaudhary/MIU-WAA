package mvc;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;



public class ShoppingCart {
    private List<Cartitem> cartlist = new LinkedList<Cartitem>();
    private double totalPrice= 0;

    public ShoppingCart() {
    }
    
    public void addToCart(Product product){
        boolean found=false;
        // first check if product is already in the shoppingcart
        Iterator<Cartitem> iter = cartlist.iterator();
        while (iter.hasNext()){
            Cartitem cartitem =  iter.next();
            Product prod = cartitem.getProduct();
            if (prod.getProductNumber().equals(product.getProductNumber())){
                cartitem.setQuantity(cartitem.getQuantity()+1);
                found=true;
                break;
            }
        }
        if (!found){
          cartlist.add(new Cartitem(product,1));
        }
        computeTotalPrice();
    }

    public void computeTotalPrice(){
        totalPrice = 0;
        Iterator<Cartitem> iter = cartlist.iterator();
        while (iter.hasNext()){
            Cartitem cartitem =  iter.next();
            totalPrice = totalPrice + (cartitem.getQuantity()* cartitem.getProduct().getPrice());
        }
    }
    
    public void removeFromCart(String productNumber){
        Iterator<Cartitem> iter = cartlist.iterator();
        while (iter.hasNext()){
            Cartitem cartitem =  iter.next();
            Product product = cartitem.getProduct();
            if (product.getProductNumber().equals(productNumber)){
            	if (cartitem.getQuantity()== 1){
                    iter.remove();
                    computeTotalPrice();
                    break;
            	}
            	else{
            		cartitem.setQuantity(cartitem.getQuantity()-1);
            	}
            }
        }
    }

    public void setCartlist(List<Cartitem> cart) {
        this.cartlist = cart;
    }

    public List<Cartitem> getCartlist() {
        return cartlist;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
