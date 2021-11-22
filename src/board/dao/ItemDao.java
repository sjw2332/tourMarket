package board.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import board.db.DBConn;
import board.vo.ItemVo;
import oracle.jdbc.OracleCallableStatement;

public class ItemDao {
	
	Connection        conn  = null; 
	CallableStatement cstmt = null;
	ResultSet         rs    = null;


	public List<ItemVo> getItemList(String category_code_id, int nowpage, int pagecount) {
		
		List<ItemVo>     list  = new ArrayList<ItemVo>();
	
	    try {
			DBConn  db   =  new DBConn();
			conn         =  db.getConnection();
			String  sql  =  "{CALL  PKG_TRAVEL.PROC_ITEM_LIST(?, ?, ?, ?, ?) }";
			cstmt        =  conn.prepareCall(sql);
			
			cstmt.setString(1,  category_code_id);     
			cstmt.setInt(2,  nowpage);                // nowpage     
			cstmt.setInt(3,  pagecount);              // pagecount     
			cstmt.registerOutParameter(4, 
				Types.NUMERIC );                      // 결과 , 전체자료수
			cstmt.registerOutParameter(5, 
					oracle.jdbc.OracleTypes.CURSOR ); // 결과 , cursor
			
			cstmt.execute();  // 스토어드 프로시저 실행
									
			OracleCallableStatement ocstmt = (OracleCallableStatement) cstmt;
			
			int  totalcount  = ocstmt.getInt(4);
			System.out.println("t:" + totalcount);
			
			rs    =     ocstmt.getCursor(5);
			while( rs.next() ) {
				String   item_id       =  rs.getString("item_id");
				String   item_name      =  rs.getString("item_name");
				String   item_cont     =  rs.getString("item_cont");   
				String   regdate    =  rs.getString("regdate");   
				int      price  =  rs.getInt("price");
				int      buycount       =  rs.getInt("buycount");
		
				ItemVo  itemVo    =  new ItemVo(item_id, item_name, item_cont, category_code_id, price, regdate, buycount, totalcount);
				
				list.add(itemVo);				
			}
			
			
		} catch (SQLException e) {;
			e.printStackTrace();
		}  finally {
			try {
				if( rs    != null ) rs.close();
				if( cstmt != null ) cstmt.close();
				if( conn  != null ) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
				
		return list;
	}


	public ItemVo getItem(String item_id) {
		ItemVo itemVo = null;
		
		try {
			DBConn db = new DBConn();
			conn = db.getConnection();
			String sql = "{CALL PKG_TRAVEL.PROC_ITEM_VIEW(?,?) }";
			cstmt =  conn.prepareCall( sql );
			
			cstmt.setInt(1,  Integer.parseInt( item_id ));
			cstmt.registerOutParameter(2, 
					oracle.jdbc.OracleTypes.CURSOR );
			
			cstmt.executeQuery();
			
			OracleCallableStatement ocstmt = (OracleCallableStatement) cstmt; 
			
			rs = ocstmt.getCursor( 2 ); 
			
			if( rs.next() ) {
				
				String itemId = rs.getString("item_id") ;
				String itemName = rs.getString("item_name"); 
				String itemCont = rs.getString("item_cont"); 
				String categoryId = rs.getString("category_code_id"); 
				int price = rs.getInt("price"); 
				String regdate = rs.getString("regdate"); 
				int buycount = rs.getInt("buycount");
				int totalcount = 0;
				
				itemVo = new ItemVo(itemId, itemName, itemCont, categoryId, price, regdate, buycount, totalcount);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if( cstmt != null ) cstmt.close();
				if( conn  != null ) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return itemVo;
	}


	public List<ItemVo> getMain() {
		List<ItemVo> iVo = new ArrayList<ItemVo>();
		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		try {
			DBConn db = new DBConn();
			conn = db.getConnection();
			String sql = "{CALL PKG_TRAVEL.PROC_MAIN_VIEW(?)}";
			cstmt = conn.prepareCall(sql);

			cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);

			cstmt.execute();

			OracleCallableStatement ocstmt = (OracleCallableStatement) cstmt;
			rs = ocstmt.getCursor(1);

			while (rs.next()) {
				ItemVo vo = new ItemVo();
				vo.setItem_id(rs.getString("item_id"));
				vo.setItem_name(rs.getString("item_name"));
				vo.setPrice(rs.getInt("price"));
				vo.setBuycount(rs.getInt("buycount"));
				vo.setCategory_code_id(rs.getString("category_code_id"));

				iVo.add(vo);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				if (cstmt != null)
					cstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return iVo;
	}
}
