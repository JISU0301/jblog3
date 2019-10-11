<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script
	src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"
	type="text/javascript"></script>

<script>

$(function(){
	getList(1);
	$("#btn-submit-category").click(function() {
		var name = $("#category-name").val();
		var info = $("#category-info").val();
		if(name == "" || info == "") {
			alert("입력하세요");
			return;
		}
		
		var category = JSON.stringify({
			"name" : name,
			"info" : info
		});


		// ajax 통신
		$.ajax({
			url: "${pageContext.servletContext.contextPath }/api/category/add" ,
			type: "post",
			dataType: "json",
			data: category,
			contentType: "application/json;charset=UTF-8",
			success: function(data, response){
				if(response.result == "fail"){
					console.error(response.message);
					return;
				}
				
					$("#category-name").val("");
					$("#category-info").val("");
					$("#category-name").focus();
					getList(1);
				},
				error: function(xhr, error) {
					console.error("error:"+error);
				}
	
	});
});
	
</script>



</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blognav.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/blogmenu.jsp" />
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		<c:forEach items='${list }' var='list'>
					<tr>
						<td>${list.no }</td>
						<td>${list.name }</td>
						<td>${list.contents }</td>
						<td><img src="${pageContext.request.contextPath}/assets/images/delete.jpg"></td>
					</tr>
					</c:forEach> 
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input type="submit" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table> 
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>