<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<title></title>
	
	
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />
<style type="text/css">
	
	body{margin-top:10px;}
.footer {
    background:#232a34;
	padding-bottom: 20px;
	padding-top: 50px;
}
.footer_menu {
	margin-bottom: 30px;
}
.footer_menu ul {
	list-style: none;
	text-align: center;
}
.footer_menu ul li{display: inline-block;}
.footer_menu ul li a {
	color:#fff;
	padding: 0 10px;
	-webkit-transition: 0.3s;
	transition: 0.3s;
}
.footer_menu ul li a:hover{color:#554c86;}

/*START FOOTER SOCIAL DESIGN*/
.footer_profile{margin-bottom:20px;margin-left:60px;}
.footer_profile ul{
list-style: outside none none;
margin: 0;
padding: 0
}
.footer_profile ul li{
display: inline-block;
}
@media only screen and (max-width:480px) { 
.footer_profile ul li{margin:2px;}
}
.footer_profile ul li a img{width:60px;}

.footer_profile ul li a {
	background: #40E0D0;
	width: 40px;
	height: 40px;
	display: block;
	text-align: center;
	margin-right: 20px;
	border-radius: 50%;
	line-height: 40px;
	box-sizing: border-box;
	text-decoration: none;
	-webkit-transition: .3s;
	transition: .3s;
	color: black;
}
.footer_copyright {
    background:blue;
    margin-bottom: 30px;
    text-transform: uppercase;
    font-size: 12px;
    font-family: 'Montserrat', sans-serif;
    font-weight: 600;
    color: #fff;
}
</style>
</head>
<body>


<div class="footer">
	<div class="container">		
		<div class="row text-center">						
			<div class="col-lg-12 col-sm-12 col-xs-12">
			
			<div class="footer_profile">
					<ul>
						<li><a href="#"><i class="fa fa-facebook"></i></a></li>
						<li><a href="#"><i class="fa fa-twitter"></i></a></li>
						<li><a href="#"><i class="fa fa-instagram"></i></a></li>
						<li><a href="#"><i class="fa fa-youtube"></i></a></li>
						<li><a href="#"><i class="fa fa-google"></i></a></li>
					</ul>
				</div>
			
				<div class="footer_menu">
					<ul>
						<li><a href="#">Home</a></li>
						<li><a href="#">About</a></li>
						<li><a href="#">Service</a></li>
						<li><a href="#">Contact Us</a></li>
						<li><a href="#">Our Team</a></li>
					</ul>
				</div>
										
				<div class="footer_copyright">
					<p style="padding:2px;">© 2023 Design by NNR</p>
				</div>
					
				
										
			</div><!--- END COL -->							
		</div><!--- END ROW -->					
	</div><!--- END CONTAINER -->
</div>



</body>
</html>