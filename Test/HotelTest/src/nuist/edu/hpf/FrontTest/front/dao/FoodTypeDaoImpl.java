package nuist.edu.hpf.FrontTest.front.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nuist.edu.hpf.Test.bean.FoodType;
import nuist.edu.hpf.Test.util.ConnectionFactory;

public class FoodTypeDaoImpl implements FoodTypeDao {

	//dao层  操作 数据库

	//查询所有未删除的菜系
	@Override
	public List<FoodType> findAll() {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			//①获取连接
			 connection = ConnectionFactory.getConnection();
			
			//②准备SQL
			String sql = "SELECT * FROM tb_food_type WHERE disabled = 0";
			
			//③ 获取集装箱
			 preparedStatement = connection.prepareStatement(sql.toString());
			//索引从1开始
			
			//④ 执行SQL语句
			 resultSet = preparedStatement.executeQuery();
			
			List<FoodType> foodTypes = new ArrayList<>();
			//指针下移一位，因为将表头信息也查询出来了，而表头信息不需要封装
			while (resultSet.next()) {
				FoodType foodType = new FoodType();
				//根据列名获取列值，是什么类型的就get什么类型
				foodType.setId(resultSet.getInt("id"));
				foodType.setTypeName(resultSet.getString("type_name"));
				foodType.setDisabled(resultSet.getInt("disabled"));
				foodType.setCreateDate(resultSet.getTimestamp("create_date"));//Timestamp()获取年月日时分秒
				foodType.setUpdateDate(resultSet.getTimestamp("update_date"));
				
				foodTypes.add(foodType);
			}
			
			return foodTypes;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection,preparedStatement,resultSet );
		}
		
		return null;
	}

}
