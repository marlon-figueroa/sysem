package org.primefaces.diamond.service;

import java.text.MessageFormat;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;
import org.primefaces.diamond.dao.UsuarioDAO;
import org.primefaces.diamond.domain.AuthenticationRequest;
import org.primefaces.diamond.entity.Usuario;
import org.primefaces.diamond.service.util.CryptoUtils;
import org.primefaces.diamond.service.util.JPAUtil;

/**
 *
 * @author mfigueroa
 */
@Named
@ApplicationScoped
public class UsuarioService implements IService<Usuario, Integer> {

    private static final Logger LOGGER = Logger.getLogger(UsuarioService.class);

    @Inject
    private UsuarioDAO dao;

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
                LOGGER.info("Es autentico y autorizado");
                isAutentico = true;
            } else {
                LOGGER.info("La clave es incorrecta");
            }
        } else {
            LOGGER.info("El usuario no existe");
        }
        return isAutentico;
    }

    public Usuario getByUsuario(String usr) {
        EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Usuario> query = entity.createNamedQuery("Usuario.findByUsuario", Usuario.class);
        query.setParameter("usuario", usr);
        return query.getSingleResult();
    }

    @Override
    public boolean create(Usuario entity) {
        LOGGER.info(MessageFormat.format("Operacion de creacion de {0}", entity.getId()));
        return this.dao.create(entity);
    }

    @Override
    public boolean edit(Usuario entity) {
        LOGGER.info(MessageFormat.format("Operacion de edicion de {0}", entity.getId()));
        return this.dao.edit(entity);
    }

    @Override
    public boolean remove(Usuario entity) {
        LOGGER.info(MessageFormat.format("Operacion de remover de {0}", entity.getId()));
        return this.dao.remove(entity);
    }

    @Override
    public List<Usuario> findAll() {
        LOGGER.info("Operacion de listar registros");
        return this.dao.findAll();
    }

    @Override
    public Usuario findById(Integer id) {
        LOGGER.info(MessageFormat.format("Operacion de obtener el registro por ID {0}", id));
        return this.dao.find(id);
    }

}
