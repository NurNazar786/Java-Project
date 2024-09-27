
package com.mobilestore.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mobilestore.db_conn.ConnectionProvider;
import com.mobilestore.model.Product;


public class ProductDaoImp implements ProductDao
{

	@Override
	public boolean addProduct(Product product)
	{
		int row=0;
		   Connection con=ConnectionProvider.getConnection();
    	String query="insert into Product(productTitle,productDescription,productPhoto,productPrice,productDiscount,productQuantity,brandId) values(?,?,?,?,?,?,?)";
    	try
    	{
    	    PreparedStatement stmt= con.prepareStatement(query);
    	    stmt.setString(1,product.getProductTitle());
    	    stmt.setString(2,product.getProductDescription());
    	    stmt.setString(3,product.getProductPhoto());
    	    stmt.setInt(4,product.getProductPrice());
    	    stmt.setInt(5,product.getProductDiscount());
    	    stmt.setInt(6,product.getProductQuantity());
    	    stmt.setInt(7,product.getBrandId());
    	    
    	 
    	    row=stmt.executeUpdate();
    	}
    	
    	catch(SQLException e)
    	{
    		System.out.println(e);
    	}
    	
    	if(row>0)
    	   return true;
    	else
    	   return false;
	}
	
	@Override
	public List<Product> getAllProduct()
	{
		   Connection con=ConnectionProvider.getConnection();
		String query="select * from product";
		ResultSet rs;
		Statement stmt;
		Product product;
		List<Product> li=new ArrayList<>();
		
		try
    	{
    	    stmt=con.createStatement();
    	    rs=stmt.executeQuery(query);
    	    
    	    while(rs.next())
    	    {
    	        product=new Product(rs.getInt("productId"), rs.getInt("productPrice") , rs.getInt("productDiscount") ,rs.getInt("productQuantity") , rs.getInt("brandId") ,
    	    			rs.getString("productTitle"), rs.getString("productDescription") , rs.getString("productPhoto") ); 
    	       
    	        li.add(product);
    	    }
    	}
    	
    	catch(SQLException e)
    	{
    		System.out.println(e);
    	}
    	
    	return li;
	}

	@Override
	public List<Product> getProductByBrand(int brandId)
	{
		   Connection con=ConnectionProvider.getConnection();
		String query="select * from product where brandId="+brandId;
		ResultSet rs;
		Statement stmt;
		Product product;
		List<Product> li=new ArrayList<>();
		
		try
    	{
    	    stmt=con.createStatement();
    	    rs=stmt.executeQuery(query);
    	    
    	    while(rs.next())
    	    {
    	        product=new Product(rs.getInt("productId"), rs.getInt("productPrice") , rs.getInt("productDiscount") ,rs.getInt("productQuantity") , rs.getInt("brandId") ,
    	    			rs.getString("productTitle"), rs.getString("productDescription") , rs.getString("productPhoto") ); 
    	       
    	        li.add(product);
    	    }
    	}
    	
    	catch(SQLException e)
    	{
    		System.out.println(e);
    	}
    	
    	return li;
	}
	
	

	@Override
	public List<Product> getSearchedProduct(String productName)
	{
		   Connection con=ConnectionProvider.getConnection();
		ResultSet rs;
		Statement stmt;
		Product product;
		List<Product> li=new ArrayList<>();
		String query="select * from product where productTitle LIKE '%"+productName+"%' ";
		
		try
    	{
    	    stmt=con.createStatement();
    	    rs=stmt.executeQuery(query);
    	    
    	    while(rs.next())
    	    {
    	        product=new Product(rs.getInt("productId"), rs.getInt("productPrice") , rs.getInt("productDiscount") ,rs.getInt("productQuantity") , rs.getInt("brandId"),
    	                         rs.getString("productTitle"), rs.getString("productDescription") , rs.getString("productPhoto") ); 
    	       
    	        li.add(product);
    	    }
    	}
    	
    	catch(SQLException e)
    	{
    		System.out.println(e);
    	}
    	
    	return li;
		
	}

