<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<% ArrayList<String> ent_year_list = (ArrayList<String>) request.getAttribute("ent_year_list"); %>
<% ArrayList<String> classnum_list = (ArrayList<String>) request.getAttribute("classnum_list"); %>
<% ArrayList<String> subjectname_list = (ArrayList<String>) request.getAttribute("subjectname_list"); %>
<c:import url="/common/base.jsp">
    <c:param name="title">成績管理</c:param>
    <c:param name="content">
    <form action="TestRegistExecute.action" method="post">
        <label for="ent_year">入学年度:</label>
        <div>
        <select id="ent_year" name="ent_year">
            <option>-------------</option>
   			<% for(int i = 0; i < ent_year_list.size(); i++) { %>
   			<option>
			<%= ent_year_list.get(i)%>
			</option>
			<% } %>
        </select></div>

        <label for="class_num">クラス:</label>
        <div>
        <select id="class_num" name="class_num">
            <option>--------------</option>
   			<% for(int i = 0; i < classnum_list.size(); i++) { %>
   			<option>
			<%= classnum_list.get(i)%>
			</option>
			<% } %>
        </select></div>

        <label for="subjectname">科目:</label>
        <div>
        <select id="subjectname" name="subject_name">
            <option>---------------</option>
   			<% for(int i = 0; i < subjectname_list.size(); i++) { %>
   			<option>
			<%= subjectname_list.get(i)%>
			</option>
			<% } %>
        </select></div>
		<select name="times">
			<option>----------------</option>
			<option>1</option>
			<option>2</option>
		</select>

        <input class="btn btn-primary" type="submit" value="検索">
    </form>
    </c:param>
</c:import>