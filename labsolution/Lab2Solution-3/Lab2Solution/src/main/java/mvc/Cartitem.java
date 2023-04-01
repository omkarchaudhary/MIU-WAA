package mvc;


public class Cartitem {
    private int quantity;
    private Product product;
    private double cartLinePrice;
    
    public Cartitem() {
    }
 
    public Cartitem(Product product, int quantity) {
       this.product=product;
       this.quantity=quantity;
       this.cartLinePrice= quantity * product.getPrice();
    }
   
    public void setProduct(Product product) {
        this.product = product;
        cartLinePrice= quantity * product.getPrice();
    }

    public Product getProduct() {
        return product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        cartLinePrice= quantity * product.getPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public double getCartLinePrice() {
        return cartLinePrice;
    }

    public void setCartLinePrice(double cartLinePrice) {
        this.cartLinePrice = cartLinePrice;
    }
}
