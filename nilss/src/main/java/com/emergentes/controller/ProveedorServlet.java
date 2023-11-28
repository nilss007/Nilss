
package com.emergentes.controller;

import com.emergentes.bean.BeanProveedor;
import com.emergentes.entidades.Proveedor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ProveedorServlet", urlPatterns = {"/ProveedorServlet"})
public class ProveedorServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id;
        BeanProveedor daoProveedor = new BeanProveedor();
        Proveedor prov = new Proveedor();
        
        String action =(request.getParameter("action")!=null)? request.getParameter("action") : "view";
        
        switch(action){
            case "add":
                request.setAttribute("proveedor", prov);
                request.getRequestDispatcher("proveedor-edit.jsp").forward(request, response);
                break;
            case "edit":
                id = Integer.parseInt(request.getParameter("id"));
                prov=daoProveedor.buscar(id);
                request.setAttribute("proveedor", prov);
                request.getRequestDispatcher("proveedor-edit.jsp").forward(request, response);
                break;
            case "dele":
                id = Integer.parseInt(request.getParameter("id"));
                daoProveedor.eliminar(id);
                response.sendRedirect("ProveedorServlet");
                break;
            case "view":
                List<Proveedor> lista = daoProveedor.listarTodos();
                request.setAttribute("proveedores", lista);
                request.getRequestDispatcher("proveedores.jsp").forward(request, response);
                break;
        }
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        BeanProveedor daoProveedor = new BeanProveedor();
         
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre =request.getParameter("nombre");
        int telefono = Integer.parseInt(request.getParameter("telefono"));
        String direccion =request.getParameter("direccion");

        
        Proveedor prov = new Proveedor();
        prov.setIdProveedor(id);
        prov.setNombreProv(nombre);
        prov.setTelefono(telefono);
        prov.setDireccion(direccion);
        
        if (id > 0){
            daoProveedor.editar(prov);
        }
        else{
            daoProveedor.insertar(prov);
        }
        response.sendRedirect("ProveedorServlet");
        
    }


}
