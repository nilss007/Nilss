
package com.emergentes.bean;

import com.emergentes.entidades.Proveedor;
import com.emergentes.jpa.ProveedorJpaController;
import com.emergentes.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class BeanProveedor {
    private EntityManagerFactory emf;
    private ProveedorJpaController jpaProveedor;
    
    public BeanProveedor() {
        emf = Persistence.createEntityManagerFactory("nilssPU");
        jpaProveedor = new ProveedorJpaController(emf);
    }
    
    public List<Proveedor> listarTodos() {
        return jpaProveedor.findProveedorEntities();
    }
    
    public void insertar(Proveedor c){
        jpaProveedor.create(c);
    }
    
    public void editar(Proveedor c){
        try {
            jpaProveedor.edit(c);
        }catch (Exception ex) {
            Logger.getLogger(BeanProveedor.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public void eliminar(Integer id){
        try {
            jpaProveedor.destroy(id);
        }catch (NonexistentEntityException ex) {
            Logger.getLogger(BeanProveedor.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public Proveedor buscar(Integer id){
        return jpaProveedor.findProveedor(id);
    }
}
