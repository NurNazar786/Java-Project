package com.mobilestore.paypal;

import java.util.*;

import com.mobilestore.dao.*;
import com.mobilestore.model.*;
import com.mobilestore.utils.Helper;
import com.paypal.api.payments.*;
import com.paypal.base.rest.*;
public class PaymentServices {

	private static final String CLIENT_ID = "ARu9J2kQS9VJrgRfGXfi_fpfA5uU67U24krliiVVy1QigoCQDhHHzyEebd2_0wkxzftsJkExVFW56hE9";
	private static final String CLIENT_SECRET = "ENQmMKHbOP7fBb_wkW19g12hA805_PLUahbZqYIPqtEyGXwGu1c0aX4sluj44i52rPUFQiLFfuU2hc-O";
	private static final String MODE = "sandbox";

	public String authorizePayment(List<Cart> listOfProductInCart) throws PayPalRESTException {

		Payer payer = getPayerInformation(listOfProductInCart.get(0));
		RedirectUrls redirectUrls = getRedirectURLs();
		List<Transaction> listTransaction = getTransactionInformation(listOfProductInCart);

		Payment requestPayment = new Payment();
		requestPayment.setTransactions(listTransaction);
		requestPayment.setRedirectUrls(redirectUrls);
		requestPayment.setPayer(payer);
		requestPayment.setIntent("authorize");

		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
		Payment approvedPayment = requestPayment.create(apiContext);
		return getApprovalLink(approvedPayment);

	}

	private Payer getPayerInformation(Cart userFromCart) {
		User user=new UserDaoImp().getUserByUserId(userFromCart.getUserId());
		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");

		PayerInfo payerInfo = new PayerInfo();
		payerInfo.setFirstName(user.getUserName()).setEmail(user.getUserEmail()).setLastName("Ali");

		payer.setPayerInfo(payerInfo);

		return payer;
	}

	private RedirectUrls getRedirectURLs() {
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl("http://localhost:8080/OnlineMobileShoppingSystem/cancel.jsp");
		redirectUrls.setReturnUrl("http://localhost:8080/OnlineMobileShoppingSystem/review_payment");

		return redirectUrls;
	}

	private List<Transaction> getTransactionInformation(List<Cart> cartDetail) {
		
		List<Transaction> listTransaction = new ArrayList<>();
//		String shippingAddress=new UserDaoImp().getUserByUserId(cartDetail.get(0).getUserId()).getUserAddress();
		Details details = new Details();
		details.setShipping("40");
        details.setSubtotal(String.valueOf(new CartDaoImp().totalPriceFromCart(cartDetail.get(0).getUserId())));
        details.setTax("10");
        System.out.println("total from cart :"+String.valueOf(new CartDaoImp().totalPriceFromCart(cartDetail.get(0).getUserId())));
        int total=40+10+new CartDaoImp().totalPriceFromCart(cartDetail.get(0).getUserId());
        System.out.println("Toal: "+total);
		Amount amount = new Amount();
		amount.setCurrency("USD");
		amount.setTotal(String.valueOf(total));
		amount.setDetails(details);

		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setDescription("MOBILE MART");

		ItemList itemList = new ItemList();
		List<Item> items = new ArrayList<>();

		for(Cart c: cartDetail)
		{
			Product p=new ProductDaoImp().getProductById(c.getProductId());
			Item item = new Item();
			item.setCurrency("USD");
			item.setName(p.getProductTitle());
			item.setQuantity(String.valueOf(c.getQuantity()));
			item.setPrice(String.valueOf(Helper.getProductSellingPrice(p.getProductPrice(), p.getProductDiscount())));

			items.add(item);
		}
		
		itemList.setItems(items);
		transaction.setItemList(itemList);

		
		listTransaction.add(transaction);

		return listTransaction;
	}

	private String getApprovalLink(Payment approvedPayment) {
		List<Links> links = approvedPayment.getLinks();
		String approvalLink = null;

		for (Links link : links) {
			if (link.getRel().equalsIgnoreCase("approval_url")) {
				approvalLink = link.getHref();
				break;
			}
		}
		System.out.println(approvalLink);
		return approvalLink;
	}

	public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
		return Payment.get(apiContext, paymentId);
	}

	public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
		PaymentExecution paymentExecution = new PaymentExecution();
		paymentExecution.setPayerId(payerId);

		Payment payment = new Payment().setId(paymentId);

		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

		return payment.execute(apiContext, paymentExecution);
	}
	
}
