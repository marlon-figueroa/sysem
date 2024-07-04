package org.primefaces.diamond.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;
import org.primefaces.diamond.domain.AuthenticationRequest;
import org.primefaces.diamond.domain.Usuario;
import org.primefaces.diamond.service.util.CryptoUtils;
import org.primefaces.diamond.service.util.JPAUtil;

/**
 *
 * @author mfigueroa
 */
@Named
@ApplicationScoped
public class UsuarioService {

    private static final Logger log = Logger.getLogger(UsuarioService.class);

    public boolean autenticar(AuthenticationRequest peticion) {
        boolean isAutentico = false;
        int first = 0;
        String h1 = CryptoUtils.computeHashSHA256(peticion.getPassword());
        EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Usuario> query = entity.createNamedQuery("Usuario.findByUsuario", Usuario.class);
        query.setParameter("usuario", peticion.getEmail());
        boolean isExist = query.getMaxResults() > 0;
        if (isExist) {
            Usuario usuario = query.getResultList().get(first);
            String h2 = usuario.getPassword();
            if (h1.equals(h2)) {
                log.info("Es autentico y autorizado");
                isAutentico = true;
            } else {
                log.info("La clave es incorrecta");
            }
        } else {
            log.info("El usuario no existe");
        }
        return isAutentico;
    }

    public Usuario getByUsuario(String usr) {
        EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Usuario> query = entity.createNamedQuery("Usuario.findByUsuario", Usuario.class);
        query.setParameter("usuario", usr);
        return query.getSingleResult();
    }

}
