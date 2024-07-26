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
			<form action="TestList.action">
				<div class="row border mx-3 mb-3 py-2 aligin-items-center rounded" id ="filter">
					<div class="col-12">
					<style>
				table {
				  	border-collapse: collapse;
  					width: 100%;
  					border-collapse: collapse; /* セルの枠線を統合 */
					}
				td {
  					border-top: none;    /* 上線を消す */
  					border-right: none;   /* 右線を消す */
  					border-left: none;    /* 左線を消す */
  					border-bottom: 1px solid #ccc; /* 下線のスタイル */
  					padding-bottom: 7px; /* 下線とテキストの間隔 */
  					padding-top: 7px;
					}
					th:nth-child(1), td:nth-child(1) { width: 10%; } /* 科目コード */
					th:nth-child(2), td:nth-child(2) { width: 30%; } /* 科目名 */
					th:nth-child(3), td:nth-child(3) { width: 10%; } /* 変更ボタン */
					th:nth-child(4), td:nth-child(4) { width: 10%; } /* 削除ボタン */
					th:nth-child(5), td:nth-child(5) { width: 40%; } /* 右側の余白 */
					</style>
						<label class="form-label" for=subject-f1-select></label>
						<table style="width: 100%;">
						<tr>
							<td colspan="5" style="font-weight: 900;"style="border-bottom: 1px solid #ccc; padding-bottom: 5px;">科目情報</td>

							<td colspan="5" style="font-weight: 900;"style="border-bottom: 1px solid #ccc; padding-bottom: 5px;">学生情報</td>
						</tr>
					</table>
					</div>
				</div>
				</form>
		</section>
	</c:param>
</c:import>