<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="old.*" %>


<%	// Controller 로부터 결과 데이터 받아옴.
	WriteDTO [] arr = (WriteDTO [])request.getAttribute("list");
%>



<%
	if(arr == null || arr.length == 0){
%>
		<script>
			alert("해당 글은 삭제되거나 존재하지 않습니다.");
			history.back();
		</script>	
<%
		return;		// 더이상 JSP 프로세싱 하지 않고 종료.
	} // end if
%>

<% 
	int id = arr[0].getId();	
	String name = arr[0].getName();
	String subject = arr[0].getSubject();
	String content = arr[0].getContent();
	String regdate = arr[0].getRegDate();
	int viewcnt = arr[0].getViewCnt();
%>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title><%= subject %></title>
</head>

<script>
function chkDelete(id){
	// 삭제여부, 확인하고 진행하기
	var r = confirm("삭제하시겠습니까?");
	
	if(r){
		location.href = "deleteOk.do?id=" + id;
	}
	
}

</script>

<body>

<h2><%= subject %></h2><br>
id : <%= id %><br>
작성자 : <%= name %><br>
제목 : <%= subject %><br>
등록일 : <%= regdate %><br>
조회수 : <%= viewcnt %><br>
내용 : <br>
<hr>
<div>
<%= content %>
</div>
<hr><br>
<button onclick="location.href = 'update.do?id=<%= id %>'">글 수정하기</button>
<button onclick="location.href = 'list.do'">목록보기</button>
<button onclick="location.href = 'write.do'">새글등록</button>
<button onclick="chkDelete(<%= id %>)">글 삭제하기</button>


</body>
</html>




















