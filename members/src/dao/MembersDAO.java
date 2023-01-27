package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Members;


public class MembersDAO {
	private Connection db;
	private PreparedStatement ps;
	private ResultSet rs;

	private void connect() throws NamingException, SQLException{
		Context context = new InitialContext();
		DataSource ds =(DataSource) context.lookup("java:comp/env/jdbc/jsp");
		this.db = ds.getConnection();
	}

	private void disconnect() {
		try {
		if(rs!= null) {
			rs.close();
		}
		if(ps!=null) {
			ps.close();
		}
		if(db!= null) {
			db.close();
		}
	 } catch (SQLException e) {
		 e.printStackTrace();
	 }
	}

	public List<Members> findAll(){
		List<Members> list= new ArrayList<>();
		try {
			this.connect();

		ps=db.prepareStatement("SELECT * FROM members");
		rs=ps.executeQuery();
		while(rs.next()) {
			int id=rs.getInt("id");
			String name = rs.getString("name");
			String depart = rs.getString("depart");
			int age = rs.getInt("age");
			String updated = rs.getString("updated");
			list.add(new Members(id,name,depart,age,updated));

			}
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
		return list;
	}

	public String getUpdated() {
		Date now=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
		String updated = sdf.format(now);
		return updated;
	}
	public void insertOne(Members members) {
		try {
		this.connect();
		ps=db.prepareStatement("INSERT INTO members(name,depart,age,updated) VALUES(?,?,?,?)");
		ps.setString(1,members.getName());
		ps.setString(2, members.getDepart());
		ps.setInt(3, members.getAge());
		ps.setString(4, members.getUpdated());
		ps.execute();
		}catch(NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
	}

	public Members findOne(int id) {
		Members members = null;
		try {
			this.connect();
			ps=db.prepareStatement("SELECT * FROM members WHERE id=?");
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()) {
				String name = rs.getString("name");
				String depart = rs.getString("depart");
				int age = rs.getInt("age");
				String updated = rs.getString("updated");
				members=new Members(id,name,depart,age,updated);
			}
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
		return members;
	}

	public void updateOne(Members members) {
		try {
			this.connect();
			ps=db.prepareStatement("UPDATE members SET name=?,depart=?,age=?,updated=? WHERE id=?");
			ps.setString(1,members.getName());
			ps.setString(2, members.getDepart());
			ps.setInt(3, members.getAge());
			ps.setString(4, members.getUpdated());
			ps.setInt(5, members.getId());
			ps.execute();
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
	}

	public void deleteOne(int id) {
		try {
			this.connect();
			ps=db.prepareStatement("DELETE FROM members WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
	}
}
