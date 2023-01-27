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

import model.Word;

public class WordDAO {
	//Field変数
	private Connection db; //Database の接続を保持する
	private PreparedStatement ps;//SQL文を保持&実行する変数
	private ResultSet rs;//SQL文の実行結果（Result Set）を保持&操作する変数

	//Databaseと接続するmethod
	private void connect() throws NamingException, SQLException {
		//Contextを取得
		Context context = new InitialContext();
		//DataSource を作成
		DataSource ds = (DataSource)context.lookup("java:comp/env/ejword");
		//接続処理
		this.db = ds.getConnection();
	}
	//Database 切断処理
	private void disconnect(){
		try {
			if(rs !=null) {
				rs.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(db !=null) {
				db.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//検索wordとmodeから検索結果を取得するmethod (static ないのでgenerateが必要)
	public List<Word> getListBySearchWord(String searchWord,String mode){
		List<Word> list = new ArrayList<>();
		switch(mode) {
		case "startsWith":
			searchWord=searchWord + "%";
			break;
		case "contains":
			searchWord="%"+searchWord+"%";
			break;
		case "endsWith":
			searchWord="%"+searchWord;
		}

		try {
			this.connect();
			ps=db.prepareStatement("SELECT * FROM words WHERE title LIKE ?");
			ps.setString(1, searchWord);
			//結果セットを実行
		//*******ResultSetの状態だとプログラムとして使えないので、ArrayListに入れたから初めてjavaで色々操作ができる！！********
			rs=ps.executeQuery();
			while(rs.next()) {
				String title = rs.getString("title");
				String body = rs.getString("body");
				Word w = new Word(title,body);
				list.add(w);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
		return list;
	}
	public List<Word> getListBySearchWord(String searchWord,String mode,int limit){
		List<Word> list = new ArrayList<>();
		switch(mode) {
		case "startsWith":
			searchWord=searchWord + "%";
			break;
		case "contains":
			searchWord="%"+searchWord+"%";
			break;
		case "endsWith":
			searchWord="%"+searchWord;
		}

		try {
			this.connect();
			ps=db.prepareStatement("SELECT * FROM words WHERE title LIKE ? LIMIT ?");
			ps.setString(1, searchWord);
			ps.setInt(2, limit);
			//結果セットを実行
		//*******ResultSetの状態だとプログラムとして使えないので、ArrayListに入れたから初めてjavaで色々操作ができる！！********
			rs=ps.executeQuery();
			while(rs.next()) {
				String title = rs.getString("title");
				String body = rs.getString("body");
				Word w = new Word(title,body);
				list.add(w);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
		return list;
	}

	public List<Word> getListBySearchWord(String searchWord,String mode,int limit,int offset){
		List<Word> list=new ArrayList<>();
		switch(mode) {
		case "startsWith":
			searchWord=searchWord+"%";
			break;
		case "contains":
			searchWord="%"+searchWord+"%";
			break;
		case "endsWith":
			searchWord="%"+searchWord;
		}
		try {
			this.connect();
			ps = db.prepareStatement("SELECT * FROM words WHERE title LIKE ? LIMIT ? OFFSET ?");
			ps.setString(1, searchWord);
			ps.setInt(2, limit);
			ps.setInt(3, offset);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String body = rs.getString("body");
				list.add(new Word(id, title, body));
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
				this.disconnect();

		}
		return list;
	}
	public int getTotal(String searchWord,String mode) {
		int total = 0;
		switch(mode) {
		case "startsWith":
			searchWord=searchWord + "%";
			break;
		case "contains":
			searchWord="%"+searchWord+"%";
			break;
		case "endsWith":
			searchWord="%"+searchWord;
		}

		try {
			this.connect();
			ps=db.prepareStatement("SELECT count(*) AS total FROM words WHERE title LIKE ?");
			ps.setString(1, searchWord);
			rs= ps.executeQuery();
			if(rs.next()) {
				total=rs.getInt("total");
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
		return total;
	}

}
