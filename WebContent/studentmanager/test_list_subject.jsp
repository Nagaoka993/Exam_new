<%@page import="scoremanager.main.TestListAction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="bean.TestListSubject" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>科目別成績一覧</title>
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
    <h1>科目別成績一覧</h1>
    <%
        List<TestListSubject> list = (List<TestListSubject>) request.getAttribute("list");
        if (list != null && !list.isEmpty()) {
    %>
    <table>
        <thead>
            <tr>
                <th>入学年度</th>
                <th>クラス</th>
                <th>学生番号</th>
                <th>氏名</th>
            </tr>
        </thead>
        <tbody>
            <% for (TestListSubject testListSubject : list) { %>
            <tr>
                <td colspan="2">
                    <div class="student-info">
                        <div>入学年度: <%= TestListAction.ent%></div>
                        <div>クラス: <%= testList.getTestListStudent().getTestListSubject().getSubject().getClassNum() %></div>
                    </div>
                </td>
                <td><%= testListSubject.getStudentNo() %></td>
                <td><%= testListSubject.getStudentName() %></td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <% } else { %>
        <p>該当するデータがありません。</p>
    <% } %>
</body>
</html>