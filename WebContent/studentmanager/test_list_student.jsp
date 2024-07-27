<%@page import="dao.StudentDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="bean.Student" %>
<%@ page import="bean.TestListStudent" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生別成績一覧</title>
<style>
    .student-info {
        display: flex; /* 横並びにする */
        align-items: center; /* 垂直方向中央揃え */
    }

    .student-info > div {
        margin-right: 20px; /* 各項目の間隔 */
    }
</style>
</head>
<body>
    <h1>学生別成績一覧</h1>

    <%
        Student student = (Student) request.getAttribute("student");
        List<TestListStudent> student_list = (List<TestListStudent>) request.getAttribute("student_list");

        if (student != null && student_list != null && !student_list.isEmpty()) {
    %>
    <div class="student-info">
        <tr>
        	<h3><td>${studentDao.get()}</td></h3>
        </tr>
    </div>
    <table>
        <thead>
            <tr>
                <th>科目名</th>
                <th>科目コード</th>
                <th>回数</th>
                <th>点数</th>
            </tr>
        </thead>
        <tbody>
            <% for (TestListStudent testListStudent : student_list) { %>
            <tr>
				<td>${subject.cd}</td>
				<td>${subject.name}</td>
				<td></td>
				<td></td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <% } else { %>
    <p>該当するデータがありません。</p>
    <% } %>
</body>
</html>