<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<% ArrayList<String> ent_year_list = (ArrayList<String>) request.getAttribute("ent_year_list"); %>
<% ArrayList<String> classnum_list = (ArrayList<String>) request.getAttribute("classnum_list"); %>
<% ArrayList<String> subjectname_list = (ArrayList<String>) request.getAttribute("subjectname_list"); %>
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
				<div class="row border mx-3 mb-3 py-2 aligin-items-center rounded" id ="filter">
					<div class="col-12">
					<style>
				table {
				  	border-collapse: collapse;
  					width: 100%;
  					border-collapse: collapse; /* セルの枠線を統合 */
					}
				th {
					border-bottom: 1px solid #ccc; /* 下線のスタイル */
					}
				td {
  					border-top: none;    /* 上線を消す */
  					border-right: none;   /* 右線を消す */
  					border-left: none;    /* 左線を消す */
  					border-bottom: 1px solid #ccc; /* 下線のスタイル */
  					padding-bottom: 1px; /* 下線とテキストの間隔 */
  					padding-top: 1px;
					}
					</style>
						<label class="form-label" for=subject-f1-select></label>
						<table style="width: 100%; ">
						<form action="../scoremanager.main/TestListSubjectExecute.action"method="post">
						<tr >
							<th style="width: 70px;">科目情報</th>

							<td style="width: 70px;">入学年度
							<div></div>
							 <select id="ent_year" name="ent_year">
							<option>-------------</option>
							<% for(int i = 0; i < ent_year_list.size(); i++) { %><option>
							<%= ent_year_list.get(i)%></option><% } %></select></td>

							<td style="width: 5px;">クラス
							<div></div>
							 <select id="calssnum" name="class_num">
							<option>-------------</option>
							<% for(int i = 0; i < classnum_list.size(); i++) { %><option>
							<%= classnum_list.get(i)%></option><% } %></select></td>

							<td style="width: 5px;">科目
							<div></div>
							 <select id="subjectname" name="subject_name">
							<option>-------------</option>
							<% for(int i = 0; i < subjectname_list.size(); i++) { %><option>
							<%= subjectname_list.get(i)%></option><% } %></select></td>

							<td><input class="btn btn-primary" type="submit" value="検索"></td>
						</tr>
						</form>

						<tr>
							<th>学生情報</th>
							<td>学生番号
							<div>
							<form action="../scoremanager.main/TestListSubjectExecute.action" method="post">
							<input class="student_num" type="text"name="student_num" required minlength="7" size="20" placeholder="学生番号を入力してください"></td>
							<td><input class="btn btn-primary" type="submit"value="検索"></td>
							</form>
						</tr>
					</table>
					</div>
				</div>
				<font size="2" style="color: blue;">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</font>
		</section>
	</c:param>
</c:import>