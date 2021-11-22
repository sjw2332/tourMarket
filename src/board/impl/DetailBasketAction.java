package board.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.base.Action;
import board.dao.BasketDao;
import board.vo.BasketVo;

public class DetailBasketAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String item_id = request.getParameter("item_id");
			String cnt = request.getParameter("cnt");   
			int totalBasket = Integer.parseInt(request.getParameter("totalBasket")); 
		    HttpSession session = request.getSession(false);
		    String id  = (String) session.getAttribute("customer_id");
		    System.out.println(item_id);
		    System.out.println(cnt);
		    System.out.println(totalBasket);
		    System.out.println(id);
		    
			BasketDao basketDao = new BasketDao();
			basketDao.basketAdd(item_id, cnt, id);  
			
			totalBasket++;
			
			String path = "/board?cmd=DETAILVIEW&totalBasket=" + totalBasket;
			request.getRequestDispatcher(path).forward(request, response);		
	}
}
