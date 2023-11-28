<%@page import="com.emergentes.entidades.Venta"%>
<%@page import="java.util.List"%>
<%
    List<Venta> ventas = (List<Venta>) request.getAttribute("ventas");
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
            background: url('imagenes/VEN.jpg') no-repeat center center fixed;
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
    <h1>LISTADO DE VENTAS</h1>
    <p><a href="VentaServlet?action=add" class="button">Nuevo</a></p>

    <table border="3">
        <tr>
            <th>Id</th>
            <th>Cantidad</th>
            <th>Precio</th>
            <th>Descripción</th>
            <td></td>
            <td></td>
        </tr>
        <% for(Venta item: ventas){ %>
            <tr>
                <td><%= item.getIdVenta()%></td>
                <td><%= item.getCantidad() %></td>
                <td><%= item.getFacturaId().getPrecio() %></td>
                <td><%= item.getProductoId().getDescripcion() %></td>
                <td><a href="VentaServlet?action=edit&id=<%= item.getIdVenta()%>">Editar</a></td>
                <td><a href="VentaServlet?action=dele&id=<%= item.getIdVenta()%>" onclick="return(confirm('¿Está seguro?'))">Eliminar</a></td>
            </tr>
        <% } %>
    </table>

    <p><a href="index.jsp" class="button">Volver al inicio</a></p>
</body>
</html>
