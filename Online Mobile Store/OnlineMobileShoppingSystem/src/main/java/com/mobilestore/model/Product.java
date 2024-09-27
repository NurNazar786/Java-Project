package com.mobilestore.model;

public class Product
{
    private int productId,productPrice,productDiscount,productQuantity,brandId;
    private String productTitle,productDescription,productPhoto;
    
    public Product()
    {
    	
    }

	public Product(int productPrice, int productDiscount, int productQuantity, int brandId, String productTitle,
			String productDescription, String productPhoto) {
		super();
		this.productPrice = productPrice;
		this.productDiscount = productDiscount;
		this.productQuantity = productQuantity;
		this.brandId = brandId;
		this.productTitle = productTitle;
		this.productDescription = productDescription;
		this.productPhoto = productPhoto;
	}
	
    

	public Product( int productPrice, int productDiscount, int productQuantity, String productTitle,
			String productDescription, String productPhoto) {
		super();
	
		this.productPrice = productPrice;
		this.productDiscount = productDiscount;
		this.productQuantity = productQuantity;
		this.productTitle = productTitle;
		this.productDescription = productDescription;
		this.productPhoto = productPhoto;
	}

	public Product(int productId, int productPrice, int productDiscount, int productQuantity, int brandId,
			String productTitle, String productDescription, String productPhoto) {
		super();
		this.productId = productId;
		this.productPrice = productPrice;
		this.productDiscount = productDiscount;
		this.productQuantity = productQuantity;
		this.brandId = brandId;
		this.productTitle = productTitle;
		this.productDescription = productDescription;
		this.productPhoto = productPhoto;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductDiscount() {
		return productDiscount;
	}

	public void setProductDiscount(int productDiscount) {
		this.productDiscount = productDiscount;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductPhoto() {
		return productPhoto;
	}

	public void setProductPhoto(String productPhoto) {
		this.productPhoto = productPhoto;
	}

	

	

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productPrice=" + productPrice + ", productDiscount="
				+ productDiscount + ", productQuantity=" + productQuantity + ", brandId=" + brandId + ","
						+ " productTitle=" + productTitle + ", productDescription=" + productDescription
				+ ", productPhoto=" + productPhoto + "]";
	}
	
	

    
	
    
}
