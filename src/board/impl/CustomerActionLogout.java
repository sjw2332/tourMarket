package board.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.base.Action;
import board.dao.BasketDao;

public class CustomerActionLogout implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
	    String id  = (String) session.getAttribute("customer_id");
	    
		BasketDao bdao = new BasketDao();
		bdao.deleteAllBasket(id);
		
		session.invalidate();
		int totalBasket = 0;
		String path	= "/board?cmd=MAINVIEW&totalBasket=" + totalBasket;
		request.getRequestDispatcher(path).forward(request,  response);
	}

}
