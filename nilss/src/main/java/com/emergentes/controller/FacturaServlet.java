
package com.emergentes.controller;

import com.emergentes.bean.BeanCliente;
import com.emergentes.bean.BeanFactura;
import com.emergentes.entidades.Cliente;
import com.emergentes.entidades.Factura;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;


@WebServlet(name = "FacturaServlet", urlPatterns = {"/FacturaServlet"})
public class FacturaServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id;
        BeanFactura daoFactura = new BeanFactura();
        BeanCliente daoCliente = new BeanCliente();
        Factura fac = new Factura();
        List<Cliente> lista;
        
        String action =(request.getParameter("action")!=null)? request.getParameter("action") : "view";
        
        switch(action){
            case "add":
                lista = daoCliente.listarTodos();
                request.setAttribute("clientes", lista);
                request.setAttribute("factura", fac);
                request.getRequestDispatcher("factura-edit.jsp").forward(request, response);
                break;
            case "edit":
                id = Integer.parseInt(request.getParameter("id"));
                fac=daoFactura.buscar(id);
                lista = daoCliente.listarTodos();
                request.setAttribute("clientes", lista);
                request.setAttribute("factura", fac);
                request.getRequestDispatcher("factura-edit.jsp").forward(request, response);
                request.getRequestDispatcher("factura.jsp").forward(request, response);
                break;
            case "dele":
                id = Integer.parseInt(request.getParameter("id"));
                daoFactura.eliminar(id);
                response.sendRedirect("FacturaServlet");
                break;
            case "view":
                List<Factura> prods = daoFactura.listarTodos();
                request.setAttribute("facturas", prods);
                request.getRequestDispatcher("facturas.jsp").forward(request, response);
                break;
        }
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        BeanFactura daoFactura = new BeanFactura();
        BeanCliente daoCliente = new BeanCliente();
         
        int id = Integer.parseInt(request.getParameter("id"));
        int precio = Integer.parseInt(request.getParameter("precio"));
        ////---------INSERTAR FECHA--------////
        Date fecha = new Date(System.currentTimeMillis());
        //java.sql.Date mFechaSql = new java.sql.Date(fecha.getTime());
        ////
        int cliente_id = Integer.parseInt(request.getParameter("cliente_id"));
        
        
        
        
        Cliente clie = daoCliente.buscar(cliente_id);
        
        Factura fac = new Factura();
        fac.setIdFactura(id);
        fac.setPrecio(precio);
        fac.setFecha(fecha);
        fac.setClienteId(clie);

        
        if (id > 0){
            daoFactura.editar(fac);
        }
        else{
            daoFactura.insertar(fac);
        }
        response.sendRedirect("FacturaServlet");
        
    }


}
