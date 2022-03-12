package nuist.edu.hpf.FrontTest.front.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nuist.edu.hpf.Test.bean.DinnerTable;
import nuist.edu.hpf.Test.util.ConnectionFactory;

/*实现类*/
public class DinnerTableDaoImpl implements DinnerTableDao {

	@Override
	public List<DinnerTable> findDinnerTables(String tableStatus, String tableName) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			//①获取连接
			 connection = ConnectionFactory.getConnection();
			
			//②准备SQL
			StringBuffer sql = new StringBuffer("SELECT * FROM tb_dinner_table WHERE disabled = 0");
			
			if(tableName != null && !tableName.equals("")) {
				sql.append(" and table_name like '%"+tableName+"%' ");
			}
			
			if(tableStatus != null && !tableStatus.equals("")) {
				sql.append(" and table_status = "+ tableStatus);
			}
			System.out.println("sql:"+sql);
			
			//③ 获取集装箱
			 preparedStatement = connection.prepareStatement(sql.toString());
			//索引从1开始
			
			//④ 执行SQL语句
			 resultSet = preparedStatement.executeQuery();
			
			List<DinnerTable> dinnerTables = new ArrayList<>();
			//指针下移一位，因为将表头信息也查询出来了，而表头信息不需要封装
			//JDBC
			while (resultSet.next()) {
				DinnerTable dinnerTable = new DinnerTable();
				dinnerTable.setId(resultSet.getInt("id"));
				dinnerTable.setTableName(resultSet.getString("table_name"));
				dinnerTable.setTableStatus(resultSet.getInt("table_status"));
				dinnerTable.setDisabled(resultSet.getInt("disabled"));
				dinnerTable.setCreateDate(resultSet.getTimestamp("create_date"));
				dinnerTable.setUpdateDate(resultSet.getTimestamp("update_date"));
				dinnerTable.setUseUserId(resultSet.getInt("use_user_id"));
				System.out.println(" dao :"+dinnerTable);
				dinnerTables.add(dinnerTable);
			}
			
			return dinnerTables;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection,preparedStatement,resultSet );
		}
		
		return null;
	}

	@Override
	public DinnerTable findById(int id) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			//①获取连接
			 connection = ConnectionFactory.getConnection();
			
			//②准备SQL
			String sql = "SELECT * FROM tb_dinner_table WHERE id = ?";
			
			//③ 获取集装箱
			 preparedStatement = connection.prepareStatement(sql.toString());
			//索引从1开始
			 preparedStatement.setInt(1, id);
			
			//④ 执行SQL语句
			 resultSet = preparedStatement.executeQuery();
			
			//指针下移一位，因为将表头信息也查询出来了，而表头信息不需要封装
			while (resultSet.next()) {
				DinnerTable dinnerTable = new DinnerTable();
				dinnerTable.setId(resultSet.getInt("id"));
				dinnerTable.setTableName(resultSet.getString("table_name"));
				dinnerTable.setTableStatus(resultSet.getInt("table_status"));
				dinnerTable.setDisabled(resultSet.getInt("disabled"));
				dinnerTable.setCreateDate(resultSet.getTimestamp("create_date"));
				dinnerTable.setUpdateDate(resultSet.getTimestamp("update_date"));
				dinnerTable.setUseUserId(resultSet.getInt("use_user_id"));

				return dinnerTable;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection,preparedStatement,resultSet );
		}
		return null;
	}

	@Override
	public void update(DinnerTable dinnerTable) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			//①获取连接
			 connection = ConnectionFactory.getConnection();
			
			//②准备SQL
			String sql = "UPDATE tb_dinner_table SET table_status = ?,table_name= ?,"
					+ "CREATE_DATE = ?,update_date=now() ,"
					+ "disabled = ? , use_user_id=? WHERE id = ?";
			
			Date createDate = dinnerTable.getCreateDate() != null ?new Date(dinnerTable.getCreateDate().getTime()) : null;
			//③ 获取集装箱
			 preparedStatement = connection.prepareStatement(sql);
			//索引从1开始
			 preparedStatement.setInt(1, dinnerTable.getTableStatus());
			 preparedStatement.setString(2, dinnerTable.getTableName());
			 		//setDate()的API第二个参数是java.sql.Date 而getBeginUseDate()是java.util.Date
					//解决方法是  new Date(DinnerTable.getBeginUseDate().getTime())
			 preparedStatement.setDate(3, createDate);
			 preparedStatement.setInt(4, dinnerTable.getDisabled());
			 preparedStatement.setInt(5, dinnerTable.getUseUserId());
			 preparedStatement.setInt(6, dinnerTable.getId());
			 
			 
			//④ 执行SQL语句 (update 返回的是影响行数)
			int result  =preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//连接工厂 ConnectionFactory 可以优化
			//resultSet.close();
			//preparedStatement.close();
			//connection.close();
			ConnectionFactory.close(connection,preparedStatement,resultSet );
		}
	}
}
