<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<% List<String> entYearList = (List<String>) request.getAttribute("ent_year_list"); %>
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
        <select id="ent_year" name="ent_year">
            <option>-- 選択してください --</option>
   			<c:forEach var="ent_year" items="${ent_year_list} ">
			<option><c:out value="${ent_year}" /></option>
   			</c:forEach>

        </select><br>

        <label for="subject_name">科目:</label>
        <select id="subject_name" name="subject_name">
            <option value="">-- 選択してください --</option>
            %>
        </select><br><br>

        <input type="submit" value="検索">
    </form>

    <h2>学生番号で検索</h2>
    <form action="TestListSubjectExecuteAction.do" method="post">
        <label for="student_num">学生番号:</label>
        <input type="text" id="student_num" name="student_num"><br><br>
        <input type="submit" value="検索">
    </form>
</body>
</html>