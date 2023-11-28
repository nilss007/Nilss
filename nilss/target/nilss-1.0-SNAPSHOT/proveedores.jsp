<%@page import="com.emergentes.entidades.Proveedor"%>
<%@page import="java.util.List"%>
<%
    List<Proveedor> proveedores = (List<Proveedor>) request.getAttribute("proveedores");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
        body {
            margin: 0;
            padding: 0;
            background: url('imagenes/PROV.jpg') no-repeat center center fixed;
            background-size: cover;
            font-family: 'Arial', sans-serif;
            color: #000000;
            background-attachment: fixed;
        }

        h1 {
            text-align: center;
            font-size: 36px;
            font-weight: bold;
            font-family: 'Georgia', serif;
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            text-align: center;
            background: rgba(255, 255, 255, 0.8);
            border-radius: 5px;
        }

        th, td {
            border: 1px solid #dddddd;
            padding: 8px;
        }

        a.button {
            display: block;
            width: 120px;
            margin: 10px auto;
            padding: 10px;
            text-align: center;
            text-decoration: none;
            background-color: #4CAF50;
            color: white;
            border-radius: 5px;
        }
    </style>
    </head>
    <body>
        <h1>LISTADO DE PROVEEDORES</h1>
        <p><a href="ProveedorServlet?action=add" class="button">Nuevo</a></p>
        <table border="3">
            <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th>Telefono</th>
                <th>Direccion</th>
                <td></td>
                <td></td>
            </tr>
            <%
                for(Proveedor item: proveedores){
            %>
            <tr>
                <td><%= item.getIdProveedor()%></td>
                <td><%= item.getNombreProv()%></td>
                <td><%= item.getTelefono()%></td>
                <td><%= item.getDireccion()%></td>
                <td><a href="ProveedorServlet?action=edit&id=<%= item.getIdProveedor()%>">Editar</a></td>
                <td><a href="ProveedorServlet?action=dele&id=<%= item.getIdProveedor()%>" onclick="return(confirm('Esta seguro?'))">Eliminar</a></td>
            </tr>
            <%
                }
            %>
        </table>
    <p><a href="index.jsp" class="button">Volver al incio</a></p>
    </body>
</html>

