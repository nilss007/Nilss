
package com.emergentes.controller;

import com.emergentes.bean.BeanCategoria;
import com.emergentes.bean.BeanProducto;
import com.emergentes.bean.BeanProveedor;
import com.emergentes.entidades.Categoria;
import com.emergentes.entidades.Producto;
import com.emergentes.entidades.Proveedor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ProductoServlet", urlPatterns = {"/ProductoServlet"})
public class ProductoServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id;
        BeanProducto daoProducto = new BeanProducto();
        BeanCategoria daoCategoria = new BeanCategoria();
        BeanProveedor daoProveedor = new BeanProveedor();
        Producto prod = new Producto();
        List<Categoria> lista;
        List<Proveedor> listass;
        
        String action =(request.getParameter("action")!=null)? request.getParameter("action") : "view";
        
        switch(action){
            case "add":
                lista = daoCategoria.listarTodos();
                listass = daoProveedor.listarTodos();
                request.setAttribute("categorias", lista);
                request.setAttribute("proveedores", listass);
                request.setAttribute("producto", prod);
                request.getRequestDispatcher("producto-edit.jsp").forward(request, response);
                break;
            case "edit":
                id = Integer.parseInt(request.getParameter("id"));
                prod=daoProducto.buscar(id);
                lista = daoCategoria.listarTodos();
                listass = daoProveedor.listarTodos();
                request.setAttribute("categorias", lista);
                request.setAttribute("proveedores", listass);
                request.setAttribute("producto", prod);
                request.getRequestDispatcher("producto-edit.jsp").forward(request, response);
                break;
            case "dele":
                id = Integer.parseInt(request.getParameter("id"));
                daoProducto.eliminar(id);
                response.sendRedirect("ProductoServlet");
                break;
            case "view":
                List<Producto> prods = daoProducto.listarTodos();
                request.setAttribute("productos", prods);
                request.getRequestDispatcher("productos.jsp").forward(request, response);
                break;
        }
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        BeanProducto daoProducto = new BeanProducto();
        BeanCategoria daoCategoria = new BeanCategoria();
        BeanProveedor daoProveedor = new BeanProveedor();
         
        int id = Integer.parseInt(request.getParameter("id"));
        String descripcion =request.getParameter("descripcion");
        int precio =Integer.parseInt(request.getParameter("precio"));
        int disponible =Integer.parseInt(request.getParameter("disponible"));
        int categoria_id = Integer.parseInt(request.getParameter("categoria_id"));
        int proveedor_id = Integer.parseInt(request.getParameter("proveedor_id"));
        
        Categoria cate = daoCategoria.buscar(categoria_id);
        Proveedor prov = daoProveedor.buscar(proveedor_id);
        
        Producto prod = new Producto();
        prod.setIdProducto(id);
        prod.setDescripcion(descripcion);
        prod.setPrecio(precio);
        prod.setDisponible(disponible);
        prod.setCategoriaId(cate);
        prod.setProveedorId(prov);
        
        if (id > 0){
            daoProducto.editar(prod);
        }
        else{
            daoProducto.insertar(prod);
        }
        response.sendRedirect("ProductoServlet");
        
    }


}
