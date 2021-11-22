package board.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.base.Action;
import board.dao.CategoryDao;
import board.dao.ItemDao;
import board.vo.CategoryVo;
import board.vo.ItemVo;

public class DetailActionView implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String category_code_id = request.getParameter("category_code_id");
		String category_code_name = request.getParameter("category_code_name");
		String item_id = request.getParameter("item_id");
		int totalBasket = Integer.parseInt(request.getParameter("totalBasket"));
		int    nowpage    =  Integer.parseInt(request.getParameter("nowpage"));
		int    pagecount  =  Integer.parseInt(request.getParameter("pagecount"));
		
		request.setAttribute("totalBasket", totalBasket);  // 장바구니수
	
		//카테고리(헤더) 정보 
		CategoryDao       categoryDao   =  new CategoryDao();
		List<CategoryVo>  categoryList  =  categoryDao.getCategoryList();
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("category_code_id", category_code_id);
		request.setAttribute("category_code_name", category_code_name);

		ItemDao itemDao = new ItemDao();
		List<ItemVo> itemList = itemDao.getItemList(category_code_id, nowpage, pagecount);
		Collections.shuffle(itemList);
		request.setAttribute("itemList", itemList);
		
		ItemVo itemVo = itemDao.getItem(item_id);
		request.setAttribute("itemVo", itemVo);
		
		System.out.println(itemVo);
		
		request.setAttribute("item_id", item_id);
		
		System.out.println(item_id);
				
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("newLineChar", "\n");
		request.setAttribute("nowpage", nowpage);
		request.setAttribute("pagecount", pagecount);
		
		// 페이지 이동
		String path = "/view/detail.jsp";
		request.getRequestDispatcher(path).forward(request,  response);
		
	}

}
