<%--学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class=mp-4>
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">ログアウト</h2>
			<p class="  fw-norma bg-success bg-opacity-10 py-2 px-2" style="text-align: center; ">ログアウトしました</p>

		</section>
		<br>
		<br>
		<a href="login.jsp">ログイン</a>
	</c:param>
</c:import>