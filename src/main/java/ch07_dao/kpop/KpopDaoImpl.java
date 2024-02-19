package ch07_dao.kpop;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class KpopDaoImpl implements KpopDao {
	
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
	
	@Override
	public List<Kpop> getKpopList() {
		Connection conn = getConnection();
		String sql = "SELECT gid, NAME, debut, sid, title, lyrics FROM girl_group" +
				" JOIN song ON girl_group.hit_song_id=song.sid";
		List<Kpop> kpList = new ArrayList<>();
		Kpop kp = null;
		try {
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				kp = new Kpop();
				kp.setAid(rs.getInt(1));
				kp.setName(rs.getString(2));
				kp.setDebut(rs.getDate(3).toLocalDate());
				kp.setSid(rs.getInt(4));
				kp.setTitle(rs.getString(5));
				kp.setLyrics(rs.getString(6));
				kpList.add(kp);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kpList;
	}

	@Override
	public Artist getArtist(int aid) {
		Connection conn = getConnection();
		String sql = "select * from girl_group where gid=?";
		Artist arti = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, aid);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()){
				arti = new Artist();
				arti.setAid(rs.getInt(1));
				arti.setName(rs.getString(2));
				arti.setDebut(rs.getDate(3).toLocalDate());
				arti.setHitSongId(rs.getInt(4));
			}
			pstmt.close(); rs.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arti;
	}

	@Override
	public Song getSong(int sid) {
		Connection conn = getConnection();
		String sql = "select * from song where sid=?";
		Song song = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sid);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()){
				song = new Song();
				song.setSid(rs.getInt(1));
				song.setTitle(rs.getString(2));
				song.setLyrics(rs.getString(3));
			}
			pstmt.close(); rs.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return song;
	}

	@Override
	public void insertArtist(Artist artist) {
		Connection conn = getConnection();
		String sql = "insert into girl_group values(default, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,artist.getName());
			pstmt.setString(2, artist.getDebut().toString());
			pstmt.setInt(3,artist.getHitSongId());

			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertSong(Song song) {
		Connection conn = getConnection();
		String sql = "insert into song values(default, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, song.getTitle());
			pstmt.setString(2, song.getLyrics());

			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateArtist(Artist artist) {
		Connection conn = getConnection();
		String sql = "update girl_group set name=?, debut=?, hit_song_id=? where gid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,artist.getName());
			pstmt.setString(2, artist.getDebut().toString());
			pstmt.setInt(3,artist.getHitSongId());
			pstmt.setInt(4, artist.getAid());

			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateSong(Song song) {
		Connection conn = getConnection();
		String sql = "update song set title=?, lyrics=? where sid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, song.getTitle());
			pstmt.setString(2, song.getLyrics());
			pstmt.setInt(3, song.getSid());
			
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteArtist(int aid) {
		Connection conn = getConnection();
		String sql = "delete from girl_group where gid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, aid);

			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteSong(int sid) {
		Connection conn = getConnection();
		String sql = "delete from song where sid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sid);

			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
