<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register For Mobile Store</title>

 
<%@ include file ="components/bootstrap_css_js.jsp" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"/>
<link rel="stylesheet" href="css/register.css">

</head>
<body>
     <%@ include file="components/navbar.jsp"%>
    
      <div class="container" style="margin-top: 110px;">
       <%@ include file="components/message.jsp"%>
      <div class="wrapper">
        <div class="title"><span>Register here..!! </span></div>

        <form action="register" method="post" enctype="multipart/form-data">

          <div class="row">
            <i class="fas fa-user"></i>
           <input type="text" name="name" id="name" placeholder="Enter Name" autocomplete="off" required>

          </div>

          <div class="row">
            <i class="fas fa-envelope"></i>
          <input type="email" name="email" id="email" placeholder="Enter Email" autocomplete="off" required>

          </div>

           <div class="row">
            <i class="fas fa-lock"></i>
            <input type="password" name="pass" id="pass" placeholder="Enter Pass" autocomplete="off" required>

          </div>

           <div class="row">
            <i class="fas fa-mobile"></i>
           <input type="number" name="mobile" id="mobile" placeholder="Enter Mobile" autocomplete="off" required>

          </div>
           <div class="address">
            <i class="fas fa-address-card"></i>
            <textarea   name="useraddress"    placeholder="Enter Address" required></textarea>

          </div>

           <div class="row">
            <i class="fas fa-camera"></i>
            <input type="file"  name="profilePic" required style="padding-top:7px;">

          </div>

           

          <div class="row button">
            <input type="submit" value="Register">
          </div>

          <div class="signup-link">Already Register ? <a href="login.jsp">Login now</a></div>
        </form>

	  </div>
	</div>
     
 

</body>
</html>