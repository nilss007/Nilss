<%@page import="com.emergentes.entidades.Cliente"%>
<%@page import="java.util.List"%>
<%
    List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
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
            background: url('imagenes/CLIE.jpg') no-repeat center center fixed;
            background-size: cover;
            font-family: Arial, sans-serif;
            color: #000000; /* Cambia el color del texto según tus preferencias */
            background-attachment: fixed; /* Fija la imagen de fondo */
        }

        h1 {
            text-align: center;
            font-size: 36px;
            font-weight: bold;
            font-family: 'Georgia', serif;
            background-color: #4CAF50; /* Color del fondo */
            color: white; /* Color del texto */
            padding: 10px 20px; /* Espaciado dentro del fondo encuadrado */
            border-radius: 5px; /* Bordes redondeados */
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            text-align: center;
            background: rgba(255, 255, 255, 0.8); /* Cambia el fondo de la tabla según tus preferencias */
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
            background-color: #4CAF50; /* Cambia el color de fondo del botón según tus preferencias */
            color: white;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <h1>LISTADO DE CLIENTES</h1>
    <a href="ClienteServlet?action=add" class="button">Nuevo</a>

    <table border="3">
        <tr>
            <th>Id</th>
            <th>Nombre</th>
            <th>Dirección</th>
            <th>Teléfono</th>
            <th></th>
            <th></th>
        </tr>
        <%
            for (Cliente item : clientes) {
        %>
        <tr>
            <td><%= item.getIdCliente() %></td>
            <td><%= item.getNombre() %></td>
            <td><%= item.getDireccion() %></td>
            <td><%= item.getTelefono() %></td>
            <td><a href="ClienteServlet?action=edit&id=<%= item.getIdCliente() %>">Editar</a></td>
            <td><a href="ClienteServlet?action=dele&id=<%= item.getIdCliente() %>" onclick="return(confirm('¿Está seguro?'))">Eliminar</a></td>
        </tr>
        <%
            }
        %>
    </table>

    <a href="index.jsp" class="button">Volver al inicio</a>
</body>
</html>
