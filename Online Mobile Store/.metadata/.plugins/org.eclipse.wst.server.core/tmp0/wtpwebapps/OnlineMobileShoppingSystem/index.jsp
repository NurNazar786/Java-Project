<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ page import ="java.sql.*"%>
    <%@ page import ="java.util.*"%>
    <%@ page import ="com.mobilestore.model.*"%>
    <%@ page import ="com.mobilestore.dao.*"%>
    <%@ page import ="com.mobilestore.utils.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mobile Store</title>

<%@ include file ="components/bootstrap_css_js.jsp"%>

</head>

<body>

  <%@ include file="components/navbar.jsp"%>

  <%
       String searchResult=(String)request.getAttribute("enteredText");
       String cat=request.getParameter("category"); 
       String filter=(String)request.getParameter("filter");
       
       ProductDaoImp pdao=new ProductDaoImp();
       BrandDaoImp cdao1=new BrandDaoImp();
       List<Product> l1=null;
       List<Brand> cl1=cdao1.getAllBrand();        

       if(cat==null) 
       {	
	     cat="0";
	
	    if(cat.trim().equals("0"))  {
     	  l1 =pdao.getAllProduct();
          }
        }

       else 
       {
    	   
	   if(cat.equals("0"))  {
		 l1 =pdao.getAllProduct();
	   }
	 
	   else {
	
       int categoryId=Integer.parseInt(cat.trim());
       l1=pdao.getProductByBrand(categoryId);	 
       }    
       }
       
  %>


  
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel" style="margin-top:70px;">
     
     <ol class="carousel-indicators">
        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
     </ol>
  
     <div class="carousel-inner" style="width:100%;height:180px;">
     
        <div class="carousel-item active">
        <img class="d-block w-100" src="images/banner1.png" alt="First slide">
        </div>
        
        <div class="carousel-item">
        <img class="d-block w-100" src="images/banner2.png" alt="Second slide">
        </div>
        
        <div class="carousel-item">
        <img class="d-block w-100" src="images/banner3.png" alt="Third slide">
        </div>
        
     </div>
     
	  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
	    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
	    <span class="sr-only">Previous</span>
	  </a>
	  
	  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
	    <span class="carousel-control-next-icon" aria-hidden="true"></span>
	    <span class="sr-only">Next</span>
	  </a>
	  
	</div>

  <div class="text-center">  <%@ include file="components/message.jsp"%> </div>
    
    
<!-- ****************************************** -->
<div class="container-fluid">  
<div class="row justify-content-center">

