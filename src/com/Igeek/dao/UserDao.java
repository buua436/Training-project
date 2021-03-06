package com.Igeek.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.Igeek.bean.User;
import com.Igeek.utils.DaoUtils;


public class UserDao {
	//添加用户
	public int insertUser(User user)
	{
		QueryRunner runner = new QueryRunner(DaoUtils.dataSource);
		String sql="insert into users values(?,?,?,?,?)";
		try {
			return runner.update(sql,user.getUserName(),user.getPassWord(),user.getUserMoney(),user.getYesOrno(),user.getUserTel());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	//根据用户名查询用户
	public User findByName(String name) {
		// TODO Auto-generated method stub
		QueryRunner runner = new QueryRunner(DaoUtils.dataSource);
		String sql = "select * from users where username=? ";
		try {
			return runner.query(sql, new BeanHandler<User>(User.class),name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//更改用户余额
	public int updateUser(User user,double balance)
	{
		QueryRunner runner = new QueryRunner(DaoUtils.dataSource);
		String sql="update user set balance=? where username=?";
		try {
			return runner.update(sql,balance,user.getUserName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	
}
