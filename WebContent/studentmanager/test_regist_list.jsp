<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList,bean.Test"%>
<% ArrayList<String> ent_year_list = (ArrayList<String>) request.getAttribute("ent_year_list"); %>
<% ArrayList<String> classnum_list = (ArrayList<String>) request.getAttribute("classnum_list"); %>
<% ArrayList<String> subjectname_list = (ArrayList<String>) request.getAttribute("subjectname_list"); %>
<% ArrayList<Test> Test_list = (ArrayList<Test>) session.getAttribute("Test_list"); %>
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
    <form action="../scoremanager.main/TestRegistUpdateExecute.action" method="post">
    	<table class="table table-hover">
			<tr>
			<th>入学年度</th>
			<th>クラス</th>
			<th>学生番号</th>
			<th>氏名</th>
			<th>点数</th>
			</tr>
			<% int count=0; %><
			<% for(Test i:Test_list){%>
				<tr>
				<th><%= i.getStudent().getEntYear() %></th>
				<th><%= i.getClassNum() %></th>
				<th><%= i.getStudent().getNo() %></th>
				<th><%= i.getStudent().getName() %></th>
				    <input type="hidden"
				           name="point_old_set[]"
				           value="<%= i.getPoint() %>" /><%-- 変更前の点数をhiddenで保持 --%>
				<th>
					<input  name="point_new_set[]" value="<%= i.getPoint() %>"/>
				</th>
				<tr>
					<input type="hidden" name="student_no_set[]" value=<%= i.getStudent().getNo() %>>
					<input type="hidden" name="subject_cd_set[]" value=<%= i.getSubject().getCd() %>>
					<input type="hidden" name="no_set[]" value=<%= i.getNo() %>>
			<% } %>
		</table>

    <input class="btn btn-primary" type="submit" value="登録して終了">
    </form>
    </c:param>
</c:import>