<!-- show products --> 
    
   <%
    if(searchResult!=null)
       {
    	 List<Product> listOfProductFromSearch=new ProductDaoImp().getSearchedProduct(searchResult);
   %>
     
     <div class="col-md-12 bg-white">
     <div class="row mt-4">
                     
            <%
            String available="Available";
                String stock="Out Of Stock!!!"; 
                for(Product p:listOfProductFromSearch)
                {
            %>
           <div class="col-md-3 mb-4">
            <div class="card">
                 
              <a href="product.jsp?productId=<%=p.getProductId()%>"> 
              <img src="productImages/<%=p.getProductPhoto() %>" style="height:310px;max-width:100%;width:auto;" class="card-img-top rounded mx-auto d-block m-2" alt="img">
              </a> 
            
                <div class="card-footer" style="background:#bdf4ef;">
                <h4 class="card-title" style="text-color:black; white-space:nowrap;overflow:hidden;text-overflow-ellipsis;"><a href="product.jsp?productId=<%=p.getProductId()%>" style="color:black;"> <%=p.getProductTitle() %></a></h4>

                    <h5>
                     <span><b>&#8377;<%=Helper.getProductSellingPrice(p.getProductPrice(), p.getProductDiscount()) %>/-</b></span>
                     <span class="ml-2" style="color:red"><s>&#8377;<%=p.getProductPrice()%></s></span>
                     <span class="ml-2" style="color:green"><%=p.getProductDiscount() %>&#37 off</span>
                    </h5>
              
                    <h5 class="card-title"><%if(p.getProductQuantity()<1){ %><span style="color:red;"><%=stock%></span><%} else{%><span style="color:green;"><%=available%></span><% } %></h5>
                   
                </div>
                
            </div>  
            </div>
            
        <%  }  %>
    </div>
    </div>
            
         
     <% } 
     else if(filter!=null)
         {
    	   int proRange=Integer.parseInt(filter);
    	   List<Product> listOfProductFromSearch=new ProductDaoImp().getSearchedProduct(proRange);
      %>
        
      <div class="col-md-12 bg-white">
      <div class="row mt-4">
                     
       <%
           String available="Available"; 
               String stock="Out Of Stock!!!"; 
               for(Product p:listOfProductFromSearch)
               {
           %>
           <div class="col-md-3 mb-4">
           <div class="card">
                
              <a href="product.jsp?productId=<%=p.getProductId()%>"> 
             <img src="productImages/<%=p.getProductPhoto() %>" style="height:310px;max-width:100%;width:auto;" class="card-img-top rounded mx-auto d-block m-2" alt="img">
             </a> 
           
               <div class="card-footer" style="background:#bdf4ef;">
               <h4 class="card-title" style="text-color:black; white-space:nowrap;overflow:hidden;text-overflow-ellipsis;"><a href="product.jsp?productId=<%=p.getProductId()%>" style="color:black;"> <%=p.getProductTitle() %></a></h4>

                   <h5>
                    <span><b>&#8377;<%=Helper.getProductSellingPrice(p.getProductPrice(), p.getProductDiscount()) %>/-</b></span>
                    <span class="ml-2" style="color:red"><s>&#8377;<%=p.getProductPrice()%></s></span>
                    <span class="ml-2" style="color:green"><%=p.getProductDiscount() %>&#37 off</span>
                   </h5>
                   
                   <h5 class="card-title"><%if(p.getProductQuantity()<1){ %><span style="color:red;"><%=stock%></span><%} else{%><span style="color:green;"><%=available%></span><% } %></h5>
                  
               </div>
               
           </div>
           </div>
           
         
           <%  }  %>
                   
     </div>    
     </div>
     <%
       }
       else
       {
     %> 
         
       <div class="col-md-12 bg-white">
       <div class="row mt-4">
                     
           <%
               String available="Available"; 
               String stock="Out Of Stock!!!"; 
               for(Product p:l1)
               {
           %>
            <div class="col-md-3 mb-4">
            <div class="card">
                  
               <a href="product.jsp?productId=<%=p.getProductId()%>"> <img src="productImages/<%=p.getProductPhoto() %>" style="height:310px;max-width:100%;width:auto;" class="card-img-top rounded mx-auto d-block m-2" alt="img"></a> 
             
                 <div class="card-footer" style="background:#bdf4ef;">
                 <h4 class="card-title" style="text-color:black; white-space:nowrap;overflow:hidden;text-overflow-ellipsis;"><a href="product.jsp?productId=<%=p.getProductId()%>" style="color:black;"> <%=p.getProductTitle() %></a></h4>
 
                     <h5>
                      <span><b>&#8377;<%=Helper.getProductSellingPrice(p.getProductPrice(), p.getProductDiscount()) %>/-</b></span>
                      <span class="ml-2" style="color:red"><s>&#8377;<%=p.getProductPrice()%></s></span>
                      <span class="ml-2" style="color:green"><%=p.getProductDiscount() %>&#37 off</span>
                     </h5>
                     
                     <h5 class="card-title"><%if(p.getProductQuantity()<1){ %><span style="color:red;"><%=stock%></span><%} else{%><span style="color:green;"><%=available%></span><% } %></h5>
                    
                 </div>
                             
               </div>   
               </div>          
                       
              <%   }   %>
      </div>
      </div>
          
     <%
       }
     %>
         
      <!-- ******* -->
         
 </div>
 </div>


<%@ include file="components/footer.jsp"%>

</body>


