package tags;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.*;
import javax.servlet.jsp.*;



public class LogInHandlerv extends SimpleTagSupport {
	public void doTag() throws JspException,IOException{
		try {
			String id=(String) getJspContext().findAttribute("vname");
			System.out.println("**********IN THE TAGS NAME IS "+id+" ****************");
			if(null==id) {
				JspWriter out = getJspContext().getOut();
				out.println("<script type=\"text/javascript\">window.location.href='index.jsp';</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new JspException(e.getMessage());
		}
	}

}
