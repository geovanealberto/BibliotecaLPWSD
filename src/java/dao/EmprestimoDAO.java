/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Modelo.TbEmprestimo;
import bd.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author alunoces
 */
public class EmprestimoDAO {
    public static EmprestimoDAO emprestimoDAO;

    public static EmprestimoDAO getInstance() {
        if (emprestimoDAO == null) {
            emprestimoDAO = new EmprestimoDAO();
        }
        return emprestimoDAO;
    }
    
    public TbEmprestimo buscar(String idtbEmprestimo) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select e from TbEmprestimo e where e.idtbEmprestimo =:idtbEmprestimo ");
        query.setParameter("idtbEmprestimo", idtbEmprestimo);

        List<TbEmprestimo> emprestimo = query.getResultList();
        if (emprestimo != null && emprestimo.size() > 0) {
            return emprestimo.get(0);
        }

        return null;
    }

    public List<TbEmprestimo> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from TbEmprestimo As e ");
        return query.getResultList();
    }

    public List<TbEmprestimo> buscarTbEmprestimoInstancia() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select distinct e from TbEmprestimo e");
        return query.getResultList();
    }
    
    public void remover(TbEmprestimo emprestimo) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        if (!em.contains(emprestimo)) {
            emprestimo = em.merge(emprestimo);
        }
        em.remove(emprestimo);
        em.getTransaction().commit();
    }

    public TbEmprestimo persistir(TbEmprestimo emprestimo) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            emprestimo = em.merge(emprestimo);
            em.getTransaction().commit();
            System.out.println("Registro TbEmprestimo gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emprestimo;
    }

    public void removeAll() {
       EntityManager em = PersistenceUtil.getEntityManager();
       em.getTransaction().begin();
       Query query = em.createQuery(" delete from TbEmprestimo ");
       query.executeUpdate();
       em.getTransaction().commit();
    }

}
