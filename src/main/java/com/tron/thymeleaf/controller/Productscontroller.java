package com.tron.thymeleaf.controller;

import java.io.InputStream;
import java.nio.file.*;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tron.thymeleaf.models.Product;
import com.tron.thymeleaf.models.ProductDto;
import com.tron.thymeleaf.service.Productrepo;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class Productscontroller {
   
	@Autowired
   private Productrepo repo;
	
	@GetMapping({"", "/"})
	public String showproductlist(Model m)
	{
		List<Product> products=repo.findAll(Sort.by(Sort.Direction.ASC, "id"));
		m.addAttribute("products", products);
		return "products/index";
	}
	
	@GetMapping("/createpage")
	public String showcreatepage(Model m)
	{
		ProductDto productDto=new ProductDto();
		m.addAttribute("productDto", productDto);
		return "products/createproduct";
	}
    
	@PostMapping("/create")
	public String createproduct(@Valid @ModelAttribute ProductDto productDto,BindingResult br)
	{
		if(productDto.getImageRile().isEmpty())
		{
			br.addError(new FieldError("productDto", "imageRile", "the image file is required"));
		}
		if(br.hasErrors())
		{
			return "products/createproduct";
		}     
		MultipartFile image = productDto.getImageRile();
		Date createdAt = new Date();
		String storageFileName = createdAt.getTime() + image.getOriginalFilename ();
		try {
		String uploadDir = "src/main/resources/static/images/";
		Path uploadPath = Paths.get(uploadDir);
		if (!Files.exists(uploadPath)) {
		Files.createDirectories (uploadPath);
		}
		try (InputStream inputStream = image.getInputStream()) {
		Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
		}
		} catch (Exception ex) {
		System.out.println("Exception: "+ ex.getMessage());
		}
			
		Product product = new Product ();
		product.setName (productDto.getName());
		product.setBrand (productDto.getBrand());
		product.setCategory (productDto.getCategory());
		product.setPrice (productDto.getPrice());
		product.setDescription (productDto.getDescription());
		product.setCreatedAt (createdAt);
		product.setImageFileName (storageFileName);
		
		repo.save(product);
		return "redirect:/products";
	}

	@GetMapping("/edit")
	public String editproduct(Model m, @RequestParam("id") int id)
	{
		
		try {
			Product product = repo.findById(id).get(); 
			m.addAttribute("product", product);
			
			ProductDto productDto = new ProductDto(); 
			productDto.setName (product.getName()); 
			productDto.setBrand (product.getBrand()); 
			productDto.setCategory (product.getCategory()); 
			productDto.setPrice (product.getPrice()); 
			productDto.setDescription (product.getDescription());
			
			m.addAttribute("productDto", productDto);
			}
			catch (Exception ex) {
			System.out.println("Exception:" + ex.getMessage());
			return "redirect:/products";
			}
		return "products/editproduct";
	}
	
	@PostMapping("/edit")
	public String updateProduct(Model model, @ RequestParam int id,@Valid @ModelAttribute ProductDto productDto,
			BindingResult result)          	
	 {
	try {
	Product product = repo.findById(id).get(); 
	model.addAttribute("product", product);
	
	if (result.hasErrors()) {
	return "products/EditProduct";
	}
	System.out.println("      "+productDto.getImageRile());
	System.out.println(product.getImageFileName());
	//chenge imge
	if (!productDto.getImageRile().isEmpty()) {
		// delete old image.
		String uploadDir = "public/images/";
		Path oldImagePath = Paths.get(uploadDir + product.getImageFileName());
		try {
		Files.delete(oldImagePath);
		}
		catch (Exception ex) {
		System.out.println("Exception:" + ex.getMessage());
		}
		// save new image file
		MultipartFile image= productDto.getImageRile();
		Date createdAt = new Date();
		String storageFileName = createdAt.getTime() + " " + image.getOriginalFilename();
		try (InputStream inputStream = image.getInputStream()) {
		Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
		StandardCopyOption.REPLACE_EXISTING);
		}
		product.setImageFileName (storageFileName);
		}

	    product.setName (productDto.getName());
	    product.setBrand (productDto.getBrand());
	    product.setCategory (productDto.getCategory());
	    product.setPrice (productDto.getPrice());
	    product.setDescription (productDto.getDescription());
	    repo.save(product);
	    }
	    catch (Exception ex) {
	    System.out.println("Exception:" + ex.getMessage());
	    }
	
	    return "redirect:/products";
     }
	
	@GetMapping("/delete")
	public String deleteProduct(@RequestParam int id) 
	 {
	 try {
	 Product product = repo.findById(id).get();
	
	 // delete product image
	 Path imagePath = Paths.get("src/main/resources/static/images/"+ product.getImageFileName());
	       try {
	       Files.delete(imagePath);
	       } 
	       catch (Exception ex) {
	       System.out.println("Exception: " + ex.getMessage());
	       }
	       //deleteproduct
	       repo.delete(product);
	 }
    catch (Exception ex) {
    System.out.println("Exception:"+ ex.getMessage());
	}
	return "redirect:/products";
	}
}
