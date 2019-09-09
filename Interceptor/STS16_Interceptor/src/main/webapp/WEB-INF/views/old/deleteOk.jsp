<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="old.*" %>


<%
	int cnt = (Integer)request.getAttribute("result");

%>

<!-- exception or 실행된 쿼리가 없을때 -->
<%if(cnt == 0){ %> 
	<script>
	alert("삭제실패!!!!");
	history.back();		// 브라우저가 직전에 기억하는 페이지로 이동
	</script>
<%}else{%>
	<script>
		alert("삭제성공, 리스트 출력합니다.");
		location.href = "list.do";
	</script>
<%}%>