
package com.emergentes.controller;

import com.emergentes.bean.BeanCliente;
import com.emergentes.entidades.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ClienteServlet", urlPatterns = {"/ClienteServlet"})
public class ClienteServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id;
        BeanCliente daoCliente = new BeanCliente();
        Cliente cli = new Cliente();
        
        String action =(request.getParameter("action")!=null)? request.getParameter("action") : "view";
        
        switch(action){
            case "add":
                request.setAttribute("cliente", cli);
                request.getRequestDispatcher("cliente-edit.jsp").forward(request, response);
                break;
            case "edit":
                id = Integer.parseInt(request.getParameter("id"));
                cli=daoCliente.buscar(id);
                request.setAttribute("cliente", cli);
                request.getRequestDispatcher("cliente-edit.jsp").forward(request, response);
                break;
            case "dele":
                id = Integer.parseInt(request.getParameter("id"));
                daoCliente.eliminar(id);
                response.sendRedirect("ClienteServlet");
                break;
            case "view":
                List<Cliente> lista = daoCliente.listarTodos();
                request.setAttribute("clientes", lista);
                request.getRequestDispatcher("clientes.jsp").forward(request, response);
                break;
        }
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        BeanCliente daoCliente = new BeanCliente();
         
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre =request.getParameter("nombre");
        String direccion =request.getParameter("direccion");
        int telefono = Integer.parseInt(request.getParameter("telefono"));
        

        
        Cliente cli = new Cliente();
        cli.setIdCliente(id);
        cli.setNombre(nombre);
        cli.setDireccion(direccion);
        cli.setTelefono(telefono);
        
        
        if (id > 0){
            daoCliente.editar(cli);
        }
        else{
            daoCliente.insertar(cli);
        }
        response.sendRedirect("ClienteServlet");
        
    }

}
