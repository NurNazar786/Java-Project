package com.mobilestore.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobilestore.dao.CartDaoImp;
import com.mobilestore.model.Cart;
import com.mobilestore.paypal.PaymentServices;
import com.paypal.base.rest.PayPalRESTException;

/**
 * Servlet implementation class AuthorizePaymentServlet
 */
@WebServlet("/authorize_payment")
public class AuthorizePaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AuthorizePaymentServlet() {
       
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String userId=(String)request.getParameter("userId");
    	System.out.println("User Id : "+userId);
    	List<Cart> listOfproductInCart=new CartDaoImp().getCartByUserId(Integer.parseInt(userId));
 
        try {
            PaymentServices paymentServices = new PaymentServices();
            String approvalLink = paymentServices.authorizePayment(listOfproductInCart);
 
            response.sendRedirect(approvalLink);
             
        } catch (PayPalRESTException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            ex.printStackTrace();
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
		
	}

}
