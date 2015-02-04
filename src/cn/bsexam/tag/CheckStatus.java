package cn.bsexam.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class CheckStatus extends TagSupport {
	private static final String Redirect = "/Work/index.html" ; 
	public int doStartTag() throws JspException {
		String sessionId = (String) super.pageContext.getSession().getAttribute("sessionId");
		if(sessionId==null||sessionId.equals("")){
			HttpServletResponse response = (HttpServletResponse) super.pageContext.getResponse();
			try {
				response.sendRedirect(Redirect);
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return TagSupport.SKIP_BODY;
	}

}
