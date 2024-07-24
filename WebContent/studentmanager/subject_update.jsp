<%-- 科目更新JSP --%>
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
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>
			<form action = "../scoremanager.main/SubjectUpdateExecute.action" method="post">
				<div class="mx-3 py-2">
					<label class="form-label" for="subject-cd-input">科目コード</label>
					<input readonly class="form-control-plaintext ms-3" type="text"
						id="subject-cd-input" name="cd" value="${cd}" />
				</div>
				<div class="me-4">
					<label class="form-label" for="subject-name-input">科目名</label>
					<input class="form-control" type="text" id="subject-name-input"
						name="name" placeholder="科目名を入力して下さい" maxlength="20"
						value="${name}" required>
					<div class="text-warning">${errors.get("name")}</div>
				</div>
				<div class="me-4">
					<input class="btn btn-primary" type="submit" value="変更">
				</div>
			</form>
			<div class="lh-lg row">
				<div class="mx-3 col-1">
					<a href="../scoremanager.main/Subjectlist.action">戻る</a>
				</div>
			</div>
		</section>
	</c:param>

</c:import>