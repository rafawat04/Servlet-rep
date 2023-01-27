<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.Members"%>

    <%
    Members members = (Members)request.getAttribute("members");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Members Table</title>
</head>
<body>
<form action="/members/Update" method="post">

名前:<input type="text" name="name" value="<%=members.getName() %>"><br>
部署:<input type="text" name="depart" value="<%=members.getDepart() %>"><br>
年齢:<input type="number" name="age" value="<%=members.getAge() %>"><br>
更新:<input type="text" name="updated" value="<%=members.getUpdated() %>"><br>
<input type="hidden" name="id" value="<%=members.getId() %>"><br>
<button type="submit">更新</button>
</form>

</body>
</html>