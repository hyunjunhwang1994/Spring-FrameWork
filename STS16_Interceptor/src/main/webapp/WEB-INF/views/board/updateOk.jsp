<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${result == 0 }">
	<script>
	alert("수정 실패!!!!");
	history.back();		// 브라우저가 직전에 기억하는 페이지로 이동
	</script>
</c:if>


<c:if test="${result > 0 }">
	<script>
		alert("수정성공");
		location.href = "view.do?id=" + ${dto.id };
	</script>
</c:if>