	@Override
	public Product getProductById(int productId) 
	{
		   Connection con=ConnectionProvider.getConnection();
		ResultSet rs;
		Statement stmt;
		Product product = null;
		
		String query="select * from product where productId="+productId;
		try
    	{
    	    stmt=con.createStatement();
    	    rs=stmt.executeQuery(query);
    	    
    	    while(rs.next())
    	    {
    	        product=new Product(rs.getInt("productId"), rs.getInt("productPrice") , rs.getInt("productDiscount") ,rs.getInt("productQuantity") , rs.getInt("brandId"),
    	                         rs.getString("productTitle"), rs.getString("productDescription") , rs.getString("productPhoto") ); 
    	       
    	    }
    	}
    	
    	catch(SQLException e)
    	{
    		System.out.println(e);
    	}
    	
    	return product;
	}

	@Override
	public int getTotalProduct()
	{
		   int totalProduct=0;
		   ResultSet rs;
	 	   
		   Connection con=ConnectionProvider.getConnection();
	        String query="select count(productId) from product";
	        
	 	   try 
	 	   {
	 		Statement stmt=con.createStatement();
	 	    rs=stmt.executeQuery(query);
	 	    while(rs.next())
	 		{
	 	    	  totalProduct=rs.getInt("count(productId)");
	 		}
	 	   }
	 	   catch (SQLException e) 
	 	   {
	 		e.printStackTrace();
	 	   }
	 	  
		   return totalProduct;  
	}

	@Override
	public boolean deleteProductById(int productId)
	{
		int row=0;
		   Connection con=ConnectionProvider.getConnection();
    	String query="delete from product where productId=?";
    	try
    	{
    	    PreparedStatement stmt= con.prepareStatement(query);
    	    stmt.setInt(1,productId);
    	    row=stmt.executeUpdate();
    	}
    	
    	catch(SQLException e)
    	{
    		System.out.println(e);
    	}
    	
    	if(row>0)
    	   return true;
    	else
    	   return false;
	}

	@Override
	public boolean updateProduct(Product product)
	{
		int row=0;
		   Connection con=ConnectionProvider.getConnection();
    	String query="update product set productTitle=?,productDescription=?,productPhoto=?,productPrice=?, productDiscount=?,productQuantity=? where productId=?";
    	try
    	{
    	    PreparedStatement stmt= con.prepareStatement(query);
    	    stmt.setString(1,product.getProductTitle());
    	    stmt.setString(2,product.getProductDescription());
    	    stmt.setString(3,product.getProductPhoto());
    	    stmt.setInt(4,product.getProductPrice());
    	    stmt.setInt(5,product.getProductDiscount());
    	    stmt.setInt(6,product.getProductQuantity());
    	    stmt.setInt(7,product.getProductId());
    	    row=stmt.executeUpdate();
    	}
    	
    	catch(SQLException e)
    	{
    		System.out.println(e);
    	}
    	
    	if(row>0)
    	   return true;
    	else
    	   return false;
	}

	@Override
	public int getQuantityByProductId(int productId)
	{
		   int quantity=0;
		   ResultSet rs;
	 	   
		   Connection con=ConnectionProvider.getConnection();
	        String query="select quantity from product where productId="+productId;
	        
	 	   try 
	 	   {
	 		Statement stmt=con.createStatement();
	 	    rs=stmt.executeQuery(query);
	 	    while(rs.next())
	 		{
	 	    	  quantity=rs.getInt("quantity");
	 		}
	 	   }
	 	   catch (SQLException e) 
	 	   {
	 		e.printStackTrace();
	 	   }
	 	  
		   return quantity;  
	}

