<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:choose>
	<c:when test="${result == 0}">
		<script>
			alert("삭제실패!!!!");
			history.back();		// 브라우저가 직전에 기억하는 페이지로 이동
		</script>
	</c:when>
	
	<c:otherwise>
		<script>
			alert("삭제성공, 리스트 출력합니다.");
			location.href = "list.do";
		</script>
	</c:otherwise>
</c:choose>




