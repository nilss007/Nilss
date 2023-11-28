
package com.emergentes.controller;

import com.emergentes.bean.BeanFactura;
import com.emergentes.bean.BeanProducto;
import com.emergentes.bean.BeanVenta;
import com.emergentes.entidades.Factura;
import com.emergentes.entidades.Producto;
import com.emergentes.entidades.Venta;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "VentaServlet", urlPatterns = {"/VentaServlet"})
public class VentaServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id;
        BeanVenta daoVenta = new BeanVenta();
        BeanFactura daoFactura = new BeanFactura();
        BeanProducto daoProducto = new BeanProducto();
        Venta prod = new Venta();
        List<Factura> lista;
        List<Producto> listass;
        
        String action =(request.getParameter("action")!=null)? request.getParameter("action") : "view";
        
        switch(action){
            case "add":
                lista = daoFactura.listarTodos();
                listass = daoProducto.listarTodos();
                request.setAttribute("facturas", lista);
                request.setAttribute("productos", listass);
                request.setAttribute("venta", prod);
                request.getRequestDispatcher("venta-edit.jsp").forward(request, response);
                break;
            case "edit":
                id = Integer.parseInt(request.getParameter("id"));
                prod=daoVenta.buscar(id);
                lista = daoFactura.listarTodos();
                listass = daoProducto.listarTodos();
                request.setAttribute("facturas", lista);
                request.setAttribute("productos", listass);
                request.setAttribute("venta", prod);
                request.getRequestDispatcher("venta-edit.jsp").forward(request, response);
                break;
            case "dele":
                id = Integer.parseInt(request.getParameter("id"));
                daoProducto.eliminar(id);
                response.sendRedirect("VentaServlet");
                break;
            case "view":
                List<Venta> prods = daoVenta.listarTodos();
                request.setAttribute("ventas", prods);
                request.getRequestDispatcher("ventas.jsp").forward(request, response);
                break;
        }
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        BeanVenta daoVenta = new BeanVenta();
        BeanFactura daoFactura = new BeanFactura();
        BeanProducto daoProducto = new BeanProducto();
         
        int id = Integer.parseInt(request.getParameter("id"));
        int cantidad =Integer.parseInt(request.getParameter("cantidad"));
        int factura_id = Integer.parseInt(request.getParameter("factura_id"));
        int producto_id = Integer.parseInt(request.getParameter("producto_id"));
        
        Factura fac = daoFactura.buscar(factura_id);
        Producto pro = daoProducto.buscar(producto_id);
        
        Venta ven = new Venta();
        ven.setIdVenta(id);
        ven.setCantidad(cantidad);
        ven.setFacturaId(fac);
        ven.setProductoId(pro);
        
        if (id > 0){
            daoVenta.editar(ven);
        }
        else{
            daoVenta.insertar(ven);
        }
        response.sendRedirect("VentaServlet");
        
    }


}
