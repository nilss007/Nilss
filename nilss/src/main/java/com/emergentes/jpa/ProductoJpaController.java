/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.emergentes.entidades.Categoria;
import com.emergentes.entidades.Producto;
import com.emergentes.entidades.Proveedor;
import com.emergentes.entidades.Venta;
import com.emergentes.jpa.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author HOMELADER
 */
public class ProductoJpaController implements Serializable {

    public ProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) {
        if (producto.getVentaList() == null) {
            producto.setVentaList(new ArrayList<Venta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria categoriaId = producto.getCategoriaId();
            if (categoriaId != null) {
                categoriaId = em.getReference(categoriaId.getClass(), categoriaId.getIdCategoria());
                producto.setCategoriaId(categoriaId);
            }
            Proveedor proveedorId = producto.getProveedorId();
            if (proveedorId != null) {
                proveedorId = em.getReference(proveedorId.getClass(), proveedorId.getIdProveedor());
                producto.setProveedorId(proveedorId);
            }
            List<Venta> attachedVentaList = new ArrayList<Venta>();
            for (Venta ventaListVentaToAttach : producto.getVentaList()) {
                ventaListVentaToAttach = em.getReference(ventaListVentaToAttach.getClass(), ventaListVentaToAttach.getIdVenta());
                attachedVentaList.add(ventaListVentaToAttach);
            }
            producto.setVentaList(attachedVentaList);
            em.persist(producto);
            if (categoriaId != null) {
                categoriaId.getProductoList().add(producto);
                categoriaId = em.merge(categoriaId);
            }
            if (proveedorId != null) {
                proveedorId.getProductoList().add(producto);
                proveedorId = em.merge(proveedorId);
            }
            for (Venta ventaListVenta : producto.getVentaList()) {
                Producto oldProductoIdOfVentaListVenta = ventaListVenta.getProductoId();
                ventaListVenta.setProductoId(producto);
                ventaListVenta = em.merge(ventaListVenta);
                if (oldProductoIdOfVentaListVenta != null) {
                    oldProductoIdOfVentaListVenta.getVentaList().remove(ventaListVenta);
                    oldProductoIdOfVentaListVenta = em.merge(oldProductoIdOfVentaListVenta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto persistentProducto = em.find(Producto.class, producto.getIdProducto());
            Categoria categoriaIdOld = persistentProducto.getCategoriaId();
            Categoria categoriaIdNew = producto.getCategoriaId();
            Proveedor proveedorIdOld = persistentProducto.getProveedorId();
            Proveedor proveedorIdNew = producto.getProveedorId();
            List<Venta> ventaListOld = persistentProducto.getVentaList();
            List<Venta> ventaListNew = producto.getVentaList();
            if (categoriaIdNew != null) {
                categoriaIdNew = em.getReference(categoriaIdNew.getClass(), categoriaIdNew.getIdCategoria());
                producto.setCategoriaId(categoriaIdNew);
            }
            if (proveedorIdNew != null) {
                proveedorIdNew = em.getReference(proveedorIdNew.getClass(), proveedorIdNew.getIdProveedor());
                producto.setProveedorId(proveedorIdNew);
            }
            List<Venta> attachedVentaListNew = new ArrayList<Venta>();
            for (Venta ventaListNewVentaToAttach : ventaListNew) {
                ventaListNewVentaToAttach = em.getReference(ventaListNewVentaToAttach.getClass(), ventaListNewVentaToAttach.getIdVenta());
                attachedVentaListNew.add(ventaListNewVentaToAttach);
            }
            ventaListNew = attachedVentaListNew;
            producto.setVentaList(ventaListNew);
            producto = em.merge(producto);
            if (categoriaIdOld != null && !categoriaIdOld.equals(categoriaIdNew)) {
                categoriaIdOld.getProductoList().remove(producto);
                categoriaIdOld = em.merge(categoriaIdOld);
            }
            if (categoriaIdNew != null && !categoriaIdNew.equals(categoriaIdOld)) {
                categoriaIdNew.getProductoList().add(producto);
                categoriaIdNew = em.merge(categoriaIdNew);
            }
            if (proveedorIdOld != null && !proveedorIdOld.equals(proveedorIdNew)) {
                proveedorIdOld.getProductoList().remove(producto);
                proveedorIdOld = em.merge(proveedorIdOld);
            }
            if (proveedorIdNew != null && !proveedorIdNew.equals(proveedorIdOld)) {
                proveedorIdNew.getProductoList().add(producto);
                proveedorIdNew = em.merge(proveedorIdNew);
            }
            for (Venta ventaListOldVenta : ventaListOld) {
                if (!ventaListNew.contains(ventaListOldVenta)) {
                    ventaListOldVenta.setProductoId(null);
                    ventaListOldVenta = em.merge(ventaListOldVenta);
                }
            }
            for (Venta ventaListNewVenta : ventaListNew) {
                if (!ventaListOld.contains(ventaListNewVenta)) {
                    Producto oldProductoIdOfVentaListNewVenta = ventaListNewVenta.getProductoId();
                    ventaListNewVenta.setProductoId(producto);
                    ventaListNewVenta = em.merge(ventaListNewVenta);
                    if (oldProductoIdOfVentaListNewVenta != null && !oldProductoIdOfVentaListNewVenta.equals(producto)) {
                        oldProductoIdOfVentaListNewVenta.getVentaList().remove(ventaListNewVenta);
                        oldProductoIdOfVentaListNewVenta = em.merge(oldProductoIdOfVentaListNewVenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = producto.getIdProducto();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getIdProducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            Categoria categoriaId = producto.getCategoriaId();
            if (categoriaId != null) {
                categoriaId.getProductoList().remove(producto);
                categoriaId = em.merge(categoriaId);
            }
            Proveedor proveedorId = producto.getProveedorId();
            if (proveedorId != null) {
                proveedorId.getProductoList().remove(producto);
                proveedorId = em.merge(proveedorId);
            }
            List<Venta> ventaList = producto.getVentaList();
            for (Venta ventaListVenta : ventaList) {
                ventaListVenta.setProductoId(null);
                ventaListVenta = em.merge(ventaListVenta);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Producto findProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
