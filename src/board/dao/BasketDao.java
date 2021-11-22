package board.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.db.DBConn;
import board.vo.BasketVo;
import oracle.jdbc.OracleCallableStatement;

public class BasketDao {
	
	 public List<BasketVo> getBasketList(String id) {
		  List<BasketVo>   basketList   =   new ArrayList<BasketVo>();
	      
	      Connection   conn   =   null;
	      CallableStatement   cstmt   =   null;
	      ResultSet   rs   =   null;
	      
	      try {
	         DBConn   db   =   new DBConn();
	         conn      =   db.getConnection();
	         String   sql   =   "{ CALL PKG_TRAVEL.PROC_BASKET_LIST(?,?) }";
	         cstmt      =   conn.prepareCall(sql);
	         
	     	 cstmt.setString(1, id);      
	         cstmt.registerOutParameter( 2, oracle.jdbc.OracleTypes.CURSOR	);
	 		 cstmt.execute();
	 			
	 		 OracleCallableStatement ocstmt = (OracleCallableStatement) cstmt;
	 		 rs  = ocstmt.getCursor(2);

			while(rs.next()) {
					BasketVo   vo  =  new BasketVo();
					vo.setBasket_id( rs.getInt("basket_id") );
					vo.setItem_name( rs.getString("item_name") );
					vo.setPrice( rs.getInt("price") );
					vo.setCnt( rs.getString("cnt") );
					vo.setSubtotal( rs.getInt("subtotal") );
					vo.setRegdate( rs.getString("regdate") );
					
					basketList.add(vo);
			}
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }   finally {
	         try {
	            if(rs     !=null && !rs.isClosed() )    rs.close();
	            if(cstmt  !=null && !cstmt.isClosed() ) cstmt.close();
	            if(conn   !=null && !conn.isClosed() )  conn.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      
	      return basketList;
   }
	
	public void basketAdd(String item_id, String cnt, String id) {
		
		Connection        conn    = null;
		CallableStatement cstmt   = null;
		
		try {
			DBConn db  = new DBConn();
			conn       = db.getConnection();
			String sql = "{CALL PKG_TRAVEL.PROC_BASKET_ADD(?,?,?) }";
			cstmt      = conn.prepareCall(sql);
			
			cstmt.setString(1, item_id );
			cstmt.setString(2, cnt );
			cstmt.setString(3, id );
			
			cstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(cstmt  !=null && !cstmt.isClosed() ) cstmt.close();
				if(conn   !=null && !conn.isClosed() )  conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		}

	public void deleteBasket(int basket_id, String id) {

		Connection        conn    = null;
		CallableStatement cstmt   = null;
		
		try {
			DBConn db  = new DBConn();
			conn       = db.getConnection();
			String sql = "{CALL PKG_TRAVEL.PROC_BASKET_DELETE(?,?) }";
			cstmt      = conn.prepareCall(sql);
			
			cstmt.setInt(1, basket_id );
			cstmt.setString(2, id );
			
			cstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(cstmt  !=null && !cstmt.isClosed() ) cstmt.close();
				if(conn   !=null && !conn.isClosed() )  conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		}

	public void deleteAllBasket(String id) {

		Connection        conn    = null;
		CallableStatement cstmt   = null;
		
		try {
			DBConn db  = new DBConn();
			conn       = db.getConnection();
			String sql = "{CALL PKG_TRAVEL.PROC_BASKET_DELETEALL() }";
			cstmt      = conn.prepareCall(sql);
			
			cstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(cstmt  !=null && !cstmt.isClosed() ) cstmt.close();
				if(conn   !=null && !conn.isClosed() )  conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}