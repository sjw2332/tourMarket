package board.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.base.Action;
import board.dao.BasketDao;
import board.dao.CategoryDao;
import board.vo.BasketVo;
import board.vo.CategoryVo;

public class BasketActionDelete implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/view/basket.jsp";
		int totalBasket = Integer.parseInt(request.getParameter("totalBasket"));
		int basket_id = Integer.parseInt(request.getParameter("basket_id"));
	    HttpSession session = request.getSession(false);
        String id  = (String) session.getAttribute("customer_id");
//		String customer_id   =   request.getParameter("customer_id");
        
        System.out.println(basket_id);
		CategoryDao       categoryDao   =  new CategoryDao();
		List<CategoryVo>  categoryList  =  categoryDao.getCategoryList();
		
		// 삭제
		BasketDao dao = new BasketDao();
		dao.deleteBasket(basket_id, id);
		
		BasketDao   bDao   =   new BasketDao();
		List<BasketVo>  basketList  =  bDao.getBasketList(id);
		System.out.println(basketList);
		
		totalBasket -= 1;
		
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("totalBasket", totalBasket);
		request.setAttribute("basketList", basketList);
	
		// 페이지 이동
		request.getRequestDispatcher(path).forward(request,  response);
	}

}
