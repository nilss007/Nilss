/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.bean;

import com.emergentes.entidades.Factura;
import com.emergentes.jpa.FacturaJpaController;
import com.emergentes.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author HOMELADER
 */
public class BeanFactura {
    private EntityManagerFactory emf;
    private FacturaJpaController jpaFactura;
    
    public BeanFactura() {
        emf = Persistence.createEntityManagerFactory("nilssPU");
        jpaFactura = new FacturaJpaController(emf);
    }
    
    public List<Factura> listarTodos() {
        return jpaFactura.findFacturaEntities();
    }
    
    public void insertar(Factura fac){
        jpaFactura.create(fac);
    }
    
    public void editar(Factura fac){
        try {
            jpaFactura.edit(fac);
        }catch (Exception ex) {
            Logger.getLogger(BeanFactura.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public void eliminar(Integer id){
        try {
            jpaFactura.destroy(id);
        }catch (NonexistentEntityException ex) {
            Logger.getLogger(BeanFactura.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public Factura buscar(Integer id){
        return jpaFactura.findFactura(id);
    }
}
