package board.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.base.Action;
import board.dao.CategoryDao;
import board.dao.CustomerDao;
import board.vo.CategoryVo;
import board.vo.CustomerVo;

public class CustomerActionUpdate implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//회원가입 입력받은 parameter 받아들임.
		String	customer_id			=	request.getParameter("customer_id");	
		String	customer_nickname	=	request.getParameter("customer_nickname");	
		String	passwd				=	request.getParameter("passwd");	
		String	email				=	request.getParameter("email");	
		String	tel					=	request.getParameter("tel");	
		String	address				=	request.getParameter("address");
		int totalBasket = Integer.parseInt(request.getParameter("totalBasket"));
		
		CustomerVo	cVo		=	new CustomerVo(customer_id, customer_nickname,
												passwd, email, tel, address);
		
		System.out.println(cVo);
		
		CustomerDao	cDao	=	new	CustomerDao();
		cDao.updateCustomer(cVo);
		
		HttpSession session = request.getSession();
	      String   id   =   (String)session.getAttribute("customer_id");
	      
	      CustomerDao   cDao2   =   new CustomerDao();
	      CustomerVo   cVo2      =   cDao2.getmypage(id);
	      
	      System.out.println(cVo);
	      request.setAttribute("cVo", cVo2);
		
		CategoryDao       categoryDao   =  new CategoryDao();
		List<CategoryVo>  categoryList  =  categoryDao.getCategoryList();
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("totalBasket", totalBasket);
		// 완료 후 /index.jsp로 이동.
		String path	=	"view/mypage.jsp";
		request.getRequestDispatcher(path).forward(request, response);
		
	}

}
