<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" type="image/x-icon" href="images/SiSt.ico">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script   src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<link href="/resources/dipin/example.css" type="text/css" rel="stylesheet" />
<script   src="/resources/dipin/example.js"></script>
</head>
<body>
<div>

  <div>
  	<h1 class='main'>JSP DEPT TEST</h1>
	<table border="1" style="width:100%">
		<tr>
			<th>deptno</th>
			<th>dname</th>
			<th>loc</th>
		</tr>
		<c:forEach items="${list}" var="vo">
			<tr>
				<td>${vo.deptno}</td>
				<td>${vo.dname}</td>
				<td>${vo.loc}</td>
			</tr>
		</c:forEach>
	</table>
  </div>


</div> 
</body>
</html>
