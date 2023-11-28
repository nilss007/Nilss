
package com.emergentes.bean;

import com.emergentes.entidades.Categoria;
import com.emergentes.jpa.CategoriaJpaController;
import com.emergentes.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class BeanCategoria {
    private EntityManagerFactory emf;
    private CategoriaJpaController jpaCotegoria;
    
    public BeanCategoria() {
        emf = Persistence.createEntityManagerFactory("nilssPU");
        jpaCotegoria = new CategoriaJpaController(emf);
    }
    
    public List<Categoria> listarTodos() {
        return jpaCotegoria.findCategoriaEntities();
    }
    
    public void insertar(Categoria c){
        jpaCotegoria.create(c);
    }
    
    public void editar(Categoria c){
        try {
            jpaCotegoria.edit(c);
        }catch (Exception ex) {
            Logger.getLogger(BeanCategoria.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public void eliminar(Integer id){
        try {
            jpaCotegoria.destroy(id);
        }catch (NonexistentEntityException ex) {
            Logger.getLogger(BeanCategoria.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public Categoria buscar(Integer id){
        return jpaCotegoria.findCategoria(id);
    }
}
