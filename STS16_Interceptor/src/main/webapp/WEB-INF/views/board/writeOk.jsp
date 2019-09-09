<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- day 78 05 -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${result == 0 }">
	<script>
	alert("등록실패!!!!");
	history.back();		// 브라우저가 직전에 기억하는 페이지로 이동
	</script>
</c:if>

<c:if test="${result > 0 }">
	<script>
		alert("등록성공, 리스트 출력합니다.");
		location.href = "list.do";
	</script>
</c:if>


























