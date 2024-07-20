package org.primefaces.diamond.view;

import java.io.Serializable;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.diamond.domain.AuthenticationRequest;
import org.primefaces.diamond.entity.Usuario;
import org.primefaces.diamond.service.UsuarioService;
import org.primefaces.diamond.service.util.SessionUtil;

@Named
@ViewScoped
public class LoginView implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(LoginView.class);
    private static final String MSJ_TITULO_CORRECTO = "Bienvenid@";
    private static final String MSJ_TITULO_INCORRECTO = "Usario y password incorrectos";
    private static final String MSJ_INCORRECTO = "Por favor ingrese usuario y password correctos";
    private AuthenticationRequest authReq;
    
    @Inject
    private UsuarioService usuarioService;

    @PostConstruct
    public void init() {
        authReq = new AuthenticationRequest();
    }

    public String login() {
        LOGGER.info("Iniciando login...");
        FacesMessage msg = null;
        String route = "login?faces-redirect=true";
        try {
            boolean isAuthValid = usuarioService.autenticar(authReq);
            if (isAuthValid) {
                SessionUtil.getSession().setAttribute("username", authReq.getEmail());
                Usuario usuario = usuarioService.getByUsuario(authReq.getEmail());
                SessionUtil.getSession().setAttribute("userId", usuario.getId());
                SessionUtil.getSession().setAttribute("usrLoggin", usuario);
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, MSJ_TITULO_CORRECTO, authReq.getEmail());
                route = "dashboard?faces-redirect=true";
                LOGGER.info("Iniciando login...ok");

            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, MSJ_TITULO_INCORRECTO, MSJ_INCORRECTO);
            }
        } catch (Throwable e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, MSJ_TITULO_INCORRECTO, MSJ_INCORRECTO);
            LOGGER.error(e.getMessage());
            LOGGER.info("Iniciando login...ERROR", e);
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        LOGGER.info(route);
        return route;
    }

    public String logout() {
        LOGGER.info("CERRAR SESION");
        SessionUtil.getSession().invalidate();
        return "login?faces-redirect=true";
    }

    public AuthenticationRequest getAuthReq() {
        return authReq;
    }

    public void setAuthReq(AuthenticationRequest authReq) {
        this.authReq = authReq;
    }

}
