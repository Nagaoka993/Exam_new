<%--科目一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
	<%@page import="bean.Subject, java.util.List" %>
		<section class=mp-4>
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目管理</h2>
			<div class="my-2 text-end px-4">
				<a href="../scoremanager.main/SubjectCreate.action">新規登録</a>
			</div>
			<form action="Subjectlist.action">
				<div class="row border mx-3 mb-3 py-2 aligin-items-center rounded" id ="filter">
					<div class="col-4">
					<style>
					td {
  					border-bottom: 1px solid #000; /* 下線のスタイル */
  					padding-bottom: 5px; /* 下線とテキストの間隔 */
						}
					</style>
						<label class="form-label" for=subject-f1-select></label>
						<table border="1">
						<tr>
							<th>科目コード</th>
							<th>科目名</th>
						</tr>
						<c:forEach var="subject" items="${subject}">
						<tr>
							<td>${subject.cd}</td>
							<td>${subject.name}</td>
						</tr>
						</c:forEach>
					</table>
					</div>
				</div>
				</form>
		</section>
	</c:param>
</c:import>