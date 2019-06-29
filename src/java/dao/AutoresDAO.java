
package dao;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import bd.PersistenceUtil;
import Modelo.TbAutores;
import java.io.Serializable;

public class AutoresDAO implements Serializable{

    public static AutoresDAO autoresDAO;

    public static AutoresDAO getInstance() {
        if (autoresDAO == null) {
            autoresDAO = new AutoresDAO();
        }
        return autoresDAO;
    }
    
    public TbAutores buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from TbAutores a where a.nomeAutor =:nome ");
        query.setParameter("nome", nome);

        List<TbAutores> autores = query.getResultList();
        if (autores != null && autores.size() > 0) {
            return autores.get(0);
        }

        return null;
    }

    public List<TbAutores> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from TbAutores As a");
        return query.getResultList();
    }

    public List<TbAutores> buscarTbAutoresInstancia() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select distinct a from TbAutores a group by a.autores");
        return query.getResultList();
    }
    
    public void remover(TbAutores autor) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        if (!em.contains(autor)) {
            autor = em.merge(autor);
        }
        em.remove(autor);
        em.getTransaction().commit();
    }

    public TbAutores persistir(TbAutores autores) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            autores = em.merge(autores);
            em.getTransaction().commit();
            System.out.println("Registro TbAutores gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return autores;
    }

    public void removeAll() {
       EntityManager em = PersistenceUtil.getEntityManager();
       em.getTransaction().begin();
       Query query = em.createQuery(" delete from TbAutores ");
       query.executeUpdate();
       em.getTransaction().commit();
    }

}
