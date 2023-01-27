<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Members Table</title>
</head>
<body>

<form action="/members/Create" method="post">
名前:<input type="text" name="name"><br>
部署:<input type="text" name="depart"><br>
年齢:<input type="text" name="age"><br>
更新:<input type="date" name="updated"
        placeholder="yyyy-mm-dd" min="1950-01-01" max="2022-12-31"><br>
  <input type="submit" value="登録">
</form>
</body>
</html>