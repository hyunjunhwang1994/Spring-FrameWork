<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="old.*" %>

<%
	// controller가 보내준 request안 데이터 뽑아냄
	WriteDTO [] arr = (WriteDTO[])request.getAttribute("list"); // object 리턴

%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>글 목록</title>
<style>
table, th, td{
	border : 1px solid black;
	border-collapse: collapse;
}
</style>
</head>
<body>
<hr>
<h2>리스트</h2>
<%

	if(arr == null || arr.length == 0){
		out.println("데이터가 없습니다<br>");
	}else{
%>



<table style="width:100%;">
	<tr>
		<th>번호</th> <!-- 원래는 실제로 내부에서 사용되는 고유 id값(pk)는 보여주지않는다.(내부처리용)
					  따로 Row Number(줄넘버)를 만들어서 보여줘야하지만 예제이므로 그냥 고유 ID값을 쓰겠다. -->
		<th>제목</th>
		<th>작성자</th>
		<th>조회수</th>
		<th>등록일</th>
	</tr>
<% 
	for(int i = 0; i < arr.length; i++){
		out.println("<td>" + arr[i].getId() + "</td>");
		out.println("<td><a href='view.do?id=" + arr[i].getId() + "'>" + arr[i].getSubject() + "</a></td>");
		out.println("<td>" + arr[i].getName() + "</td>");
		out.println("<td>" + arr[i].getViewCnt() + "</td>");
		out.println("<td>" + arr[i].getRegDate() + "</td>");
		out.println("</tr>");			
	} // end for()

		
%>
</table>
<%
	} // end if()
%>
<br>
<button onclick="location.href = 'write.do'">새글 등록</button>		
</body>
</html>







