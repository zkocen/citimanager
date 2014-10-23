package company.citymanagerweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ThreadSafeServlet
 */
@WebServlet("/threadsafeservlet.do")
public class ThreadSafeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThreadSafeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		synchronized (getServletContext()) {
			StringBuilder msg = new StringBuilder("");
			msg.append("<html><body>");
			int currentSeed = (int)getServletContext().getAttribute("currentSeedValue");
			msg.append(String.format("Attribute Retreived value: %d<br>", currentSeed));
			currentSeed *= 2;
			getServletContext().setAttribute("currentSeedValue", currentSeed);
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			currentSeed = (int)getServletContext().getAttribute("currentSeedValue");
			msg.append(String.format("Attribute retrieved after process: %d <br>", currentSeed));
			msg.append("</body></html>");
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(msg);
		}
	}
}
