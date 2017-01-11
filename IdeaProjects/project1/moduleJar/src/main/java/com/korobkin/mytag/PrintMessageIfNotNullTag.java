package com.korobkin.mytag;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * This tag print "div" message with particular id attribute if var != ""
 */
public class PrintMessageIfNotNullTag extends TagSupport {
    private static final Logger logger = Logger.getLogger(PrintMessageIfNotNullTag.class);
    private String var;
    private String id;

    public void setVar(String var) {
        this.var = var;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int doStartTag() throws JspException {
        if (var.length()!=0) {
            JspWriter out = pageContext.getOut();
            try {
                out.print("<div");
                if (id.length() != 0) {
                    out.print(" id=\"" + id + "\"");
                }
                out.print(">");
                out.print(var + "</div>\r\n");
            } catch (IOException e) {
                logger.error(e);
            }
        }
        return SKIP_BODY;
    }
}
