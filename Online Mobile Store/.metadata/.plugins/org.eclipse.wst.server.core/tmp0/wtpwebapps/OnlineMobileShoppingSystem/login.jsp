<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login for Mobile Mart</title>
<%@ include file ="components/bootstrap_css_js.jsp" %>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"/>

   <link rel="stylesheet" href="css/login.css">


</head>
<body>

   
   <%@ include file="components/navbar.jsp"%>
    
             
     <div class="container">
      <%@ include file="components/message.jsp"%>
      <div class="wrapper">
        <div class="title"><span>Login Form</span></div>

        <form action="LoginServlet" method="post">
        
          <div class="row">
            <i class="fas fa-user"></i>
            <input type="email" id="exampleInputEmail1" name="email" placeholder="Enter Email" required>
          </div>

          <div class="row">
            <i class="fas fa-lock"></i>
            <input type="password" id="exampleInputPassword1" name="password" placeholder="Password" required>
          </div>

          
          <div class="pass" data-toggle="modal" data-target=".forgetpasswordmodal"><a>Forget password</a> </div>
          

          <div class="row button">
            <input type="submit" value="Login">
          </div>

          <div class="signup-link">Not a member? <a href="register.jsp">Signup now</a></div>
        </form>

      </div>
    </div>          
                
  
  <!-- forget password modal -->

<div class="modal fade forgetpasswordmodal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header custom-bg text-white text-center">
        <h5 class="modal-title" id="exampleModalLongTitle" >Forget Password</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
          <form action="login" method="post">
          <input type="hidden" name="action" value="forgetPassword">
         
  <div class="form">
 
    <div class="form-group ">
      <label for="inputPassword4">Email</label>
      <input type="email" class="form-control" id="email" name="email" required>
    </div>
    <div class="form-group ">
      <label for="inputPassword4">Phone</label>
      <input type="text" class="form-control" id="phone" name="phone" required>
    </div>
    <div class="form-group">
      <label for="inputPassword4">Password</label>
      <input type="password" class="form-control" id="pass" name="pass" required>
    </div>
    <div class="form-group">
      <label for="inputPassword4">Confirm Password</label>
      <input type="password" class="form-control" id="cpass" name="cpass" required>
    </div>
  </div>
 
  <div class="container text-center">
       
       <input type="submit" class="btn custom-bg text-light" value="Change">
       
       <button type="button" class="btn custom-bg text-light ml-5" data-dismiss="modal">Close</button>
                                      
  </div>   
</form>
      </div>
    </div>
  </div>
</div>


<!--  -->

</body>
</html>