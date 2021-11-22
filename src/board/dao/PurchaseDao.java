package board.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.db.DBConn;
import board.vo.BasketVo;
import board.vo.PurchaseVo;
import oracle.jdbc.OracleCallableStatement;

public class PurchaseDao {
	
	public List<PurchaseVo> getPurchaseList(String id) {
		List<PurchaseVo>   purchaseList   =   new ArrayList<PurchaseVo>();
	      
	      Connection   conn   =   null;
	      CallableStatement   cstmt   =   null;
	      ResultSet   rs   =   null;
	      
	      try {
	         DBConn   db   =   new DBConn();
	         conn      =   db.getConnection();
	         String   sql   =   "{ CALL PKG_TRAVEL.PROC_PURCHASE_LIST(?,?) }";
	         cstmt      =   conn.prepareCall(sql);
	         
	     	 cstmt.setString(1, id);      
	         cstmt.registerOutParameter( 2, oracle.jdbc.OracleTypes.CURSOR	);
	 		 cstmt.execute();
	 			
	 		 OracleCallableStatement ocstmt = (OracleCallableStatement) cstmt;
	 		 rs  = ocstmt.getCursor(2);

			while(rs.next()) {
					PurchaseVo   vo  =  new PurchaseVo();
					vo.setPurchase_id( rs.getInt("purchase_id") );
					vo.setItem_name( rs.getString("item_name") );
					vo.setPrice( rs.getInt("price") );
					vo.setCnt( rs.getString("cnt") );
					vo.setSubtotal( rs.getInt("subtotal") );
					vo.setRegdate( rs.getString("regdate") );
					
					purchaseList.add(vo);
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
	      
	      return purchaseList;	
	}	
	
	public void PurchaseAdd(String id) {
		Connection        conn    = null;
		CallableStatement cstmt   = null;
		
		try {
			DBConn db  = new DBConn();
			conn       = db.getConnection();
			String sql = "{CALL PKG_TRAVEL.PROC_PURCHASE_ADD(?) }";
			cstmt      = conn.prepareCall(sql);
			
			cstmt.setString(1, id);
	
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
