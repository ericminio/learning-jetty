package tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class HelloWorld extends TagSupport
{
    public int doStartTag() throws JspException
    {
        try
        {
            pageContext.getOut().write("Hello from custom tag");
        }
        catch(Exception e)
        {
            throw new JspException(e.getMessage());
        }
        return EVAL_PAGE;
    }
}
