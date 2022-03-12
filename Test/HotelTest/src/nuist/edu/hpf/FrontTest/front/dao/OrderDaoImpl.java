package nuist.edu.hpf.FrontTest.front.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nuist.edu.hpf.Test.bean.DinnerTable;
import nuist.edu.hpf.Test.bean.Food;
import nuist.edu.hpf.Test.bean.Order;
import nuist.edu.hpf.Test.bean.OrderDetail;
import nuist.edu.hpf.Test.util.ConnectionFactory;
import nuist.edu.hpf.Test.util.ConstantUtil;

public class OrderDaoImpl implements OrderDao {

	@Override
	public void order(Order order, Map<Integer, Integer> shopCar, Integer userId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			//①获取连接
			 connection = ConnectionFactory.getConnection();
			//手动提交事务
			 connection.setAutoCommit(false);   //order和orderList要么同时通过，要么同时失败
			//②准备SQL
			String sql = "INSERT INTO tb_order(order_code,table_id,total_Price,order_date,create_userId) VALUES(?,?,?,NOW(),?)";
			
			//③ 获取集装箱
			 preparedStatement = connection.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
			//索引从1开始
			 preparedStatement.setString(1, order.getOrderCode());
			 preparedStatement.setInt(2, order.getTableId());
			 preparedStatement.setDouble(3, order.getTotalPrice());
			 preparedStatement.setInt(4, userId);
			
			//④ 执行SQL语句,返回影响的行数
			int result = preparedStatement.executeUpdate();
			
			resultSet = preparedStatement.getGeneratedKeys();  //包装集，现在resultSet指向第一行 （列名）
			
			//指针下移一位 （核心），因为将表头信息也查询出来了，而表头信息不需要封装
			resultSet.next();
			Integer orderId = resultSet.getInt(1);  //ctrl + alt + V
			System.out.println("orderId:"+orderId);

			//测试错误 （手动提交）
			//JDBC事务，当需要一次执行多条SQL语句时，使用事务。sql语句全部执行成功，才对数据库进行更新
			//int i = 100/0;
			
			//保存订单明细
			String  sqlItem = "INSERT INTO tb_order_detail(orderId,food_id,buyNum,discount)  VALUES(?,?,?,?)";
			//sql语句要装进去
			preparedStatement = connection.prepareStatement(sqlItem);
			
			//获取购物车中所有的菜品id
			Set<Integer>  foodIds = shopCar.keySet();
			for (Integer foodId : foodIds) {
				//购买数量
				Integer buyNum = shopCar.get(foodId);
				FoodDao foodDao = new FoodDaoImpl();
				Food food = foodDao.findById(foodId);

				
				preparedStatement.setInt(1, orderId);
				preparedStatement.setInt(2, foodId);
				preparedStatement.setInt(3, buyNum);
				preparedStatement.setDouble(4, food.getDiscount());
				
				preparedStatement.addBatch();   //addBatch()批处理，对象的一批命令添加一组参数
			}
			//执行批处理
			preparedStatement.executeBatch();  //执行批处理，返回的是数组
			connection.commit();  //当点击提交事务时才提交
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection,preparedStatement,resultSet );
		}
	}

	/*通过餐桌的id查询未付款的订单*/
	@Override
	public List<Order> findByTableId(int dinnerTableId,Integer userId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			//①获取连接
			 connection = ConnectionFactory.getConnection();
			//②准备SQL
			//disabled = 0 标识未删除的
			StringBuffer sql = new StringBuffer("SELECT * FROM tb_order WHERE disabled= 0 AND table_id = ? AND  order_Status = 0");
			if(userId != null) {
				sql.append(" and create_userId = ?");
			}
			//③ 获取集装箱
			 preparedStatement = connection.prepareStatement(sql.toString());
			//索引从1开始
			 preparedStatement.setInt(1, dinnerTableId);
			 if(userId != null) {
				 preparedStatement.setInt(2, userId);
				}
			
			//④ 执行SQL 查询语句,返回影响的行数
			 resultSet = preparedStatement.executeQuery();
			 List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				Order order = new Order();
				order.setId(resultSet.getInt("id"));
				order.setCreatUserId(resultSet.getInt("create_userId"));
				order.setOrderCode(resultSet.getString("order_code"));
				order.setOrderDate(resultSet.getTimestamp("order_Date"));
				order.setOrderStatus(resultSet.getInt("order_Status"));
				order.setTableId(resultSet.getInt("table_id"));
				order.setTotalPrice(resultSet.getDouble("total_Price"));
				order.setPayDate(resultSet.getTimestamp("pay_date"));
				order.setDisabled(resultSet.getInt("disabled"));
				order.setCreatUserId(resultSet.getInt("create_userId"));
				
				orders.add(order); //用集合来接
			}
			return orders;			//集合返回到上一层去
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection,preparedStatement,resultSet );
		}
		return null;
	}

	@Override
	public List<OrderDetail> findByOrderId(Integer orderId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			//①获取连接
			 connection = ConnectionFactory.getConnection();
			//②准备SQL
			String sql = "SELECT tb_order_detail.`id`  detailId,tb_order_detail.*,tb_food.*   "
					+ " FROM tb_order_detail " + 
					" LEFT JOIN tb_food ON tb_food.`id`=tb_order_detail.`food_id`" + 
					" WHERE tb_order_detail.orderId = ? ";
			
			//③ 获取集装箱
			 preparedStatement = connection.prepareStatement(sql.toString());
			//索引从1开始
			 preparedStatement.setInt(1, orderId);
			
			//④ 执行SQL语句,返回影响的行数
			 resultSet = preparedStatement.executeQuery();
			 List<OrderDetail> orderDetails = new ArrayList<>();
			while (resultSet.next()) {
				OrderDetail orderDetail = new OrderDetail();  //封装订单明细
				orderDetail.setBuyNum(resultSet.getInt("buyNum"));
				orderDetail.setOrderId(resultSet.getInt("orderId"));
				orderDetail.setFoodId(resultSet.getInt("food_id"));
				orderDetail.setDiscount(resultSet.getDouble("discount"));
				
				Food food = new Food();
				food.setFoodTypeId(resultSet.getInt("foodType_id"));
				food.setFoodName(resultSet.getString("food_name"));
				food.setImg(resultSet.getString("img"));
				food.setDiscount(resultSet.getDouble("discount"));
				food.setPrice(resultSet.getDouble("price"));
				food.setRemark(resultSet.getString("remark"));
				
				orderDetail.setFood(food);
				orderDetails.add(orderDetail);		
			}
			return orderDetails;   //dao返回到service
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection,preparedStatement,resultSet );
		}
		return null;
	}

	@Override
	public Order findById(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			//①获取连接
			 connection = ConnectionFactory.getConnection();
			//②准备SQL
			String sql = "SELECT * FROM tb_order WHERE id = ?";
			
			//③ 获取集装箱
			 preparedStatement = connection.prepareStatement(sql.toString());
			//索引从1开始
			 preparedStatement.setInt(1, id);
			
			//④ 执行SQL语句,返回影响的行数
			 resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Order order = new Order();
				order.setId(resultSet.getInt("id"));
				order.setOrderCode(resultSet.getString("order_code"));
				order.setOrderDate(resultSet.getTimestamp("order_Date"));
				order.setOrderStatus(resultSet.getInt("order_Status"));
				order.setTableId(resultSet.getInt("table_id"));
				order.setTotalPrice(resultSet.getDouble("total_Price"));
				order.setPayDate(resultSet.getTimestamp("pay_date"));
				order.setDisabled(resultSet.getInt("disabled"));
				order.setCreatUserId(resultSet.getInt("create_userId"));
				return order;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection,preparedStatement,resultSet );
		}
		return null;
	}
	
	@Override
	public void pay(Order order) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			//①获取连接
			 connection = ConnectionFactory.getConnection();
			//设置收到提交
			 connection.setAutoCommit(false);
			//②准备SQL
			String sql = "UPDATE tb_order SET order_Status = ?,order_code = ?,table_id=?,total_Price=?,order_date=?,pay_date=now(),update_date=now(),disabled=? WHERE id = ?";
			
			//③ 获取集装箱
			 preparedStatement = connection.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
			 
			 //索引从1开始
			 preparedStatement.setInt(1, order.getOrderStatus());
			 preparedStatement.setString(2, order.getOrderCode());
			 preparedStatement.setInt(3, order.getTableId());
			 preparedStatement.setDouble(4, order.getTotalPrice());
			 preparedStatement.setTimestamp(5, new Timestamp(order.getOrderDate().getTime()));
			 preparedStatement.setInt(6, order.getDisabled());
			 preparedStatement.setInt(7, order.getId());
			 
			//④ 执行SQL语句,返回影响的行数
			int result = preparedStatement.executeUpdate();
			
			//已付完款，用户走了，餐桌状态该为未使用
			DinnerTableDao dinnerTableDao = new DinnerTableDaoImpl();
			DinnerTable dinnerTable= dinnerTableDao.findById(order.getTableId());
			dinnerTable.setTableStatus(ConstantUtil.TABLE_NO_USE);
			
			dinnerTableDao.update(dinnerTable);
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection,preparedStatement,resultSet );
		}
	}

	
	//取消订单
	@Override
	public void deleteOrder(Order order) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			//①获取连接
			 connection = ConnectionFactory.getConnection();
			//设置收到提交
			 connection.setAutoCommit(false);
			//②准备SQL
			String sql = "UPDATE tb_order SET disabled=?,update_date=now() WHERE id = ?";
			
			//③ 获取集装箱
			 preparedStatement = connection.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
			 
			 //索引从1开始
			 preparedStatement.setInt(1, order.getDisabled());
			 preparedStatement.setInt(2, order.getId());
			 
			//④ 执行SQL语句,返回影响的行数
			int result = preparedStatement.executeUpdate();
			
			//取消订单，用户走了，餐桌状态该为未使用
			DinnerTableDao dinnerTableDao = new DinnerTableDaoImpl();
			//查询餐桌通过餐桌的id
			DinnerTable dinnerTable= dinnerTableDao.findById(order.getTableId());
			dinnerTable.setTableStatus(ConstantUtil.TABLE_NO_USE);
			
			dinnerTableDao.update(dinnerTable);
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection,preparedStatement,resultSet );
		}
	}

	@Override
	public List<Order> findAll(Integer userId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			//①获取连接
			 connection = ConnectionFactory.getConnection();
			//②准备SQL
			StringBuffer sql = new StringBuffer("SELECT * FROM tb_order where DISABLED=0 ");
			if(userId != null  &&  userId != 1) {
				sql.append(" and create_userId = ?");
			}
			sql.append("  ORDER BY order_Date DESC");
			//③ 获取集装箱
			 preparedStatement = connection.prepareStatement(sql.toString());
			 if(userId != null && userId != 1) {
				//索引从1开始
				 preparedStatement.setInt(1, userId);
			 }
			//④ 执行SQL语句,返回影响的行数
			 resultSet = preparedStatement.executeQuery();
			 List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				Order order = new Order();
				order.setId(resultSet.getInt("id"));
				order.setOrderCode(resultSet.getString("order_code"));
				order.setOrderDate(resultSet.getTimestamp("order_Date"));
				order.setOrderStatus(resultSet.getInt("order_Status"));
				order.setTableId(resultSet.getInt("table_id"));
				order.setTotalPrice(resultSet.getDouble("total_Price"));
				order.setPayDate(resultSet.getTimestamp("pay_date"));
				order.setDisabled(resultSet.getInt("disabled"));
				order.setCreatUserId(resultSet.getInt("create_userId"));
				orders.add(order);
			}
			return orders;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection,preparedStatement,resultSet );
		}
		return null;
	}

}
