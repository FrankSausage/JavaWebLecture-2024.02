package project.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import project.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
	
    public Connection getConnection() {
        Connection conn = null;
        try {
            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/" + "jdbc/bbs");
            conn = ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }
    
    public User getUserByUid(String uid) {
    	Connection conn = getConnection();
        String sql = "select * from users where uid=?";
        User user = null;
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, uid);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                user = new User();
                user.setUid(rs.getString(1));
                user.setPwd(rs.getString(2));
                user.setUname(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setRegDate(rs.getDate(5).toLocalDate());
                user.setIsDeleted(rs.getInt(6));
            }
            pstmt.close(); rs.close(); conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getUserList(int num, int offset) {
    	Connection conn = getConnection();
        String sql = "select * from users where isDeleted=0" +
                    " order by regDate desc, uid limit ? offset ?";
        List<User> uList = new ArrayList<>();
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, num);
            pstmt.setInt(2, offset);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                User user = new User();
                user.setUid(rs.getString(1));
                user.setPwd(rs.getString(2));
                user.setUname(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setRegDate(rs.getDate(5).toLocalDate());
                user.setIsDeleted(rs.getInt(6));
                uList.add(user);
            }
            pstmt.close(); rs.close(); conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return uList;
    }

    public void insertUser(User user) {
    	Connection conn = getConnection();
        String sql = "insert into users values(?, ?, ?, ?, default, default)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUid());
            pstmt.setString(2, user.getPwd());
            pstmt.setString(3, user.getUname());
            pstmt.setString(4, user.getEmail());

            pstmt.executeUpdate();
            pstmt.close(); conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void updateUser(User user) {
    	Connection conn = getConnection();
        String sql = "update users set pwd=?, uname=?, email=? where uid=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getPwd());
            pstmt.setString(2, user.getUname());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUid());

            pstmt.executeUpdate();
            pstmt.close(); conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteUser(String uid) {
    	Connection conn = getConnection();
        String sql = "delete from users where uid=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,uid);

            pstmt.executeUpdate();
            pstmt.close(); conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
