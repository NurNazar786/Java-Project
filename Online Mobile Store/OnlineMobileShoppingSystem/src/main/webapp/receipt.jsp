<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payment Receipt</title>
<%@ include file ="components/bootstrap_css_js.jsp" %>
<style type="text/css">
table {
	border: 0;
}

table td {
	padding: 5px;
}
</style>
</head>
<body>
	<%@ include file="components/navbar.jsp"%>
	<div class="text-center">
		<h1>Payment Done. Thank you for purchasing our products</h1>
	</div>
	<div class="admin">
		<div class="card text-center mx-auto" style="width: 36rem; margin-top:70px;">
			<div class="card-header custom-bg">
				<h3>Receipt Details:</h3>
			</div>
			<div class="card-body text-left">
				<div class="table-responsive">
					<table class="table">
						<thead>
							<tr>
								<th>Merchant:</th>
								<td>Mobile Mart PVT LTD.</td>
							</tr>
							<tr>
								<th>Payer:</th>
								<td>${payer.firstName}${payer.lastName}</td>
							</tr>

							<tr>
								<th>Subtotal:</th>
								<td>Rs ${transaction.amount.details.subtotal}</td>
							</tr>
							<tr>
								<th>Shipping:</th>
								<td>Rs ${transaction.amount.details.shipping}</td>
							</tr>
							<tr>
								<th>Tax:</th>
								<td>Rs ${transaction.amount.details.tax}</td>
							</tr>
							<tr>
								<th>Total:</th>
								<td>Rs ${transaction.amount.total}</td>
							</tr>
						</thead>
					</table>
				</div>

				<div class="text-center">
					<a href="index.jsp"><button class="btn custom-bg text-white">Shop
							More</button></a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>