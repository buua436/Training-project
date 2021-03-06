package com.Igeek.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.Igeek.bean.Car;
import com.Igeek.bean.CarType;
import com.Igeek.utils.DaoUtils;

public class CarDao {
	// 最新发布的10两二手车
	public List<Car> findLastedTenCars() {
		QueryRunner runner = new QueryRunner(DaoUtils.dataSource);
		String sql = "select c.* from car c where c.isselled=0 order by releaseDate desc limit 10";
		try {
			return runner.query(sql, new BeanListHandler<Car>(Car.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 根据价格查询
	public List<Car> findByPrice(int min, int max) {
		// TODO Auto-generated method stub
		QueryRunner runner = new QueryRunner(DaoUtils.dataSource);
		String sql = "select c.* from car c where  c.price between ? and ? and c.isselled=0 order by releaseDate desc limit 10";
		try {
			return runner.query(sql, new BeanListHandler<Car>(Car.class), min, max);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 根据车型查询
	public List<Car> findByBrand(CarType type) {
		// TODO Auto-generated method stub
		QueryRunner runner = new QueryRunner(DaoUtils.dataSource);
		String sql = "select c.* from car c where c.typeid=? and c.isselled=0 order by releaseDate desc limit 10";
		try {
			return runner.query(sql, new BeanListHandler<Car>(Car.class), type.getTypeID());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 根据上牌日期查询
	public List<Car> findByDate(int firstYear, int firstMonth, int secondYear, int secondMonth) {
		QueryRunner runner = new QueryRunner(DaoUtils.dataSource);
		String sql = "select c.* from car c where c.isselled=0 and DATE_FORMAT(releaseDate,'%Y') BETWEEN ? and ? and DATE_FORMAT(releaseDate,'%m') BETWEEN ? and ? order by releaseDate desc limit 10";
		try {
			return runner.query(sql, new BeanListHandler<Car>(Car.class),firstYear,secondYear,firstMonth,secondMonth);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 根据车辆Id查询
	public Car findByCarId(int id) {
		// TODO Auto-generated method stub
		QueryRunner runner = new QueryRunner(DaoUtils.dataSource);
		String sql = "select c.* from car c where c.carid=? and c.isselled=0 order by releaseDate desc limit 10";
		try {
			return runner.query(sql, new BeanHandler<Car>(Car.class), id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 卖出车辆
	public int updateIsBought(int id) {
		QueryRunner runner = new QueryRunner(DaoUtils.dataSource);
		String sql = "update car set isselled=1 where carid=?";
		try {
			return runner.update(sql, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	//删除车型车辆
	public int deleteByType(int typeid)
	{
		QueryRunner runner = new QueryRunner(DaoUtils.dataSource);
		String sql = "delete from car where typeid=?";
		try {
			return runner.update(sql, typeid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;	
	}
	//插入车辆
		public int insertCar(Car car)
		{
			QueryRunner runner = new QueryRunner(DaoUtils.dataSource);
			String sql="insert into car values(?,?,?,?,?,?,?,?,?,?,?)";
			try {
				return runner.update(sql,car.getCarID(),car.getDisplacement(),car.getMileage(),car.getPrice(),car.getReleaseDate(),car.getLicenceDate(),car.getCluth(),0,car.getTypeID(),car.getBrandName(),car.getTypeName());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}
}
