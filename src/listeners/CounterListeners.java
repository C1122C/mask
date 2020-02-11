package listeners;

import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class CounterListeners implements ServletContextListener, ServletContextAttributeListener {
	int user_num=0;
	int visitor_num=0;
	String file_path="E:\\workspace\\onlineStockWebSample01\\WebContent\\counter.txt";
	public void contextInitialized(ServletContextEvent cse) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file_path));
			writer.write("0");
			writer.write("\n");
			writer.write("0");
			writer.close();
		}catch (Exception e) {
			System.out.println(e.toString());
		}
		ServletContext servletContext= cse.getServletContext();
		servletContext.setAttribute("v_counter", Integer.toString(visitor_num));
		servletContext.setAttribute("u_counter", Integer.toString(user_num));
		System.out.println("Application initialized");

	}
	
	public void attributeAdded(ServletContextAttributeEvent arg0) {
		System.out.println("ServletContextattribute added");
	}
	
	
	public void attributeReplaced(ServletContextAttributeEvent scae) {
		System.out.println("ServletContextattribute replaced");
		writeCounter(scae);
	}
	
	synchronized void writeCounter(ServletContextAttributeEvent scae) {
		ServletContext servletContext= scae.getServletContext();
		visitor_num = Integer.parseInt((String) servletContext.getAttribute("v_counter"));
		user_num = Integer.parseInt((String) servletContext.getAttribute("u_counter"));
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file_path));
			writer.write(Integer.toString(visitor_num));
			writer.write("\n");
			writer.write(Integer.toString(user_num));
			writer.close();
			System.out.println("Writing:NOW "+visitor_num+" VISITOR AND "+user_num+" USER");
		}catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Application shut down");
	}

}
