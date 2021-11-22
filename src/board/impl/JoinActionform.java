package board.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.base.Action;
import board.dao.CategoryDao;
import board.vo.CategoryVo;

public class JoinActionform implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/view/join.jsp";
		int totalBasket = Integer.parseInt(request.getParameter("totalBasket"));
		CategoryDao       categoryDao   =  new CategoryDao();
		List<CategoryVo>  categoryList  =  categoryDao.getCategoryList();
				
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("totalBasket", totalBasket);
	
		// 페이지 이동
		request.getRequestDispatcher(path).forward(request,  response);
	}

}
