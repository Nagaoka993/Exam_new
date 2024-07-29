<%-- 科目削除JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報削除</h2>
			<form action = "../scoremanager.main/SubjectDelete.action" method="post">
				<div class="mx-3 py-2">
					<p><label class="form-label" for="subject-delete-input">「${name}(${cd})」を削除してもよろしいですか</label></p>
				</div>
				<div class="mt-3">
					<input class="btn btn-primary" onclick="location.href='../subject_delete_comp'" type="submit" value="削除">
				</div>
			</form>
			<div class="lh-lg row">
				<div class="mx-3 col-2">
					<a href="../scoremanager.main/Subjectlist.action">科目一覧</a>
				</div>
			</div>
		</section>
	</c:param>
</c:import>