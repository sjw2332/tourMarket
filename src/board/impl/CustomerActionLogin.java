package board.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.base.Action;
import board.dao.CustomerDao;
import board.vo.CustomerVo;

public class CustomerActionLogin implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		int totalBasket = Integer.parseInt(request.getParameter("totalBasket"));
		String	customer_id		=	request.getParameter("customer_id");
		String	passwd			=	request.getParameter("passwd");
		String  reff         =   request.getParameter("ref");
		
		System.out.println(customer_id);
		System.out.println(passwd);
		
		CustomerDao	cDao		=	new	CustomerDao();
		CustomerVo	cVo			=	cDao.getLogin(customer_id, passwd);
		
		 //로그인 후 오픈할 페이지 조건
	      String path = "";
	      
	      if(reff.equals("http://localhost:9090/board")||reff.equals("http://localhost:9090/")||reff.equals("http://localhost:9090/board?cmd=LOGOUT")) {
	         path   = "/board?cmd=MAINVIEW&totalBasket=" + totalBasket;
	         
	      } else {
	         path = reff.substring(21);
	      };
	
		
		
		
		String loginId	=	Integer.toString(cVo.getLogincheck());
		
		
		System.out.println(loginId);
		HttpSession	session	=	request.getSession();
		
		
			//로그인 성공
		if(loginId !=null && loginId.equals("0") ) {
			session.setAttribute("loginId", loginId);
			System.out.println(session.getId());
			session.setAttribute("customer_id",customer_id);
			session.getAttribute(customer_id);
			System.out.println(customer_id);
			
			//System.out.println(session.getAttribute(loginId) );
			//System.out.println( session.setAttribute("loginId", loginId) );
			
			
			
//			String path	= "/board?cmd=MAINVIEW&totalBasket=" + totalBasket;
			request.getRequestDispatcher(path).forward(request, response);
			
			
			//비밀번호가 틀렸을 경우
		}	else if( loginId !=null && loginId.equals("2")){
			session.invalidate();

			/*
			PrintWriter out	=	response.getWriter();
			response.setContentType("text/html; charset=utf-8");
			out.println("<script language='javascript'>");
			out.println("alert('비밀번호가 틀렸습니다.');");
			out.println("</script>");
			out.flush();
			*/
			
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().print("<script>alert('비밀번호가 잘못되었습니다.');history.back();</script>");
			return;
			
		}
		
		// 아이디가 틀렸을 경우
		else if( loginId !=null && loginId.equals("1")){
			session.invalidate();
			
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().print("<script>alert('아이디가 잘못되었습니다.');history.back();</script>");
			return;
		}
		
		
	
		
		
	}

}
