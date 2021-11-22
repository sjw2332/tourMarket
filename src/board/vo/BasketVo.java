package board.vo;

public class BasketVo {
	private int basket_id;
	private String item_id;
	private String customer_id;
	private String cnt;
	private String regdate;
	private String item_name;
	private int price;
	private int subtotal;
	
	public BasketVo() {}
	public BasketVo(int basket_id, String item_id, String customer_id, String cnt, String regdate) {
		this.basket_id = basket_id;
		this.item_id = item_id;
		this.customer_id = customer_id;
		this.cnt = cnt;
		this.regdate = regdate;
	}
	
	public int getBasket_id() {
		return basket_id;
	}
	public void setBasket_id(int basket_id) {
		this.basket_id = basket_id;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}

	@Override
	public String toString() {
		return "BasketVo [basket_id=" + basket_id + ", item_id=" + item_id + ", customer_id=" + customer_id + ", cnt="
				+ cnt + ", regdate=" + regdate + "]";
	}
}
