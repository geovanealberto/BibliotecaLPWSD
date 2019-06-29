/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import Modelo.TbEmprestimo;
import dao.EmprestimoDAO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author alunoces
 */
@ManagedBean
@ViewScoped
public class emprestimoBean {
    
   TbEmprestimo emprestimo = new TbEmprestimo();

    List emprestimos = new ArrayList();

    //construtor
    public emprestimoBean() {
        emprestimos = new EmprestimoDAO().buscarTodas();
        emprestimo = new TbEmprestimo();
    }

    //Métodos dos botões 
    public void record(ActionEvent actionEvent) {
        new EmprestimoDAO().persistir(emprestimo);
        emprestimos = new EmprestimoDAO().buscarTodas();
        emprestimo = new TbEmprestimo();
    }

    public void exclude(ActionEvent actionEvent) {
        new EmprestimoDAO().remover(emprestimo);
        emprestimos = new EmprestimoDAO().buscarTodas();
        emprestimo = new TbEmprestimo();
    }

    public TbEmprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(TbEmprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public List getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List emprestimos) {
        this.emprestimos = emprestimos;
    }
    
    
    
}
