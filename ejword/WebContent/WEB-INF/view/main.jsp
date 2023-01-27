<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*,java.util.*"%>

<%
String searchWord = (String)request.getAttribute("searchWord");
searchWord=searchWord ==null?"":searchWord;
String mode=(String)request.getAttribute("mode");
mode=mode== null?"":mode;
List<Word> list=(List<Word>)request.getAttribute("list");
Integer total=(Integer)request.getAttribute("total");
Integer limit=(Integer)request.getAttribute("limit");
Integer pageNo =(Integer)request.getAttribute("pageNo");
List<LinkItem> linkItems=(List<LinkItem>)request.getAttribute("items");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'>
<link rel="stylesheet" href="/ejword/css/style.css"> <%--../css/style.css --%>
<title>EJWord</title>
</head>
<body>
<h1>EJ Dictionary</h1>
<div class="container">
<form action="/ejword/main" method="get" class="form-inline">
<input type="text" name="searchWord" value="<%=searchWord%>"class="form-control">
<select name="mode" class="custom-select my-1 mr-sm-2">
<option value="startsWith"<%=mode.equals("startsWith")?" selected":"" %>>で始まる</option>
<option value="contains"<%=mode.equals("contains")?" selected":"" %>>含む</option>
<option value="endsWith"<%=mode.equals("endsWith")?" selected":"" %>>で終わる</option>
<option value="match"<%=mode.equals("match")?" selected":"" %>>一致する</option>
</select>
<button type="submit"class="btn btn-primary" data-toggle="button" aria-pressed="false" >検索</button>
</form>
<% if (total !=null && total == 0){ %>
<p>一致するものはありません</p>
<%} %>
<% if((total !=null && total != 0) && (list !=null && list.size() > 0)){ %>
<% if(total <= limit){ %>
<p>全<%=total %>件</p>
<%}else{ %>
<p>全<%=total %>件中 <%=(pageNo-1)*limit+1 %>~<%=Math.min(pageNo*limit,total)%>件を表示</p>
<%--ページ番号が１より大きかったら前へのリンクを表示 --%>
<%} %>
	<%if(pageNo >=2){ %>
	<a href="/ejword/main?searchWord=<%=searchWord%>&mode=<%=mode %>&page=<%=pageNo-1 %>"><span class="btn btn-outline-primary">&larr;前へ</span></a>
<%} %>
<%if(total > pageNo * limit){ %>
<a href="/ejword/main?searchWord=<%=searchWord%>&mode=<%=mode %>&page=<%=pageNo+1 %>"><span class="btn btn-outline-primary">次へ&rarr;</span></a>
<%} %>

<table class="table table-striped table-bordered">
<% for(Word w:list){ %>
<tr><th><%=w.getTitle() %></th><td><%=w.getBody() %></td></tr>
<%} %>
</table>
</div>
<%} %>
<%if(linkItems != null && linkItems.size()>1){ %>
<div id="navBox">
<nav aria-label="Page navigation example">
  <ul class="pagination">
    <li class="page-item">
      <a class="page-link" href="/ejword/main?searchWord=<%=searchWord %>&mode=<%=mode %>&page=1" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
        <span class="sr-only">Previous</span>
      </a>
    </li>
    <%for(LinkItem item : linkItems){ %>
    <li class="page-item <%=item.isActive()?"active":""%>"><a class="page-link" href="/ejword/main?searchWord=<%=searchWord %>&mode=<%=mode %>&page=<%=item.getPageNo()%>"><%=item.getPageNo() %></a></li>
    <%} %>
    <li class="page-item">
      <a class="page-link" href="/ejword/main?searchWord=<%=searchWord %>&mode=<%=mode %>&page=<%=(total-1) / limit +1 %>" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
</div>
<%} %>
</div>
<footer>
&copy; 2022 Joytas.net
</footer>
</body>
</html>