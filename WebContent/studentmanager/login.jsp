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
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4"  style="text-align: center;">ログイン</h2>
			<form action = "../scoremanager.main/LoginExecute.action" method="post">
				<div class="mx-3 py-2">
					<div class="mb-3">
						<label class="form-label" for="id-input">ID</label>
						<input class="form-control ms-3" type="text" id="id-input" name="id" value="id" />
					</div>
					<div class="mb-3">
						<label class="form-label" for="password-input">パスワード</label>
						<input class="form-control ms-3" type="password" id="password-input" name="password" value="password" />
					</div>
					<label for="inputCheckbox"><input id="inputCheckbox" type="checkbox" name="chk_d_ps"> パスワードを表示する</label>
					<div class="mt-3">
						<input class="btn btn-primary" type="submit" name = "login" value="ログイン">
					</div>
				</div>
			</form>
		</section>
	</c:param>
</c:import>