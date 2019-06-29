package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import bd.PersistenceUtil;
import Modelo.TbExemplar;
import java.io.Serializable;

public class ExemplarDAO implements Serializable{

    public static ExemplarDAO exemplarDAO;

    public static ExemplarDAO getInstance() {
        if (exemplarDAO == null) {
            exemplarDAO = new ExemplarDAO();
        }
        return exemplarDAO;
    }
    
    public TbExemplar buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from TbExemplar a where a.circular =:nome ");
        query.setParameter("nome", nome);

        List<TbExemplar> exemplar = query.getResultList();
        if (exemplar != null && exemplar.size() > 0) {
            return exemplar.get(0);
            
        }

        return null;
    }

    public List<TbExemplar> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from TbExemplar As a");
        return query.getResultList();
    }

    public List<TbExemplar> buscarTbExemplarInstancia() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select distinct a from TbExemplar a group by a.circular");
        return query.getResultList();
    }
    
    public void remover(TbExemplar exemplar) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        if (!em.contains(exemplar)) {
            exemplar = em.merge(exemplar);
        }
        em.remove(exemplar);
        em.getTransaction().commit();
    }

    public TbExemplar persistir(TbExemplar exemplar) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            exemplar = em.merge(exemplar);
            em.getTransaction().commit();
            System.out.println("Registro TbExemplar gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exemplar;
    }

    public void removeAll() {
       EntityManager em = PersistenceUtil.getEntityManager();
       em.getTransaction().begin();
       Query query = em.createQuery(" delete from TbExemplar ");
       query.executeUpdate();
       em.getTransaction().commit();
    }

}
