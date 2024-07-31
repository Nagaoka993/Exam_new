<%-- 科目登録完了画面 --%>
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
	<div class="bg-success bg-opacity-50 text-center lh-lg">
				<p>登録が完了しました</p>
			</div>
			<div class="lh-lg row" style="margin-top: 8rem;">
				<div class="mx-3 col-1">
					<a href="../scoremanager.main/SubjectCreate.action">戻る</a>
				</div>
				<div class="mx-3 col-2">
					<a href="../scoremanager.main/Subjectlist.action">科目一覧</a>
				</div>
			</div>

	</form>
	</section>
	</c:param>
</c:import>