package org.springframework.samples.petclinic.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
	private ProductRepository repo;
	
	@Autowired
	public ProductService(ProductRepository repo) {
		this.repo = repo;
	}
	
	public List<ProductType> findAllProductTypes(){
		return repo.findAllProductTypes();
	}
	
    public List<Product> getAllProducts(){
        return repo.findAll();
    }

    public List<Product> getProductsCheaperThan(double price) {
        return repo.findByPriceLessThan(price);
    }

    public ProductType getProductType(String typeName) {
        return repo.findTypeByname(typeName);
    }

    public Product save(Product p){
        return repo.save(p);       
    }
    
}
