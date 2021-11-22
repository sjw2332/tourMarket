package board.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.base.Action;
import board.dao.CategoryDao;
import board.dao.ItemDao;
import board.vo.CategoryVo;
import board.vo.ItemVo;

public class MainActionView implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String                path      =  "/index.jsp";
		CategoryDao       categoryDao   =  new CategoryDao();
		List<CategoryVo>  categoryList  =  categoryDao.getCategoryList();
		int totalBasket = Integer.parseInt(request.getParameter("totalBasket")); 
		
		ItemDao iDao = new ItemDao();
	    List<ItemVo> mainList = iDao.getMain();
	    System.out.println("ddd");
		    
		request.setAttribute("mainList", mainList);
				
		request.setAttribute("categoryList", categoryList);
	
		request.setAttribute("totalBasket", totalBasket);
		// 페이지 이동
		request.getRequestDispatcher(path).forward(request,  response);
	}
}
