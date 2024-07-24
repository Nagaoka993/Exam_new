<%-- メインメニュー --%>
<%@page import="bean.Teacher, java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../common/base.jsp">


	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
	<section class="mp-4">
	<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">メニュー</h2>
	<a href="../scoremanager.main/Studentlist.action">学生管理</a>
	成績管理
	<a href="../TestRegist.action">成績登録</a>
	<a href="TestList.action">成績参照</a>

	<a href="../scoremanager.main/Subjectlist.action">科目管理</a>

	</section>
	</c:param>

</c:import>
