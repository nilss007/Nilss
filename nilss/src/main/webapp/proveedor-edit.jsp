<%@page import="com.emergentes.entidades.Proveedor"%>
<%
    Proveedor prov = (Proveedor) request.getAttribute("proveedor");
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
        
        form {
            width: 50%;
            margin: 20px auto;
            text-align: left;
            background: rgba(255, 255, 255, 0.8);
            padding: 20px;
            border-radius: 5px;
        }
        
        table {
            width: 60%;
            margin: 20px auto;
            border-collapse: collapse;
            text-align: left;
            background: rgba(255, 255, 255, 0.8);
            border-radius: 5px;
            padding: 20px;
        }

        td {
            padding: 10px;
        }

        input[type="text"],
        select {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        a {
            text-decoration: none;
            color: #ffffff;
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
        <h1>REGISTRO DE PROVEEDOR</h1>
        <form action="ProveedorServlet" method="post">
            <input type="hidden" name="id" value="<%= prov.getIdProveedor()%>">
            <table>
                <tr>
                    <td>Nombre:</td>
                    <td><input type="text" name="nombre" value="<%= prov.getNombreProv()%>"></td>
                </tr>
                <tr>
                    <td>Telefono:</td>
                    <td><input type="text" name="telefono" value="<%= prov.getTelefono()%>"></td>
                </tr>
                <tr>
                    <td>Direccion:</td>
                    <td><input type="text" name="direccion" value="<%= prov.getDireccion() %>"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit"></td>
                </tr>
            </table> 
                <p><a href="index.jsp" class="button">Volver al incio</a></p>
        </body>
</html>
