package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import bd.PersistenceUtil;
import Modelo.TbEditora;
import java.io.Serializable;

public class EditoraDAO implements Serializable{

    public static EditoraDAO editoraDAO;

    public static EditoraDAO getInstance() {
        if (editoraDAO == null) {
            editoraDAO = new EditoraDAO();
        }
        return editoraDAO;
    }
    
    public TbEditora buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from TbEditora a where a.nomeEditora =:nome ");
        query.setParameter("nome", nome);

        List<TbEditora> editora = query.getResultList();
        if (editora != null && editora.size() > 0) {
            return editora.get(0);
        }

        return null;
    }

    public List<TbEditora> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from TbEditora As a");
        return query.getResultList();
    }

    public List<TbEditora> buscarTbEditoraInstancia() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select distinct a from TbEditora a group by a.editora");
        return query.getResultList();
    }
    
    public void remover(TbEditora editora) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        if (!em.contains(editora)) {
            editora = em.merge(editora);
        }
        em.remove(editora);
        em.getTransaction().commit();
    }

    public TbEditora persistir(TbEditora editora) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            editora = em.merge(editora);
            em.getTransaction().commit();
            System.out.println("Registro TbEditora gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return editora;
    }

    public void removeAll() {
       EntityManager em = PersistenceUtil.getEntityManager();
       em.getTransaction().begin();
       Query query = em.createQuery(" delete from TbEditora ");
       query.executeUpdate();
       em.getTransaction().commit();
    }

}
