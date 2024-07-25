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

			<%-- パラメーターdoneが存在する場合 --%>
			<c:if test="${!empty done}">
				<div class="bg-success bg-opacity-50 text-center lh-lg">
					<p>${done}</p>
				</div>
			</c:if>
			<form action="../scoremanager.main/SubjectCreateExecute.action" method="post">
				<div class="mx-3 py-2">
					<div class="my-3">
						<label class="form-label" for="subject-cd-input">科目コード</label>
						<input type="text" class="form-control" id="subject-cd-input" name="cd"
						placeholder="科目コードを入力してください" size="95" maxlength="3" value="${cd}" required><br>
						<div class="mt-2 text-warning">${errors.get("cd")}</div>
					</div>
					<div class="my-3">
						<label class="form-label" for="subject-cd-input">科目名</label>
						<input type="text" class="form-control" id="subject-name-input" name="name"
						placeholder="科目名を入力してください" size="95" maxlength="20" value="${name}" required><br>
						<div class="mt-2 text-warning">${errors.get("name")}</div>
					</div>
						<input type="submit" value="登録" name="create_btn">
				</div>
			</form>
				<div>
						<a href="../scoremanager.main/Subjectlist.action">戻る</a>
				</div>

		</section>
	</c:param>
</c:import>