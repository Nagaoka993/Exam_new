package scoremanager.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={""})

public class StudenttestAction extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)

	throws ServletException, IOException {

		request.getRequestDispatcher("student_list.jsp")

				.forward(request, response);

	}

}
