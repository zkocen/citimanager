package company.citymanagerweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NonThreadSafeServlet
 */
@WebServlet("/nonthreadsafeservlet.do")
public class NonThreadSafeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NonThreadSafeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder msg = new StringBuilder("");
		msg.append("<html><body>");
		int currentSeed = (int)getServletContext().getAttribute("currentSeedValue");
		msg.append(String.format("Attribute Retrieved value: %d <br>", currentSeed));
		currentSeed *= 2;
		
		getServletContext().setAttribute("currentSeedValue", currentSeed);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		currentSeed = (int)getServletContext().getAttribute("currentSeedValue");
		msg.append(String.format("Attribute retrieved value after process: %d <br>", currentSeed));
		msg.append("</body></html>");
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		out.println(msg);
	}

}
  