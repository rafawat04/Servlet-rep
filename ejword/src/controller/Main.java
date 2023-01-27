package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.WordDAO;
import model.LinkItem;
import model.Word;

@WebServlet("/main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int LIMIT = 20;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String searchWord = (String) request.getParameter("searchWord");
		if (searchWord != null) {
			String mode = (String) request.getParameter("mode");
			if(mode ==null){
				mode="startsWith";
			}
			String page=(String)request.getParameter("page");
			int pageNo=page==null? 1:Integer.parseInt(page); //計算するためにIntにしています
			WordDAO dao = new WordDAO();
			int total = dao.getTotal(searchWord, mode);
			List<Word> list = dao.getListBySearchWord(searchWord, mode, LIMIT,(pageNo-1)*LIMIT);
			request.setAttribute("total", total);
			request.setAttribute("limit", LIMIT);
			request.setAttribute("searchWord", searchWord);
			request.setAttribute("mode", mode);
			request.setAttribute("list", list);
			request.setAttribute("pageNo",pageNo);
			List<LinkItem> items = new ArrayList<>();
			int lastPage = (total -1)/LIMIT +1;
			if(lastPage <=11) {
			for(int i =1;i<=lastPage;i++) {
				items.add(new LinkItem(i,i==pageNo));
			}
			}else {
				for(int i=Math.max(1, pageNo -5);i<=Math.min(pageNo+5, lastPage);i++) {
					items.add(new LinkItem(i,i==pageNo));
				}
			}
			request.setAttribute("items", items);
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/main.jsp");
		rd.forward(request, response);
	}
}
//		doGet
//		request.setCharacterEncoding("UTF-8");
//		//Userが入力した単語の受け取り
//		String searchWord = request.getParameter("searchWord");
//		//User 選択したmodeの受け取り(startswith,contains,endWith,match)
//		String mode = request.getParameter("mode");
//		//dao Instance generate
//		WordDAO dao = new WordDAO(); //getListBySearchWord使うためにWordDAO生成しています
//		int total = dao.getTotal(searchWord, mode);
//		List<Word> list = dao.getListBySearchWord(searchWord, mode, LIMIT);
//		//request scope total 件数
//		request.setAttribute("total", total);
//		//request scope　ページあたり取得件数を保存
//		request.setAttribute("limit", LIMIT);
//		//request scope にlistを保存
//		request.setAttribute("searchWord", searchWord);
//		//request scopeにmodeを保存
//		request.setAttribute("mode", mode);
//		//request scope search word 保存
//		request.setAttribute("list", list);
//
//		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/main.jsp");
//		rd.forward(request, response);


//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//
//		doGet(request, response);
//	}


