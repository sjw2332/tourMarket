package board.vo;

public class PurchaseVo {
	private int purchase_id;
	private String item_id;
	private String customer_id;
	private String cnt;
	private String regdate;
	private String item_name;
	private int price;
	private int subtotal;
	
	public PurchaseVo() {}
	public PurchaseVo(int purchase_id, String item_id, String customer_id, String cnt, String regdate) {
		this.purchase_id = purchase_id;
		this.item_id = item_id;
		this.customer_id = customer_id;
		this.cnt = cnt;
		this.regdate = regdate;
	}
	
	public int getPurchase_id() {
		return purchase_id;
	}
	public void setPurchase_id(int purchase_id) {
		this.purchase_id = purchase_id;
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
		return "PurchaseVo [purchase_id=" + purchase_id + ", item_id=" + item_id + ", customer_id=" + customer_id
				+ ", cnt=" + cnt + ", regdate=" + regdate + "]";
	}
}
