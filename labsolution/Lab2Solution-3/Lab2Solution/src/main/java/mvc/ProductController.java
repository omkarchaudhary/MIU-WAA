package mvc;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@Controller
public class ProductController {

    @PostMapping("/addproduct")
    public ModelAndView addProduct(  @Valid @ModelAttribute("product") Product product, BindingResult bindingResult,
                                     HttpSession session, RedirectAttributes redirectAttributes) {
        Map<String, Object> params = new HashMap<>();
            if (bindingResult.hasErrors()) {
                return new ModelAndView("addproduct", params);
            }
        if (product != null) {
            //get the productlist from the session
            Map<String, Product> productList = (Map<String, Product>) session.getAttribute("productList");
            //if there is no productlist in the session, create one.
            if (productList == null) {
                productList = new HashMap<String, Product>();
                session.setAttribute("productList", productList);
            }
            //add the product to the productlist
            productList.put(product.getProductNumber(), product);
            redirectAttributes.addFlashAttribute( "productList", productList.values());
        }
        return new ModelAndView("redirect:products", params);
    }

    @GetMapping("/products")
    public ModelAndView init(HttpSession session) {
        //get the productlist from the session
        Map<String, Product> productList = (Map<String, Product>) session.getAttribute("productList");
        //if there is no productlist in the session, create one.
        if (productList == null) {
            productList = new HashMap<String, Product>();
            session.setAttribute("productList", productList);
        }
        Map<String, Object> params = new HashMap<>();
        params.put("productList", productList.values());
        return new ModelAndView("products", params);
    }

    @PostMapping("/addproductpage")
    public ModelAndView addproductNavigation(HttpSession session) {
        Map<String, Object> params = new HashMap<>();
        params.put("product", new Product());
        return new ModelAndView("addproduct", params);
    }

    @PostMapping("/removeproduct")
    public ModelAndView removeproduct(@RequestParam("productNumber") String productNumber,
                                  HttpSession session,
                                  RedirectAttributes redirectAttributes) {
            Map<String, Object> params = new HashMap<>();
            if (productNumber != null) {
                //get the productlist from the session
                Map<String, Product> productList = (Map<String, Product>) session.getAttribute("productList");
                //if there is no productlist in the session, create one.
                if (productList == null) {
                    productList = new HashMap<String, Product>();
                    session.setAttribute("productList", productList);
                }
                //add the product to the productlist
                productList.remove(productNumber);
                redirectAttributes.addFlashAttribute( "productList", productList.values());
            }
        return new ModelAndView("redirect:products", params);
    }
}
