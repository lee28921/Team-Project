package kr.co.farmstory2.dto;

public class OrderDTO {
	private int orderNo;
	private int orderProduct;
	private int orderCount;
	private int orderDelivery;
	private int orderPrice;
	private int orderTotal;
	private String receiver;
	private String hp;
	private String zip;
	private String addr1;
	private String addr2;
	private String orderEtc;
	private String orderUser;
	private String orderDate;
	
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getOrderProduct() {
		return orderProduct;
	}
	public void setOrderProduct(int orderProduct) {
		this.orderProduct = orderProduct;
	}
	public void setOrderProduct(String orderProduct) {
		this.orderProduct = Integer.parseInt(orderProduct);
	}
	public int getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
	public void setOrderCount(String orderCount) {
		this.orderCount = Integer.parseInt(orderCount);
	}
	public int getOrderDelivery() {
		return orderDelivery;
	}
	public void setOrderDelivery(int orderDelivery) {
		this.orderDelivery = orderDelivery;
	}
	public void setOrderDelivery(String orderDelivery) {
		this.orderDelivery = Integer.parseInt(orderDelivery);
	}
	public int getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}
	public void setOrderPrice(String orderPrice) {
		this.orderPrice = Integer.parseInt(orderPrice);
	}
	public int getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(int orderTotal) {
		this.orderTotal = orderTotal;
	}
	public void setOrderTotal(String orderTotal) {
		this.orderTotal = Integer.parseInt(orderTotal);
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getOrderEtc() {
		return orderEtc;
	}
	public void setOrderEtc(String orderEtc) {
		this.orderEtc = orderEtc;
	}
	public String getOrderUser() {
		return orderUser;
	}
	public void setOrderUser(String orderUser) {
		this.orderUser = orderUser;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	
	@Override
	public String toString() {
		return "OrderDTO [orderNo=" + orderNo + ", orderProduct=" + orderProduct + ", orderCount=" + orderCount
				+ ", orderDelivery=" + orderDelivery + ", orderPrice=" + orderPrice + ", orderTotal=" + orderTotal
				+ ", receiver=" + receiver + ", hp=" + hp + ", zip=" + zip + ", addr1=" + addr1 + ", addr2=" + addr2
				+ ", orderEtc=" + orderEtc + ", orderUser=" + orderUser + ", orderDate=" + orderDate + "]";
	}
	
	
}
