package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import bd.PersistenceUtil;
import Modelo.TbUsuario;
import java.io.Serializable;

public class UsuarioDAO implements Serializable{

    public static UsuarioDAO usuarioDAO;

    public static UsuarioDAO getInstance() {
        if (usuarioDAO == null) {
            usuarioDAO = new UsuarioDAO();
        }
        return usuarioDAO;
    }
    
    public TbUsuario buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from TbUsuario a where a.nomeUsuario =:nome ");
        query.setParameter("nome", nome);

        List<TbUsuario> usuario = query.getResultList();
        if (usuario != null && usuario.size() > 0) {
            return usuario.get(0);
        }

        return null;
    }

    public List<TbUsuario> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from TbUsuario As a");
        return query.getResultList();
    }

    public List<TbUsuario> buscarTbUsuarioInstancia() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select distinct a from TbUsuario a group by a.usuario");
        return query.getResultList();
    }
    
    public void remover(TbUsuario usuario) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        if (!em.contains(usuario)) {
            usuario = em.merge(usuario);
        }
        em.remove(usuario);
        em.getTransaction().commit();
    }

    public TbUsuario persistir(TbUsuario usuario) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            usuario = em.merge(usuario);
            em.getTransaction().commit();
            System.out.println("Registro TbUsuario gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public void removeAll() {
       EntityManager em = PersistenceUtil.getEntityManager();
       em.getTransaction().begin();
       Query query = em.createQuery(" delete from TbUsuario ");
       query.executeUpdate();
       em.getTransaction().commit();
    }
    
    public TbUsuario login(TbUsuario usuario) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from TbUsuario a where a.email =:email and a.senha =:senha");
        query.setParameter("email", usuario.getEmail());
        query.setParameter("senha", usuario.getSenha());

        List<TbUsuario> usuarios = query.getResultList();
        if (usuarios != null && usuarios.size() > 0) {
            return usuarios.get(0);
        }

        return null;
    }

}
