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
import board.dao.PurchaseDao;
import board.vo.CategoryVo;
import board.vo.PurchaseVo;

public class PurchaseActionList implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/view/purchaselist.jsp";
		int totalBasket = Integer.parseInt(request.getParameter("totalBasket"));
		HttpSession session = request.getSession(false);
	    String id  = (String) session.getAttribute("customer_id");
		
		CategoryDao       categoryDao   =  new CategoryDao();
		List<CategoryVo>  categoryList  =  categoryDao.getCategoryList();
			
		PurchaseDao pdao = new PurchaseDao();
		pdao.PurchaseAdd(id);
		
		
		BasketDao bdao = new BasketDao();
		bdao.deleteAllBasket(id);
		totalBasket = 0;
		
		PurchaseDao   bDao   =   new PurchaseDao();
		List<PurchaseVo>  purchaseList  =  bDao.getPurchaseList(id); 
		
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("totalBasket", totalBasket);
		request.setAttribute("purchaseList", purchaseList);
	
		// 페이지 이동
		request.getRequestDispatcher(path).forward(request,  response);
	}

}
