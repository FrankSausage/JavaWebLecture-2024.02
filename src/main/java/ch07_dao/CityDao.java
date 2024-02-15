package ch07_dao;
/*
 * Web에서 DB를 액세스하는 방법 : DBCP(DataBase Connection Pool)
 * 
 * 		1. webapp/WEB-INF/lib 에 database library (.jar) 추가
 * 		2. Tomcat server의 context.xml 수정
 * */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class CityDao {
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup("java:comp/env/" + "jdbc/world");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public City getCity(int id) {
		Connection conn = getConnection();
		String sql = "select * from kcity where id=?";
		City city = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				city = new City();
				city.setId(rs.getInt(1));
				city.setName(rs.getString(2));
				city.setCountryCode(rs.getString(3));
				city.setDistrict(rs.getString(4));
				city.setPopulation(rs.getInt(5));
			}
			rs.close(); pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return city;
	}
	
	public List<City> getCityList(String district, int num, int offset){
		Connection conn = getConnection();
		String sql = "select * from kcity where district=? limit ? offset ?";
		List<City> cList = new ArrayList<City>();
		City city = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, district);
			pstmt.setInt(2, num);
			pstmt.setInt(3, offset);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				city = new City();
				city.setId(rs.getInt(1));
				city.setName(rs.getString(2));
				city.setCountryCode(rs.getString(3));
				city.setDistrict(rs.getString(4));
				city.setPopulation(rs.getInt(5));
				cList.add(city);
			}
			conn.close(); rs.close(); pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cList;
	}
	
	public void insertCity(City city) {
		Connection conn = getConnection();
		String sql = "insert into kcity values(default, ?, ?, ? ,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, city.getName());
			pstmt.setString(2, city.getCountryCode());
			pstmt.setString(3, city.getDistrict());
			pstmt.setInt(4, city.getPopulation());
			
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteCity(int id) {
		Connection conn = getConnection();
		String sql = "delete from kcity where id=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public void updateCity(City city) {
		System.out.println(city);
		Connection conn = getConnection();
		String sql = "update kcity set name=?, countryCode=?, district=?, population=? where id =?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, city.getName());
			pstmt.setString(2, city.getCountryCode());
			pstmt.setString(3, city.getDistrict());
			pstmt.setInt(4, city.getPopulation());
			pstmt.setInt(5, city.getId());
			
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}

