<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*,java.util.*"%>
<%
List<Members> list = (List<Members>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Members Table</title>
</head>
<body>
<a href="/members/Create">新規登録</a>
<%if(list !=null && list.size() > 0){ %>
<table border="1">
<%for(Members m:list){ %>
<tr>
<td><%=m.getId() %></td>
<td><%=m.getName() %></td>
<td><%=m.getDepart() %></td>
<td><%=m.getAge() %></td>
<td><%=m.getUpdated() %></td>
<td><a href="/members/Update?id=<%=m.getId()%>">更新</a>
<a href="/members/Delete?id=<%=m.getId()%>" onclick="return confirm('id=<%=m.getId()%>を削除してよろしいですか？');">削除</a></td>
</tr>
<%} %>
</table>
<%} %>
</body>
</html>