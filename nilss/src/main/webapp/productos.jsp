<%@page import="com.emergentes.entidades.Producto"%>
<%@page import="java.util.List"%>
<%
    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
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
            background: url('imagenes/PROD.jpg') no-repeat center center fixed;
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
        <h1>LISTADO DE PRODUCTOS</h1>
        <p><a href="ProductoServlet?action=add" class="button">Nuevo</a></p>
        <table border="3">
            <tr>
                <th>Id</th>
                <th>Descripcion</th>
                <th>Precio</th>
                <th>Disponible</th>
                <th>Categoria</th>
                <th>Proveedor</th>
                <td></td>
                <td></td>
            </tr>
            <%
                for(Producto item: productos){
            %>
            <tr>
                <td><%= item.getIdProducto()%></td>
                <td><%= item.getDescripcion()%></td>
                <td><%= item.getPrecio()%></td>
                <td>
                    <input type="checkbox" name="disponible"<%= (item.getDisponible() ==1)? "checked" : "" %> readonly>
                </td>
                <td><%= item.getCategoriaId().getDescripcion() %></td>
                <td><%= item.getProveedorId().getNombreProv() %></td>
                <td><a href="ProductoServlet?action=edit&id=<%= item.getIdProducto()%>">Editar</a></td>
                <td><a href="ProductoServlet?action=dele&id=<%= item.getIdProducto()%>" onclick="return(confirm('Esta seguro?'))">Eliminar</a></td>
            </tr>
            <%
                }
            %>
        </table>
    <p><a href="index.jsp" class="button">Volver al incio</a></p>
    </body>
</html>
