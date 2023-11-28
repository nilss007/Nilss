/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.bean;

import com.emergentes.entidades.Cliente;
import com.emergentes.jpa.ClienteJpaController;
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
public class BeanCliente {
    private EntityManagerFactory emf;
    private ClienteJpaController jpaCliente;
    
    public BeanCliente() {
        emf = Persistence.createEntityManagerFactory("nilssPU");
        jpaCliente = new ClienteJpaController(emf);
    }
    
    public List<Cliente> listarTodos() {
        return jpaCliente.findClienteEntities();
    }
    
    public void insertar(Cliente cli){
        jpaCliente.create(cli);
    }
    
    public void editar(Cliente cli){
        try {
            jpaCliente.edit(cli);
        }catch (Exception ex) {
            Logger.getLogger(BeanCliente.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public void eliminar(Integer id){
        try {
            jpaCliente.destroy(id);
        }catch (NonexistentEntityException ex) {
            Logger.getLogger(BeanCliente.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public Cliente buscar(Integer id){
        return jpaCliente.findCliente(id);
    }
}
