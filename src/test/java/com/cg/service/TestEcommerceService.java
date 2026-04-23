package com.cg.service;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.cg.dto.ProductDto;
import com.cg.entity.Product;
import com.cg.repo.ProductRepo;

@SpringBootTest
public class TestEcommerceService 
{
	@MockitoBean
	private ProductRepo repo;
	
	@Autowired
	private ProductService service;
	
	private Product product1,product2;
	private Optional<Product> opt1,opt2,opt3;
	@BeforeEach
	public void beforeEach()
	{
		product1 = new Product();
        product1.setProdId(1);
        product1.setProdName("Laptop");
        product1.setPrice(50000.0);
        product1.setProdDesc("Gaming Laptop");
        product1.setStock(10);
        product1.setProdImage("img1.jpg");

        product2 = new Product();
        product2.setProdId(2);
        product2.setProdName("Mobile");
        product2.setPrice(20000.0);
        product2.setProdDesc("Smartphone");
        product2.setStock(20);
        product2.setProdImage("img2.jpg");
        
        opt1= Optional.ofNullable(product1);
        opt2= Optional.ofNullable(product2);
        opt3= Optional.empty();
	}
	
	@Test
	public void testAddProduct()
	{
		ProductDto dto = new ProductDto();
        dto.setProdName("Laptop");
        dto.setPrice(50000.0);
        dto.setProdDesc("Gaming Laptop");
        dto.setStock(10);
        dto.setProdImage("img1.jpg");
        
        Mockito.when(repo.save(Mockito.any(Product.class))).thenReturn(product1);
        Integer result = service.addProduct(dto);

        assertEquals(1, result);

        Mockito.verify(repo).save(Mockito.any(Product.class));
	}
	@Test
	public void testFindByProductId()
	{
		Mockito.when(repo.findById(1)).thenReturn(opt1);
		//Mockito.when(repo.findById(2)).thenReturn(opt2);
		ProductDto result= service.findProductById(1);
		assertNotNull(result);
		assertEquals(opt1.get().getProdId(),result.getProdId());
		assertEquals("Laptop", result.getProdName());
		assertEquals(50000.0, result.getPrice());
		Mockito.verify(repo).findById(1);
	}
	@Test
	public void testFindById2()
	{
		 Mockito.when(repo.findById(99))
         .thenReturn(opt3);

		  assertThrows(com.cg.exception.NotFoundException.class,
		          () -> service.findProductById(99));
		
		  Mockito.verify(repo).findById(99);
		
	}
	
    @Test
    public void testGetAllProducts() {

        Mockito.when(repo.findAll())
               .thenReturn(Arrays.asList(product1, product2));

        List<ProductDto> result = service.getAllProducts();

        assertEquals(2, result.size());
        assertEquals("Laptop", result.get(0).getProdName());
        assertEquals("Mobile", result.get(1).getProdName());

        Mockito.verify(repo).findAll();
    }
}

