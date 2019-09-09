<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	// Controller 로 부터 결과 데이터 받아옴.
	int cnt = (Integer)request.getAttribute("result");
	int id = Integer.parseInt(request.getParameter("id"));
%>

<!-- exception or 실행된 쿼리가 없을때 -->
<%if(cnt == 0){ %> 
	<script>
	alert("수정 실패!!!!");
	history.back();		// 브라우저가 직전에 기억하는 페이지로 이동
	</script>
<%}else{%>
	<script>
		alert("수정성공, 리스트 출력합니다.");
		location.href = "view.do?id=<%= id %>";
	</script>
<%}%>
<!-- write.jsp -> run on server -->




