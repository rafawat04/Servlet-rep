package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MembersDAO;
import model.Members;

/**
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s_id=request.getParameter("id");
		if(s_id == null) {
			response.sendRedirect("/members/Read");
		}else {
			MembersDAO dao = new MembersDAO();
			Members members = dao.findOne(Integer.parseInt(s_id));
			request.setAttribute("members", members);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/update.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id= Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		String depart = request.getParameter("depart");
		int age= Integer.parseInt(request.getParameter("age"));
		String updated = request.getParameter("updated");

		Members members=new Members(id,name,depart,age,updated);

		MembersDAO dao=new MembersDAO();
		dao.updateOne(members);

		response.sendRedirect("/members/Read");

	}

}
