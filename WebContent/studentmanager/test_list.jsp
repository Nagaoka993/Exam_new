<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<% ArrayList<String> ent_year_list = (ArrayList<String>) request.getAttribute("ent_year_list"); %>
<% ArrayList<String> classnum_list = (ArrayList<String>) request.getAttribute("classnum_list"); %>
<% ArrayList<String> subjectname_list = (ArrayList<String>) request.getAttribute("subjectname_list"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生成績参照</title>
</head>
<body>
    <h1>学生成績参照</h1>

    <h2>科目情報で検索</h2>
    <form action="TestListSubjectExecuteAction.do" method="post">
        <label for="ent_year">入学年度:</label>
        <div>
        <select id="ent_year" name="ent_year">
            <option>-- 選択してください --</option>
   			<% for(int i = 0; i < ent_year_list.size(); i++) { %>
   			<option>
			<%= ent_year_list.get(i)%>
			</option>
			<% } %>
        </select></div>

        <label for="class_num">クラス:</label>
        <div>
        <select id="class_num" name="class_num">
            <option>-- 選択してください --</option>
   			<% for(int i = 0; i < classnum_list.size(); i++) { %>
   			<option>
			<%= classnum_list.get(i)%>
			</option>
			<% } %>
        </select></div>

        <label for="subjectname">科目:</label>
        <div>
        <select id="subjectname" name="subjectname">
            <option>-- 選択してください --</option>
   			<% for(int i = 0; i < subjectname_list.size(); i++) { %>
   			<option>
			<%= subjectname_list.get(i)%>
			</option>
			<% } %>
        </select></div>

        <input class="btn btn-primary" type="submit" value="検索">

    </form>
</body>
</html>