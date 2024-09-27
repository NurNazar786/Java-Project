package com.mobilestore.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mobilestore.dao.CartDaoImp;
import com.mobilestore.dao.ProductDaoImp;
import com.mobilestore.model.Cart;
import com.mobilestore.model.Product;
import com.mobilestore.model.User;
import com.mobilestore.utils.Helper;




public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CartServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		String action=request.getParameter("action");
		HttpSession session=request.getSession();
		
		if(action.equals("delete"))
		{
			int cartId=Integer.parseInt(request.getParameter("cartId"));
			boolean flag=false;
			
			flag=new CartDaoImp().deleteCartById(cartId);
			if(flag==true)
			{
				session.setAttribute("message","Product Deleted from Cart Successfully..!");
		        response.sendRedirect("index.jsp"); 	
			}
			
			else
			{
				session.setAttribute("message","Failed to Delete Product from Cart..!");
		        response.sendRedirect("index.jsp"); 
			}
			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		String action=request.getParameter("action");
		
		if(action.equals("addToCart"))
		{
			User user=(User)session.getAttribute("active-user");
			int userId=user.getUserId();
			int productId=Integer.parseInt(request.getParameter("productId"));
			int quantity=Integer.parseInt(request.getParameter("quantity"));
			
			if(quantity < 0) {
				session.setAttribute("message","Please enter valid quantity to Add product to cart");
		        response.sendRedirect("index.jsp");
			}
			
			else if(quantity < 1) {
				session.setAttribute("message","Please enter atleast one quantity to Add product to cart");
		        response.sendRedirect("index.jsp");
			}
			
			else
			{
			
			Product product=new ProductDaoImp().getProductById(productId);
			int productPrice=product.getProductPrice();
			int productDiscount=product.getProductDiscount();
			int price=Helper.getProductSellingPrice(productPrice, productDiscount);
			int totalPrice=quantity*price;
			int productActualQuantity=product.getProductQuantity();
			
			if(quantity>productActualQuantity)
			{
				session.setAttribute("message","Only "+productActualQuantity+" Item is Available Now :(");
		        response.sendRedirect("index.jsp");
			}
			
			else
			{
				
			boolean check=new CartDaoImp().alreadyAvailableInCart(productId, userId);
			if(check==false)
			{
				Cart cart=new Cart(userId,productId,quantity,totalPrice);
				
			    boolean flag=new CartDaoImp().addToCart(cart);
			    if(flag==true)
			    {
			    	session.setAttribute("message","Product Added to Cart Successfully..!");
			        response.sendRedirect("index.jsp");
			    }
			    
			   else
			    {
			    	session.setAttribute("message","Failed to add product to cart..!");
			        response.sendRedirect("index.jsp");
			    }  
			}
			
			else
			{
				
				int finalQuantity=new CartDaoImp().getPreviousQuantityFromCart(productId, userId)+quantity;
				
				int pricee=finalQuantity*Helper.getProductSellingPrice(productPrice, productDiscount);
				Cart cart=new Cart(userId,productId,finalQuantity,pricee);
				
				boolean flag=new CartDaoImp().updateCart(cart);
				
				if(flag==true)
			    {
			    	session.setAttribute("message","Product Added to Cart Successfully..!");
			        response.sendRedirect("index.jsp");
			    }
			    
			    else
			    {
			    	session.setAttribute("message","Failed to add product to cart..!");
			        response.sendRedirect("index.jsp");
			    } 
			}
			
	   	  } 
			
         }
		}
		
	}

}
