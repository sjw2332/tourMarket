package board.vo;

public class ItemVo {
	
	//Field
	private String item_id;
	private String item_name;
	private String item_cont;
	private String category_code_id;
	private int price;
	private String regdate;
	private int buycount;
	private int totalcount;     // 전체 자료수
	
	//Constructor
	public ItemVo() {}
	public ItemVo(String item_id, String item_name, String item_cont, String category_code_id, int price,
			String regdate, int buycount, int totalcount) {
		this.item_id = item_id;
		this.item_name = item_name;
		this.item_cont = item_cont;
		this.category_code_id = category_code_id;
		this.price = price;
		this.regdate = regdate;
		this.buycount = buycount;
		this.totalcount = totalcount;
	}
	
	//Getter & Setter
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getItem_cont() {
		return item_cont;
	}
	public void setItem_cont(String item_cont) {
		this.item_cont = item_cont;
	}
	public String getCategory_code_id() {
		return category_code_id;
	}
	public void setCategory_code_id(String category_code_id) {
		this.category_code_id = category_code_id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getBuycount() {
		return buycount;
	}
	public void setBuycount(int buycount) {
		this.buycount = buycount;
	}
	public int getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	
	//toString
	@Override
	public String toString() {
		return "ItemVo [item_id=" + item_id + ", item_name=" + item_name + ", item_cont=" + item_cont
				+ ", category_code_id=" + category_code_id + ", price=" + price + ", regdate=" + regdate + ", buycount="
				+ buycount + "]";
	}
}
