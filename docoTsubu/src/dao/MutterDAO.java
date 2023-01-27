package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Mutter;

public class MutterDAO {
	private Connection db; // database connection instance
	private PreparedStatement ps; //SQL文を保持/操作/etcするinstance
	private ResultSet rs; //SQLの結果を保持/操作するinstance

	private void connect() throws NamingException, SQLException{
		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:comp/env/docoTsubu");
		this.db = ds.getConnection();
	}
	private void disconnect() { // mySQL には例外処理が必要
		try {
			if(rs != null) {
				rs.close();
			}
			if(ps !=null) {
				ps.close();
			}
			if(db !=null) {
				db.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Mutter> findAll(){  //Mutter は　Mutter.java で作ったInstanceを使っている！！大事！！
		//Database用のList作っている
		List<Mutter> mutterList= new ArrayList<>();
		try {
		this.connect();
		 String sql = "SELECT * FROM mutter ORDER BY id DESC";//新しいつぶやきを上から並べていくので DESC
		this.ps=this.db.prepareStatement(sql);//its preparing the frase inside , this.ps is "PreparedStatement"
		this.rs=this.ps.executeQuery(); // after prepare set the result(ResultSet)to get 結果セットuse executeQuery();
		while(rs.next()) {
			int id=rs.getInt("id");
			String userName= rs.getString("name");
			String text= rs.getString("body");
			Mutter mutter = new Mutter(id,userName,text);
			mutterList.add(mutter);
		}
	} catch(NamingException | SQLException e) {
		e.printStackTrace();
		return null;
	}finally {
		this.disconnect();
	}
		return mutterList;
   }

	public boolean create(Mutter mutter) { //今回はvoidでも大丈夫です
		//Database 接続
		try {
			this.connect();
			String sql = "INSERT INTO mutter(name,body) VALUES(?,?)";
			this.ps=this.db.prepareStatement(sql);
			this.ps.setString(1, mutter.getUserName()); // 1 is the first "?" above
			this.ps.setString(2,mutter.getText()); // 2 is the second "?" above
			int result = ps.executeUpdate();
			if(result !=1){
				return false;
			}
		} catch (NamingException| SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			disconnect();
		}
		return true;

	}
}
