package board.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import board.db.DBConn;
import board.vo.CategoryVo;
import oracle.jdbc.OracleCallableStatement;

public class CategoryDao {
	
	Connection        conn  = null; 
	CallableStatement cstmt = null;
	ResultSet         rs    = null;

	public List<CategoryVo> getCategoryList() {

		List<CategoryVo> categoryList = new ArrayList<CategoryVo>();
		
		try {
			DBConn  db = new DBConn();
			conn       = db.getConnection();
			String sql = "{CALL PKG_TRAVEL.PROC_CATEGORY_LIST(?)}";
			cstmt      = conn.prepareCall(sql) ;
			
			cstmt.registerOutParameter( 1, 
				oracle.jdbc.OracleTypes.CURSOR	);
			cstmt.execute();
			
			OracleCallableStatement ocstmt = (OracleCallableStatement) cstmt;
			rs  = ocstmt.getCursor(1);
			
			while(rs.next()) {
				CategoryVo   vo  =  new CategoryVo();
				vo.setCategory_code_id( rs.getString("Category_code_id") );
				vo.setCategory_code_name( rs.getString("Category_code_name") );
				
				categoryList.add(vo);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs     !=null && !rs.isClosed() )    rs.close();
				if(cstmt  !=null && !cstmt.isClosed() ) cstmt.close();
				if(conn   !=null && !conn.isClosed() )  conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return categoryList;
	}

	public CategoryVo getCategory(String category_code_id) {
		
		CategoryVo categoryVo = null;
		
		try {
			DBConn  db  = new DBConn();
			conn 		= db.getConnection();
			String  sql = "{CALL PKG_TRAVEL.PROC_CATEGORY_VIEW(?,?,?) }"; 
			cstmt       = conn.prepareCall(sql);
			
			// 프로시저 파라미터 처리
			cstmt.setString(1, category_code_id);                   // 첫번재 ? : CATEGORY_CODE_ID
			// out 파라미터 등록
			cstmt.registerOutParameter(2, Types.VARCHAR);  // 2번째 ?  CATEGORY_CODE_ID 
			cstmt.registerOutParameter(3, Types.VARCHAR);  // 3번째 ?  CATEGORY_CODE_NAME 
			
			cstmt.executeQuery(); // 프로시저싷행
			
			String o_category_id   =  cstmt.getString(2); 
			String o_category_name =  cstmt.getString(3); 
			
			categoryVo   = new CategoryVo(o_category_id, o_category_name);
			
		} catch(SQLException e) {
			e.printStackTrace();			
		} finally {
			try {
				if(cstmt != null) cstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return   categoryVo;
	}

	
}
