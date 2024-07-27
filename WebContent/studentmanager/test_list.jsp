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
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>
			<div class="my-2 text-end px-4">
				<a href="../scoremanager.main/SubjectCreate.action">新規登録</a>
			</div>
    	<h1>学生成績参照</h1>
    		<h2>科目情報で検索</h2>
    			<form action="TestListAction.do" method="post"> <%-- Actionクラスへのパスを設定 --%>
        			<label for="ent_year">入学年度:</label>
        			<input type="text" id="ent_year" name="ent_year"><br>
        			<label for="class_num">クラス:</label>
        			<input type="text" id="class_num" name="class_num"><br>
        			<label for="subject_name">科目:</label>
        			<input type="text" id="subject_name" name="subject_name"><br><br>
        			<input type="submit" value="検索">
   				 </form>

    			<h2>学生番号で検索</h2>
    				<form action="TestListAction.do" method="post"> <%-- Actionクラスへのパスを設定 --%>
        				<label for="student_num">学生番号:</label>
        				<input type="text" id="student_num" name="student_num"><br><br>
        				<input type="submit" value="検索">
    				</form>
				</body>
			</html>
		</section>
	</c:param>
</c:import>