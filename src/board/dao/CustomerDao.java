package board.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import board.db.DBConn;
import board.vo.CustomerVo;

public class CustomerDao {

	public CustomerVo getLogin(String customer_id, String passwd) {

		CustomerVo			cVo		=	null;
		Connection			conn	=	null;
		CallableStatement	cstmt	=	null;
		
		
		try {
			DBConn	db	=	new	DBConn();
			conn		=	db.getConnection();
			String	sql	=	"{ CALL PKG_TRAVEL.PROC_CUSTOMER_LOGIN(?,?,?) }";
			cstmt	=	conn.prepareCall(sql);
			
			
			 
			cstmt.setString(1,customer_id);
			cstmt.setString(2,passwd);
			cstmt.registerOutParameter(3, Types.NUMERIC);
			
			cstmt.executeQuery();
			
			int logincheck	=	cstmt.getInt(3);
			
			cVo	=	new CustomerVo(logincheck);
			System.out.println(cVo);
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if( cstmt != null ) cstmt.close();
				if( conn  != null ) conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		return cVo;
		
		
		
	}

	public void insertCustomer(CustomerVo cVo) {
		
			Connection			conn	=	null;
			CallableStatement 	cstmt	=	null;
	
		
			try {
				DBConn	db	=	new DBConn();
				conn	=	db.getConnection();
				String	sql	=	"{CALL PKG_TRAVEL.PROC_CUSTOMER_JOIN(?,?,?,?,?,?,?)}";
				cstmt	=	conn.prepareCall(sql);
				
				cstmt.setString(1, cVo.getCustomer_id());
				cstmt.setString(2, cVo.getCustomer_name());
				cstmt.setString(3, cVo.getCustomer_nickname());
				cstmt.setString(4, cVo.getPasswd());
				cstmt.setString(5, cVo.getEmail());
				cstmt.setString(6, cVo.getTel());
				cstmt.setString(7, cVo.getAddress());
				
				cstmt.executeUpdate();
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}	finally {
				
				try {
					if( cstmt != null ) cstmt.close();
					if( conn  != null ) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		
	}
	
	public void updateCustomer(CustomerVo cVo) {

	      Connection          conn   =   null;
	      CallableStatement   cstmt   =   null;
	      
	      try {
	         DBConn   db   =   new DBConn();
	         conn      =   db.getConnection();
	         String   sql   =   "{   CALL PKG_TRAVEL.PROC_CUSTOMER_UPDATE(?,?,?,?,?,?)}";
	         cstmt   =   conn.prepareCall(sql);
	         
	         
	         cstmt.setString(1, cVo.getCustomer_id());
	         cstmt.setString(2, cVo.getCustomer_nickname());
	         cstmt.setString(3, cVo.getPasswd());
	         cstmt.setString(4, cVo.getEmail());
	         cstmt.setString(5, cVo.getTel());
	         cstmt.setString(6, cVo.getAddress());
	         
	         cstmt.executeUpdate();
	         
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }   finally {
	         
	         try {
	            if( cstmt != null ) cstmt.close();
	            if( conn  != null ) conn.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	         
	      }
	   }

	public CustomerVo getmypage(String customer_id) {
	      CustomerVo         cVo      =   null;
	      Connection         conn   =   null;
	      CallableStatement   cstmt   =   null;
	      
	      try {
	         DBConn   db   =   new   DBConn();
	         conn      =   db.getConnection();
	         String   sql   =   "{ CALL PKG_TRAVEL.PROC_MYPAGE_VIEW(?,?,?,?) }";
	         cstmt   =   conn.prepareCall(sql);
	         
	         cstmt.setString(1, customer_id);              
	         cstmt.registerOutParameter(2, Types.VARCHAR);  
	         cstmt.registerOutParameter(3, Types.VARCHAR);  
	         cstmt.registerOutParameter(4, Types.VARCHAR);  
	         
	         cstmt.executeQuery(); // 프로시저싷행
	         
	         String o_customer_nickname   =  cstmt.getString(2); 
	         String o_email =  cstmt.getString(3); 
	         String o_tel =  cstmt.getString(4); 
	         
	         cVo   = new CustomerVo(o_customer_nickname, o_email, o_tel);
	         
	         
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } finally {
	            
	            try {
	               if( cstmt != null ) cstmt.close();
	               if( conn  != null ) conn.close();
	            } catch (SQLException e) {
	               e.printStackTrace();
	            }
	      }
	      
	      
	      return cVo;
	   }
	
}
