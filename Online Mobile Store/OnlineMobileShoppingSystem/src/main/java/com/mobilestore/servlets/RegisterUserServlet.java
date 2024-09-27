package com.mobilestore.servlets;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.mobilestore.dao.UserDaoImp;
import com.mobilestore.model.User;


@WebServlet("/register")
@MultipartConfig
public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//doGet(request, response);
		
		HttpSession session = request.getSession();
		
		   String name    = request.getParameter("name");
		   String email   = request.getParameter("email");
		   String pass    = request.getParameter("pass");
		   String mobile  = request.getParameter("mobile");
		   String address = request.getParameter("useraddress");
		   Part part      = request.getPart("profilePic");
		   
		   String fileName = part.getSubmittedFileName();
		   String profileUploadPath = "E:\\My Project\\OnlineMobileShoppingSystem\\src\\main\\webapp\\userProfilePic\\"+fileName;

		   try{
			   
			FileOutputStream fos=new FileOutputStream(profileUploadPath);
			InputStream is=part.getInputStream();
			
			byte[] data=new byte[is.available()];
			is.read(data);
			fos.write(data);
			fos.close();
			}
			
			catch(Exception e)  {   e.printStackTrace();   }
		   
		   User user = new User(name,email,pass,mobile,fileName,address,"user");
		   
		   UserDaoImp userDaoImp = new UserDaoImp();
		   boolean isExistUser = userDaoImp.isAlreadyUser(email);
		   
		   if(isExistUser==true) {
			  session.setAttribute("message", "User is Already registered with this Email..!");
			  response.sendRedirect("register.jsp");
		   }
		   
		   else{
			    boolean flag = userDaoImp.addUser(user);
			   
			    if(flag == true) {
			    	session.setAttribute("message", "Registeration Successfull..!!!");
					 response.sendRedirect("register.jsp");
			    }
			    else{
			    	session.setAttribute("message","Registration Failed..!!!");
			    	response.sendRedirect("register.jsp");
			    }
			 
		   }
		   
		
	 	
	}

}
