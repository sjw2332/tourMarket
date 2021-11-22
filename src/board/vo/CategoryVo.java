package board.vo;

public class CategoryVo {
	
	//Field
	private String category_code_id;
	private String category_code_name;

	//Constructor
	public CategoryVo() {}
	public CategoryVo(String category_code_id, String category_code_name) {
		super();
		this.category_code_id = category_code_id;
		this.category_code_name = category_code_name;
	}
	
	//Getter & Setter
	public String getCategory_code_id() {
		return category_code_id;
	}
	public void setCategory_code_id(String category_code_id) {
		this.category_code_id = category_code_id;
	}
	public String getCategory_code_name() {
		return category_code_name;
	}
	public void setCategory_code_name(String category_code_name) {
		this.category_code_name = category_code_name;
	}
	
	//toString
	@Override
	public String toString() {
		return "CategoryVo [category_code_id=" + category_code_id + ", category_code_name=" + category_code_name + "]";
	}
	
	
	
	
	
	
	
	
}
