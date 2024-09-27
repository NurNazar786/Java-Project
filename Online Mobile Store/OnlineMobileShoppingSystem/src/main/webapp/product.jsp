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
<meta charset="UTF-8">
<title><%=new ProductDaoImp().getProductById(Integer.parseInt(request.getParameter("productId"))).getProductTitle() %></title>
<%@ include file ="components/bootstrap_css_js.jsp" %>

<script type="text/javascript">
      function checkUser()
      {
    	  <%
    	      User u=(User)session.getAttribute("active-user"); 
    	      if(u!=null)
    	      {
    	    	  if(u.getUserType().equals("user"))
    	    	  {
    	  %>
    	             return  true;
    	          <%
    	    	  }
    	    	  
    	    	  else
    	    	  {	  
    	    	  %>
    	    	  alert("Please login as customer to buy the product.");
    	    	  return false;
    	    	  <%
    	    	  }
    	    	  
    	      }
    	      else
    	      {
    	  %>
    	    	  alert("Please login to buy the product.");
    	    	  return false;
    	  <%
    	      }
    	  %>
      }
</script>
</head>
<body>

<%@ include file="components/navbar.jsp"%>
<%@ include file="components/message.jsp"%>

<%
     int productId=Integer.parseInt((String)request.getParameter("productId"));
     Product product=new ProductDaoImp().getProductById(productId);
     int catId=new BrandDaoImp().getBrandIdByProductId(productId);
     
     int brandId = product.getBrandId();
     List<Product> relatedProduct = new ProductDaoImp().getProductByBrand(brandId);
     
     String stock="Out Of Stock!!!"; 
     String available="Available"; 
%>

<div class="container-fluid"  style="margin-top: 80px;">
   <div class="row">
   
    <div class="col-sm-4.7 mt-4 admin" style="margin-left: 50px;">
              <div class="card">
                   <img src="productImages/<%=product.getProductPhoto() %>" style="height:517px;max-width:100%;width:auto;" class="card-img-top rounded mx-auto d-block m-2" alt="img">
              </div>
        </div>
        
        
        
        
        <div class="col-sm-7 mt-4 admin" style="margin-left: 30px;">
              <div class="card">
                    
                        <div class="card-body text-left">
                          
                        <h2 style="padding-left:8px;"><%=product.getProductTitle() %></h2>
                        <h3 class="card-text ml-2">Brand: <%=new BrandDaoImp().getBrandNameById(product.getBrandId()) %></h3>
                             <h3 class="card-text ml-2 text-muted" style="margin-top:20px;">Description :</h3>
                                 <h4 class="card-text ml-2 text-muted"><%=product.getProductDescription() %></h4> 
                        </div>
                        
                        
                        
                        <div class="card-footer text-left" style="margin-top:10px;">
                        
                              <div class="container" style="float:left;border:1px solid black; width:160px; height:42px;background-color:green;border-top-right-radius:50px;">
                                   <h4 class="text-left" style="padding:5px 10px 5px 0px;">Hot Deal</h4>
                              </div><br><br>
                        
                                 <p style="font-size:35px">
                                 
                                  <span class="ml-2" style="font-size:30px;color:green">-<%=product.getProductDiscount() %>&#37 off</span>
                                  <span class="ml-2" style="font-size:30px;color:grey"><s>&#8377;<%=product.getProductPrice()%></s></span>
                                  <span class="ml-2"><b>&#8377;<%=Helper.getProductSellingPrice(product.getProductPrice(), product.getProductDiscount()) %>/-</span>
                                  
                                 </p>
                                 
                                  <h5 class="card-title" style="font-size:30px;padding-left:8px;"><%if(product.getProductQuantity()<1){ %><span style="color:red;"><%=stock%></span><%} else{%><span style="color:green;"><%=available%></span><% } %></h5>
                                 <hr>
                                 
                                
                                
                                 
                               <%
                                   if(u==null)
                                   {
                               %>
                               
                               <form class="form-inline" onclick="return checkUser()">
                                  
                                  <div class="form-group mx-sm-3 mb-2">
                                      <input type="number" class="form-control" id="quan" name="quantity" placeholder="Quantity" required>
                                  </div>
                                  <button type="submit" class="btn custom-bg text-white mb-2">Add To Cart</button>
                               </form>
                              
                              <%
                                   }
                               
                                   else
                                   {
                              %> 
                                       <%
                                           if(u.getUserType().equals("admin"))
                                           {
                                       %>
                                      <a href="ProductOperationServlet?productId=<%=request.getParameter("productId")%>&action=delete"><button type="button" class="btn btn-danger">Delete Product</button></a>
                                      <button type="button" class="btn custom-bg text-light ml-5" data-toggle="modal" data-target="#update-product-modal">Update Product</button>
                                      
                                      <%
                                           }
                                           
                                           else
                                           {
                                      %>
                                               <form class="form-inline" onclick="return checkUser()" action="CartServlet" method="post">
                                                 <input type="hidden" name="action" value="addToCart">
                                                 <input type="hidden" name="productId" value="<%=productId%>">
                                                 
                                                 <div class="form-group mx-sm-3 mb-2">
                                                   <input type="number" class="form-control" id="quan" name="quantity" placeholder="Quantity" value=1>
                                                 </div>
                                               <input type="submit" class="btn custom-bg text-white mb-2" value="Add To Cart">
                                               
                                              </form>
                                      <%
                                           }
                                      %>
                              <%
                                   }
                              %>
                        </div>
                   </div>
              </div>
        
