<%-- 科目登録画面 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>
	<c:param name="content">
	<section class="mp-4">
	<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>
	<form action="SubjectCreateExecute.action" method="post">
	<p>科目コード<br><input type="text" name="cd" value="" id="subject_cd"
	placeholder="科目コードを入力してください" size="95" maxlength="3" required></p>
	<p>科目名<br><input type="text" name="name" value="" id="subject_name"
	placeholder="科目名を入力してください" size="95" maxlength="20" required></p>

	<input type="button" onclick="location.href='href'../studentmanager/subject_create_done.jsp"
	value="登録" name="create_btn">
	<a href="../scoremanager.main/Subjectlist.action">戻る</a>

	<%--
	<p><input type="button" value="Check" id="checkButton"></p>

	<p id="msg"></p>

	<script>
	function butotnClick(){
	  msg.innerText = 'お名前は' + nameText.value + 'さんですね';
	}

	let nameText = document.getElementById('nameText');
	let msg = document.getElementById('msg');

	let checkButton = document.getElementById('checkButton');
	checkButton.addEventListener('click', butotnClick);
	</script>
	--%>

	</c:param>
</c:import>