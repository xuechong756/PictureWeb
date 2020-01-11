package com.yshow.pic.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseUtils {
	private static final Logger LOG = LoggerFactory.getLogger(ResponseUtils.class);

	public static void outToHtml(HttpServletResponse response, String string)
	{
		response.setHeader("Content-type", "text/html;charset=UTF-8");  
	    PrintWriter out = null;
    	try {
			out = response.getWriter();
    		out.write(string);  
	    	out.flush();  
		} catch (IOException e) {
			LOG.error("", e);
		}
    	finally
    	{
    		if(out != null)
    		{
    			try {
					out.close();
				} catch (Exception e) {
					LOG.error("", e);
				} 
    		}
    	}
	}
}
