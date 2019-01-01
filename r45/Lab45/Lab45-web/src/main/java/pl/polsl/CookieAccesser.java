/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @version 1.0
 * @author Adam Gajewski
 */
public class CookieAccesser {
    /**
     * 
     * @param str the string we want to save in the cookie
     * @param request request
     * @param response response
     */
    public static void add(String str, HttpServletRequest request, 
            HttpServletResponse response){
        Cookie cookie = null;
	    Cookie[] cookies = request.getCookies();

	    //String name = request.getParameter("name");
            String name = "Polsl";
	    if (cookies != null) {
		for (Cookie c : cookies) {
		    if (c.getName().equals(name)) {
			cookie = c;
			break;
		    }
		}
	    }
	    if (cookie != null) {
		cookie.setValue((cookie.getValue()) + "," + str);
	    } else {
		cookie = new Cookie(name, str);
	    }
	    response.addCookie(cookie);
    }
}
