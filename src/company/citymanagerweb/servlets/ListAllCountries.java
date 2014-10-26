package company.citymanagerweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import company.citymanagerweb.helpers.DBWorldQueries;
import company.citymanagerweb.models.DBManager;

@WebServlet("/listallcountries.do")
public class ListAllCountries extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListAllCountries() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DBManager dbm = (DBManager) getServletContext().getAttribute(
				"WorldDBManager");

		StringBuilder sb = new StringBuilder("<html><body>");

		try {
			if (!dbm.isConnected()) {
				if (!dbm.openConnection()) {
					sb.append("Could not connect to the database...");
				}
			}

			sb.append("<table border=1>"
					+ "<tr><td>CODE</td><td>NAME</td><td>POPULATION</td></tr>");

			String query = DBWorldQueries.getCountriesByName();
			ResultSet rs = dbm.ExecuteResultSet(query);
			while (rs.next()) {
				String id = rs.getString("Code");
				String name = rs.getString("Name");
				int pop = rs.getInt("Population");

				sb.append("<tr><td>" + id + "</td>" + "<td>" + name + "</td>"
						+ "<td>" + pop + "</td></tr>");
			}
			sb.append("</table>");
		} catch (Exception e) {
			sb.append("<h1>ERROR: " + e.getMessage() + "</h1>");
		}
		sb.append("</body></html>");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(sb);
	}
}
