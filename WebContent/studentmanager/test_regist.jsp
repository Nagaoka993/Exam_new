<%--成績管理一覧画面--%>
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
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
			<style>
			.search-container {
			  display: flex;
			}
			</style>
	<form action="TestRegistAction" method="post">
		<div class="search-container">
			<div class="mx-3 py-2"> <label class="form-label" for="year">入学年度</label>
				<select name="f1" id="year" style="width: 100px;">
					<c:forEach var="year" items="${yearList}">
						<option value="${year}">${year}--------</option>
					</c:forEach>
				</select>
			</div>

			<div class="mx-3 py-2"> <label class="form-label" for="num">クラス</label>
				<select name="f2" id="num">
					<c:forEach var="num" items="${numList}">
						<option value="${num}">${num}</option>
					</c:forEach>
				</select>
			</div>

			<div class="mx-3 py-2"> <label class="form-label" for="subject.cd">科目</label>
				<select name="f3" id="subject.cd">
					<c:forEach var="subject.cd" items="${subject.cdList}">
						<option value="${subject.cd}">${subject.cd}</option>
					</c:forEach>
				</select>
			</div>

			<div class="mx-3 py-2"> <label for="subject.cd">回数</label>
				<select name="f4" id="num">
					<c:forEach var="num" items="${numList}">
						<option value="${num}">${num}</option>
					</c:forEach>
				</select>
			</div>

		</div>
	</form>

		</section>
	</c:param>
</c:import>