package company.citymanagerweb.sessionlisteners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class ActiveUserTracking implements HttpSessionListener {

    private static int activeUsers;
	
    public ActiveUserTracking() {}

    public void sessionCreated(HttpSessionEvent hse)  { 
    	System.out.println("A new session is created");
    	activeUsers++;
    }

    public void sessionDestroyed(HttpSessionEvent hse)  { 
    	System.out.println("An existing session was destroyed");
    	activeUsers--;
    }
    
    public static int getActiveUserCount(){
    	return activeUsers;
    }
	
}
