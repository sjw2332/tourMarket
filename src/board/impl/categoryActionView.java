package board.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.base.Action;
import board.dao.CategoryDao;
import board.dao.ItemDao;
import board.vo.CategoryVo;
import board.vo.ItemVo;
import board.vo.PageVo;

public class categoryActionView implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String category_code_id = request.getParameter("category_code_id");
		String category_code_name = request.getParameter("category_code_name");
//		String item_id = request.getParameter("item_id");
		int totalBasket = Integer.parseInt(request.getParameter("totalBasket")); 
//		String totalBasket = (String)request.getAttribute("totalBasket"); 
		int    nowpage    =  Integer.parseInt(request.getParameter("nowpage"));
		int    pagecount  =  Integer.parseInt(request.getParameter("pagecount"));

		request.setAttribute("totalBasket", totalBasket);  // 장바구니수
		System.out.println("tot2:" + totalBasket);
		//카테고리(헤더) 정보 
		CategoryDao categoryDao = new CategoryDao();
		List<CategoryVo>  categoryList = categoryDao.getCategoryList();
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("category_code_name", category_code_name);
		request.setAttribute("category_code_id", category_code_id);
		
		//상품 데이터 
		ItemDao itemDao = new ItemDao();
	    List<ItemVo> itemList = itemDao.getItemList(category_code_id, nowpage, pagecount ); // 조회된 현재 페이지 의 data 
	    int totalcount = itemList.get(0).getTotalcount();  // 전체자료수
		request.setAttribute("itemList", itemList);
//		request.setAttribute("item_id", item_id);
		
		// 현재 category 정보
		CategoryVo  categoryVo  = categoryDao.getCategory(category_code_id);
		request.setAttribute("categoryVo", categoryVo);
		
		// paging 관련변수 처리		
		PageVo  pageVo = new PageVo(nowpage, pagecount, totalcount);
		request.setAttribute("pageVo", pageVo);
		request.setAttribute("nowpage", nowpage);
		request.setAttribute("pagecount", pagecount);
	    		
		String path = "/view/category.jsp";
		request.getRequestDispatcher(path).forward(request, response);
		
		
	}

}
