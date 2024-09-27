package com.mobilestore.dao;
import java.util.List;

import com.mobilestore.model.Product;


public interface ProductDao
{
   boolean addProduct(Product product);
   
   boolean updateProduct(Product product);
   
   List<Product> getAllProduct();
   
   List<Product> getProductByBrand(int brandId);
   
   
   List<Product> getSearchedProduct(String productName);
   
   List<Product> getSearchedProduct(int productRange);
   
   Product getProductById(int productId);
   
   int getTotalProduct();
   
   boolean deleteProductById(int productId);
   
   boolean deleteProductByBrand(int brandId);
   
   int getQuantityByProductId(int productId);
   
   void updateQuantityAfterOrder(int productId,int productQuantity);
   
   int getTotalProductOfCategoryById(int categoryId);
   
   
   List<Product> getProductByBrandAndFor(String productFor,int watchId); 
}




