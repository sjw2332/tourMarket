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

public class MypageActionform implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int totalBasket = Integer.parseInt(request.getParameter("totalBasket"));
		
		HttpSession session = request.getSession();
		String	customer_id	=	(String)session.getAttribute("customer_id");
		
		CustomerDao	cDao	=	new CustomerDao();
		CustomerVo	cVo		=	cDao.getmypage(customer_id);
		
		System.out.println(cVo);
		request.setAttribute("cVo", cVo);
		
		
		
		CategoryDao       categoryDao   =  new CategoryDao();
		List<CategoryVo>  categoryList  =  categoryDao.getCategoryList();
				
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("totalBasket", totalBasket);
	
		// 페이지 이동
		String path = "/view/mypage.jsp";
		request.getRequestDispatcher(path).forward(request,  response);
	}

}
