package mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CartController {

    @PostMapping("/addtocart")
    public ModelAndView addToCart(@RequestParam("productNumber") String productNumber,
                                  HttpSession session,
                                  RedirectAttributes redirectAttributes) {
        Map<String, Object> params = new HashMap<>();
        if (productNumber != null) {
            //get the productlist from the session
            Map<String, Product> productList = (Map<String, Product>) session.getAttribute("productList");
            Product product = productList.get(productNumber);
            //get the shoppingcart from the session
            ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
            //if there is no shoppingcart in the session, create one.
            if (shoppingCart == null) {
                shoppingCart = new ShoppingCart();
                session.setAttribute("shoppingCart", shoppingCart);
            }

            //add the product to the shoppingCart
            shoppingCart.addToCart(product);
            redirectAttributes.addFlashAttribute( "items", shoppingCart.getCartlist());
            redirectAttributes.addFlashAttribute( "totalprice", shoppingCart.getTotalPrice());
        }
        return new ModelAndView("redirect:showcart", params);
    }

    @GetMapping("/showcart")
    public String showCart() {
        return "showcart";
    }

    @PostMapping("/removefromcart")
    public ModelAndView removefromCart(@RequestParam("productNumber") String productNumber,
                                       HttpSession session,
                                       RedirectAttributes redirectAttributes) {
        Map<String, Object> params = new HashMap<>();
        if (productNumber != null) {
            //get the shoppingcart from the session
            ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
            //if there is no shoppingcart in the session, create one.
            if (shoppingCart == null) {
                shoppingCart = new ShoppingCart();
                session.setAttribute("shoppingCart", shoppingCart);
            }

            //add the product to the shoppingCart
            shoppingCart.removeFromCart(productNumber);
            redirectAttributes.addFlashAttribute( "items", shoppingCart.getCartlist());
            redirectAttributes.addFlashAttribute( "totalprice", shoppingCart.getTotalPrice());
        }
        return new ModelAndView("redirect:showcart", params);
    }
}
