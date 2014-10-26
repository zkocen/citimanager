package company.citymanagerweb.helpers;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import company.citymanagerweb.models.DBManager;
import company.citymanagerweb.models.MySQLServerConnectionBehaviour;
import company.citymanagerweb.models.ServerConnectionBehaviour;

@WebListener
public class DBManagerSetup implements ServletContextListener {

    private DBManager dbm = null;
	
	
    public DBManagerSetup() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	if (dbm != null) {
			if (dbm.isConnected()) {
				dbm.closeConnection(false);
			}
		}
    	dbm = null;
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext sc = sce.getServletContext();
    	String uid = sc.getInitParameter("dbuserid");
    	String pwd = sc.getInitParameter("dbuserpwd");
    	String cat = sc.getInitParameter("dbinitcat");
    	
    	ServerConnectionBehaviour scb = new MySQLServerConnectionBehaviour(uid, pwd, cat);
    	
    	dbm = new DBManager(scb);
    	
    	sc.setAttribute("WorldManager", dbm);
    	
    	System.out.println("WorldDBManager created and added to context");
    }
}