	@Override
	public void updateQuantityAfterOrder(int productId,int productQuantity)
	{
		
		   Connection con=ConnectionProvider.getConnection();
    	String query="update product set productQuantity=? where productId=?";
    	try
    	{
    	    PreparedStatement stmt= con.prepareStatement(query);
    	    stmt.setInt(1,productQuantity);
    	    stmt.setInt(2, productId);
    	    stmt.executeUpdate();
    	}
    	
    	catch(SQLException e)
    	{
    		System.out.println(e);
    	}
    	
	}

	@Override
	public boolean deleteProductByBrand(int categoryId)
	{
		int row=0;
		   Connection con=ConnectionProvider.getConnection();
    	String query="delete from product where brandId=?";
    	try
    	{
    	    PreparedStatement stmt= con.prepareStatement(query);
    	    stmt.setInt(1,categoryId);
    	    row=stmt.executeUpdate();
    	}
    	
    	catch(SQLException e)
    	{
    		System.out.println(e);
    	}
    	
    	if(row>0)
    	   return true;
    	else
    	   return false;
	}
	
	

	@Override
	public int getTotalProductOfCategoryById(int categoryId)  // Category=brand
	{	
		   int totalProduct=0;
		   ResultSet rs;
	 	   
		   Connection con=ConnectionProvider.getConnection();
	        String query="select count(productId) from product where brandId="+categoryId;
	        
	 	   try 
	 	   {
	 		Statement stmt=con.createStatement();
	 	    rs=stmt.executeQuery(query);
	 	    while(rs.next())
	 		{
	 	    	  totalProduct=rs.getInt("count(productId)");
	 		}
	 	   }
	 	   catch (SQLException e) 
	 	   {
	 		e.printStackTrace();
	 	   }
	 	  
		   return totalProduct;  	
	}
	
	
	@Override
	public List<Product> getSearchedProduct(int productRange)
	{
		   Connection con=ConnectionProvider.getConnection();
		ResultSet rs;
		Statement stmt;
		Product product;
		List<Product> li=new ArrayList<>();
		String query=null;
		
		if(productRange==10000)
		{
		    query="select * from product where productPrice<="+10000;
		}
		
		else if(productRange==20000)
		{
		   query="select * from product where productPrice<="+20000;
		}
		
		else if(productRange==30000)
		{
		  query="select * from product where productPrice<="+30000;
		}
		else if(productRange==50000)
		{
		  query="select * from product where productPrice<="+50000;
		}
		
		else if(productRange==99900)
		{
		  query="select * from product where productPrice>="+99900;
		}
		
		try
    	{
    	    stmt=con.createStatement();
    	    rs=stmt.executeQuery(query);
    	    
    	    while(rs.next())
    	    {
    	        product=new Product(rs.getInt("productId"), rs.getInt("productPrice") , rs.getInt("productDiscount") ,rs.getInt("productQuantity") , rs.getInt("brandId"),
    	                         rs.getString("productTitle"), rs.getString("productDescription") , rs.getString("productPhoto") ); 
    	       
    	        li.add(product);
    	    }
    	}
    	
    	catch(SQLException e)
    	{
    		System.out.println(e);
    	}
    	
    	return li;
	}

	@Override
	public List<Product> getProductByBrandAndFor(String productFor, int watchId)
	{
		   Connection con=ConnectionProvider.getConnection();
		ResultSet rs;
		Statement stmt;
		Product product = null;
		List<Product> li=new ArrayList<>();
		
		String query="select * from product where productFor='"+productFor+"' and watchId="+watchId;
		try
    	{
    	    stmt=con.createStatement();
    	    rs=stmt.executeQuery(query);
    	    
    	    while(rs.next())
    	    {
    	        product=new Product(rs.getInt("productId"), rs.getInt("productPrice") , rs.getInt("productDiscount") ,rs.getInt("productQuantity") , rs.getInt("brandId"),
    	                         rs.getString("productTitle"), rs.getString("productDescription") , rs.getString("productPhoto") ); 
    	       
    	        li.add(product);
    	    }
    	}
    	
    	catch(SQLException e)
    	{
    		System.out.println(e);
    	}
    	
    	return li;
	}



}
