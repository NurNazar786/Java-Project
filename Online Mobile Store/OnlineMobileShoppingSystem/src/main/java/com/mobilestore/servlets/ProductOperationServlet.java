package com.mobilestore.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.mobilestore.dao.BrandDaoImp;
import com.mobilestore.dao.ProductDaoImp;
import com.mobilestore.model.Brand;
import com.mobilestore.model.Product;

/**
 * Servlet implementation class ProductOperationServlet
 */


@WebServlet("/ProductOperation")
@MultipartConfig
public class ProductOperationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ProductOperationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		String action=request.getParameter("action");
		HttpSession session=request.getSession();
		
		if(action.equals("delete"))
		{
			int productId=Integer.parseInt(request.getParameter("productId"));
			Product product=new ProductDaoImp().getProductById(productId);
			String productImage=product.getProductPhoto();
			
			
			//File f=new File("C:\\Users\\Vostro.MURADALIMJ\\Desktop\\watch-store-payment\\watch-store\\src\\main\\webapp\\productImages\\"+productImage);
			
			File f=new File("D:\\Project EE\\online-watch-shop-master\\online-watch-shop-master\\src\\main\\webapp\\productImages\\"+productImage);
			
			//String uploadPath="D:\\Project EE\\online-watch-shop-master\\online-watch-shop-master\\src\\main\\webapp\\userProfilePic\\"+fileName;
			
			  f.delete();
			 
			
			boolean flag=false;
			
			flag=new ProductDaoImp().deleteProductById(productId);
			if(flag==true)
			{
				session.setAttribute("message","Product Deleted Successfully..!");
		        response.sendRedirect("index.jsp"); 	
			}
			
			else
			{
				session.setAttribute("message","Failed to Delete Product..!");
		        response.sendRedirect("index.jsp"); 
			}
			
		}
		
		else if(action.equals("deleteCategory"))
		{
			int categoryId=Integer.parseInt(request.getParameter("categoryId"));
			int totalProduct=new ProductDaoImp().getTotalProductOfCategoryById(categoryId);
			if(totalProduct>0)
			{
			    List<Product> product=new ProductDaoImp().getProductByBrand(categoryId);
			    for(Product p:product)
			    {
			    	  //File f=new File("C:\\Users\\Vostro.MURADALIMJ\\Desktop\\watch-store-payment\\watch-store\\src\\main\\webapp\\productImages\\"+p.getProductPhoto());
			    	  
			    	  File f=new File("E:\\My Project\\OnlineMobileShoppingSystem\\src\\main\\webapp\\productImages\\"+p.getProductPhoto());

					  f.delete();
					  
			    }
				boolean flag=new ProductDaoImp().deleteProductByBrand(categoryId);
				if(flag==true)
				{
					boolean f=new BrandDaoImp().deleteBrandById(categoryId);
					if(f==true)
					{
						session.setAttribute("message","Category Deleted Successfully..!");
				        response.sendRedirect("index.jsp"); 	
					}
					
					else
					{
						session.setAttribute("message","Failed to Delete Category..!");
				        response.sendRedirect("index.jsp"); 
					}
				}
				
				else
				{
					session.setAttribute("message","Failed to Delete Category..!");
			        response.sendRedirect("index.jsp"); 
				}
			}
			
			
			
			else
			{
				
				boolean f=new BrandDaoImp().deleteBrandById(categoryId);
				if(f==true)
				{
					
					session.setAttribute("message","Category Deleted Successfully..!");
			        response.sendRedirect("index.jsp"); 	
				}
				
				else
				{
					session.setAttribute("message","Failed to Delete Category..!");
			        response.sendRedirect("index.jsp"); 
				} 
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String action=request.getParameter("action");
		HttpSession session=request.getSession();
		
		 if(action.equals("addProduct"))
		{
			
			String productTitle=request.getParameter("productTitle");
			String productDescription=request.getParameter("productDescription");
			int productPrice=Integer.parseInt(request.getParameter("productPrice"));
			int productQuantity=Integer.parseInt(request.getParameter("productQuantity"));
			int productDiscount=Integer.parseInt(request.getParameter("productDiscount"));
			int productCategoryId=Integer.parseInt(request.getParameter("productBrandId"));
		
			Part part=request.getPart("productPic");
			
			String fileName=part.getSubmittedFileName();
			
			   String uploadPath = "E:\\My Project\\OnlineMobileShoppingSystem\\src\\main\\webapp\\productImages\\"+fileName;
			//String uploadPath="D:\\Project EE\\online-watch-shop-master\\online-watch-shop-master\\src\\main\\webapp\\productImages\\"+fileName;
			
			
			try
			{
			FileOutputStream fos=new FileOutputStream(uploadPath);
			InputStream is=part.getInputStream();
			
			byte[] data=new byte[is.available()];
			is.read(data);
			fos.write(data);
			fos.close();
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			Product product=new Product(productPrice, productDiscount, productQuantity, productCategoryId, productTitle, productDescription, fileName);
		
			
			ProductDaoImp pdao=new ProductDaoImp();
			boolean flag=pdao.addProduct(product);
			
			if(flag==true)
		    {
		    	session.setAttribute("message","Product Added Successfully..!");
		        response.sendRedirect("admin.jsp");
		    }
		    
		   else
		    {
		    	session.setAttribute("message","Failed to add product..!");
		        response.sendRedirect("admin.jsp");
		    }	
			
	}
		
		else if(action.equals("addBrand"))
		{
		    String categoryTitle=request.getParameter("brandTitle");
		    String categoryDescription=request.getParameter("brandDescription");
		    
		    Brand brand=new Brand(categoryTitle,categoryDescription);
		    BrandDaoImp brandDaoImp=new BrandDaoImp();
		    boolean flag=brandDaoImp.addBrand(brand);
		    
		    if(flag==true)
		    {
		    	session.setAttribute("message","Brand Added Successfully..!");
		        response.sendRedirect("admin.jsp");
		    }
		    
		   else
		    {
		    	session.setAttribute("message","Failed to add Brand..!");
		        response.sendRedirect("admin.jsp");
		    }
		    
		}
		
		
		
		else if(action.equals("searchProduct"))
		{
			RequestDispatcher rd;
			String search=request.getParameter("search");
			request.setAttribute("enteredText",search);
			rd=request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);   	
		}
		
		
	
	
	
	}
}
