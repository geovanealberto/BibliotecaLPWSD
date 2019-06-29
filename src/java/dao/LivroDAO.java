package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import bd.PersistenceUtil;
import Modelo.TbLivro;
import java.io.Serializable;

public class LivroDAO implements Serializable{

    public static LivroDAO livroDAO;

    public static LivroDAO getInstance() {
        if (livroDAO == null) {
            livroDAO = new LivroDAO();
        }
        return livroDAO;
    }
    
    
   
    public TbLivro buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from TbLivro a where a.titulo =:nome ");
        query.setParameter("nome", nome);

        List<TbLivro> livro = query.getResultList();
        if (livro != null && livro.size() > 0) {
            return livro.get(0);
        }

        return null;
    }

    public List<TbLivro> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from TbLivro as a");
        
        return query.getResultList();
    }
/*SELECT idtbLivro, titulo,isbn, edicao,ano, tbassunto.nomeAssunto as Assunto, tbeditora.nomeEditora as Editora FROM tblivro \n" +
"INNER JOIN tbassunto ON tbAssunto_idtbAssunto=idtbAssunto\n" +
"INNER JOIN tbeditora ON tbEditora_idtbEditora=idtbEditora*/
    public List<TbLivro> buscarTbLivroInstancia() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select distinct a from TbLivro a group by a.livro");
        return query.getResultList();
    }
    
    public void remover(TbLivro livro) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        if (!em.contains(livro)) {
            livro = em.merge(livro);
        }
        em.remove(livro);
        em.getTransaction().commit();
    }

    public TbLivro persistir(TbLivro livro) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            livro = em.merge(livro);
            em.getTransaction().commit();
            System.out.println("Registro TbLivro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return livro;
    }

    public void removeAll() {
       EntityManager em = PersistenceUtil.getEntityManager();
       em.getTransaction().begin();
       Query query = em.createQuery(" delete from TbLivro ");
       query.executeUpdate();
       em.getTransaction().commit();
    }

}
