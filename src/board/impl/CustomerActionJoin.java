package board.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.base.Action;
import board.dao.CustomerDao;
import board.vo.CustomerVo;

public class CustomerActionJoin implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("utf-8");
		int totalBasket = Integer.parseInt(request.getParameter("totalBasket"));
		//회원가입 입력받은 parameter 받아들임.
		String	customer_id			=	request.getParameter("customer_id");	
		String	customer_name		=	request.getParameter("customer_name");	
		String	customer_nickname	=	request.getParameter("customer_nickname");	
		String	passwd				=	request.getParameter("passwd");	
		String	email				=	request.getParameter("email");	
		String	tel					=	request.getParameter("tel");	
		String	address				=	request.getParameter("address");
		
		CustomerVo	cVo		=	new CustomerVo(customer_id, customer_name, customer_nickname,
												passwd, email, tel, address);
		
		CustomerDao	cDao	=	new	CustomerDao();
		cDao.insertCustomer(cVo);
		
		request.setAttribute("totalBasket", totalBasket);
		// 완료 후 /index.jsp로 이동.  
		String path	= "/board?cmd=MAINVIEW&totalBasket=" + totalBasket;
		request.getRequestDispatcher(path).forward(request, response);
		
		
		
		
	}

}
