package org.primefaces.diamond.service.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.diamond.domain.JwtUser;

public class SessionUtil {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public static String getUsername() {
        return (String) getSession().getAttribute("username");
    }

    public static String getAccessToken() {
        return (String) getSession().getAttribute("token");
    }

    public static JwtUser getLoggedInUser() {
        return (JwtUser) getSession().getAttribute("loggedInUser");
    }

    public static String getUserId() {
        HttpSession session = getSession();
        if (session != null) {
            return (String) session.getAttribute("userId");
        } else {
            return null;
        }
    }

    public static void putParamIntoSessionMap(String key, Object object) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (key != null && object != null) {
            context.getExternalContext().getSessionMap().put(key, object);
        } else {
            System.out.println("Error al poner el parametro en session");
            if (key == null) {
                System.out.println("Key es null");
            }
            if (object == null) {
                System.out.println("Objeto es null");
            }
        }
    }

    public static void removeParamFromSessionMap(String key) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (key != null) {
            context.getExternalContext().getSessionMap().remove(key);
        } else {
            System.out.println("Error al poner el parametro en session");
        }
    }

    public static Object getParamFromSessionMap(String key) {
        FacesContext context = FacesContext.getCurrentInstance();
        Object object;
        if (key != null) {
            object = context.getExternalContext().getSessionMap().get(key);
            if (object != null) {
                return object;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
