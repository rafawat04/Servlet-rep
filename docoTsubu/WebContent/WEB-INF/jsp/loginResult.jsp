<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%@ page import ="model.User" %>
 <% User loginUser = (User) session.getAttribute("loginUser"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>どこつぶログイン</h1>
<%if(loginUser != null){ %>
<p>ログイン成功しました</p>
<p>ようこそ<%=loginUser.getName() %>さん</p>
<a href="/docoTsubu/Main">つぶやき投稿・観覧へ</a>
<%}else{ %>
<p>ログイン失敗しました</p>
<a href="/docoTsubu/">つTOPへ</a>
<%} %>
</body>
</html>