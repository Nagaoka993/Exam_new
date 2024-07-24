package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Teacher;

@WebServlet(urlPatterns={"/test/menutest"})
public class Menutest extends HttpServlet{

	public void doGet(
			HttpServletRequest request, HttpServletResponse response
	)throws ServletException, IOException{
		//動作確認のため、Teacherオブジェクトをセッションで渡す

		School school = new School();
		school.setCd("oom");
		school.setName("大宮校");

		Teacher teacher = new Teacher();
		teacher.setId("admin");
		teacher.setName("矢島 翔");
		teacher.setPassword("password");
		teacher.setSchool_cd("oom");
		teacher.setSchool(school);

        HttpSession session = request.getSession();
        session.setAttribute("user", teacher);


		request.getRequestDispatcher("/scoremanager.main/Studentlist.action")
		.forward(request, response);
	}
}