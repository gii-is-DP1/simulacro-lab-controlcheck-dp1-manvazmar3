package org.springframework.samples.petclinic.product;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
	private final String VIEW_PRODUCTS_CREATE_FORM = "products/createOrUpdateProductForm";
	private final String WELCOME = "welcome";
	private ProductService pservice;
	
	@Autowired
	public ProductController(ProductService service) {
		this.pservice = service;
	}
	
	@GetMapping("/create") 	
    public String initProduct(ModelMap map) {
    	map.addAttribute("product", new Product());
    	map.addAttribute("types", pservice.findAllProductTypes());
    	return VIEW_PRODUCTS_CREATE_FORM;
    }
    
    @PostMapping(path = "/create")
    public String createProduct(@Valid Product product, BindingResult br, ModelMap map){
        if(!br.hasErrors()){
        	pservice.save(product);
            map.addAttribute("message", "Product succesfully save");
        } else{
            map.addAttribute("product", product);
            map.addAttribute("types", pservice.findAllProductTypes());
            return VIEW_PRODUCTS_CREATE_FORM;
        }
        return WELCOME;
    }
}