</div>


 <div class="row" style="margin-top:50px;">
         <div class="col-md-2" >
             
         </div>    
   
         <div class="col-md-12">
               <div class="mt-4"><span style="font-size:35px;"><b> BRAND RELATED PRODUCTS ....</b></span></div>
         </div>
   </div>
   
   <!-- ****************************************** -->

   <!-- show products -->
   
    <div class="row justify-content-center" style="margin-top:30px;">
         
     <div class="col-md-12 bg-white">
        <div class="row mt-4">
                    
                     
                         <%
                        
                             for(Product p:relatedProduct)
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
                         
                       
                         <%
                             
                             }
                         %>
                     </div>
                 </div>
                  </div>
                   
                    
         
    <!-- ******* -->
    
     <!-- update product modal -->
    
    <div class="modal fade" id="update-product-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header custom-bg text-white">
        <h5 class="modal-title" id="exampleModalLongTitle" >Update Product.</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      
          <form action="ProductOperationServlet" method="post" enctype="multipart/form-data">
          
          <input type="hidden" name="action" value="updateProduct">
          <input type="hidden" name="productId" value="<%=productId%>">
        
     <div class="container text-center">
                   <img style="max-width:200px" class="img-fluid" src="productImages/<%=product.getProductPhoto() %>" alt="users_pic" >
                   <h3><%=product.getProductPhoto() %></h3>
         </div> 
     
    <div class="form-group">
      <label for="inputEmail4">Product Title</label>
      <input type="text" class="form-control" name="productTitle" value="<%=product.getProductTitle() %>" required>
    </div>
    <div class="form-group">
      <label for="inputEmail4">Product Description</label>
      <textarea class="form-control"rows="4" name="productDescription" required><%=product.getProductDescription() %></textarea>
    </div>
    <div class="form-row">
    <div class="form-group col-md-4">
      <label for="inputEmail4">Product Price</label>
      <input type="number" class="form-control" name="productPrice" value=<%=product.getProductPrice() %> required>
    </div>
    <div class="form-group col-md-4">
      <label for="inputPassword4">Product Discount</label>
      <input type="number" class="form-control" name="productDiscount" value=<%=product.getProductDiscount() %> required>
    </div>
    <div class="form-group col-md-4">
      <label for="inputPassword4">Product Quantity</label>
      <input type="number" class="form-control" name="productQuantity" value=<%=product.getProductQuantity() %> required>
    </div>
   </div>
   
   
   
               <div class="form-group">
                   <label>Select Product Pic</label><br>
                   <input type="file" class="form-control" name="productPhoto" required>     
               </div>

   
   <div class="container text-center">
                   <input type="submit" class="btn custom-bg text-white" value="Update Product">
                   <!-- <button type="button" class="btn btn-secondary ml-5" data-dismiss="modal">Close</button> -->
               </div> 
    
</form>   
         
      </div>
    </div>
  </div>
</div>   
    <!-- *********************************** -->
    
</div>        

</body>
</html>