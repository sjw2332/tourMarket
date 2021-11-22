package board.vo;

public class CustomerVo {
	//fields
	private String customer_id;
	private String customer_name;
	private String customer_nickname;
	private String passwd;
	private String email;
	private String tel;
	private String address;
	private int		logincheck;
	
	
	//Cunstructor
	public CustomerVo() {}
	public CustomerVo(String customer_id, String customer_name, String customer_nickname, String passwd, String email,
			String tel, String address) {
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.customer_nickname = customer_nickname;
		this.passwd = passwd;
		this.email = email;
		this.tel = tel;
		this.address = address;
	}
	
	
	// Constructor for login
	public CustomerVo(int logincheck) {
		this.logincheck	=	logincheck;
		
	}
	
	//Constructor for update
			public CustomerVo(String customer_id, String customer_nickname, String passwd, String email,
					String tel, String address) {
				this.customer_id	=	customer_id;
				this.customer_nickname = customer_nickname;
				this.passwd = passwd;
				this.email = email;
				this.tel = tel;
				this.address = address;
			}
		
	// mypage용
	public CustomerVo(String customer_nickname, String email, String tel) {
		this.customer_nickname = customer_nickname;
		this.email = email;
		this.tel = tel;
	}
	
	//getter & setter
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_nickname() {
		return customer_nickname;
	}
	public void setCustomer_nickname(String customer_nickname) {
		this.customer_nickname = customer_nickname;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	public int getLogincheck() {
		return logincheck;
	}
	public void setLogincheck(int logincheck) {
		this.logincheck = logincheck;
	}
	
	
	//toString
	@Override
	public String toString() {
		return "CustomerVo [customer_id=" + customer_id + ", customer_name=" + customer_name + ", customer_nickname="
				+ customer_nickname + ", passwd=" + passwd + ", email=" + email + ", tel=" + tel + ", address="
				+ address + "]";
	}
}
