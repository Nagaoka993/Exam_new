ject.jsp (科目別成績一覧)
<%@page import="scoremanager.main.TestListAction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="bean.TestListSubject" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>科目別成績一覧</title>
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
                <th>学生番号</th>
                <th>氏名</th>
                <th>得点</th>
                <%-- 必要に応じて項目を追加 --%>
            </tr>
        </thead>
        <tbody>
            <% for (TestListSubject testListSubject : list) { %>
            <tr>
                <td><%= TestListAction.getStudentName().get() %></td> <%-- Student情報取得 --%>
                <td><%= testListSubject.getStudent().getName() %></td>      <%-- Student情報取得 --%>
                <td><%= testListSubject.getScore() %></td>                  <%-- TestListSubject情報取得 --%>
                <%-- 必要に応じて項目を追加 --%>
            </tr>
            <% } %>
        </tbody>
    </table>
    <% } else { %>
        <p>該当するデータがありません。</p>
    <% } %>
</body>
</html>