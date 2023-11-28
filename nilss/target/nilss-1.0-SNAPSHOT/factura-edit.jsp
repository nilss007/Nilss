<%@page import="com.emergentes.entidades.Cliente"%>
<%@page import="java.util.List"%>
<%@page import="com.emergentes.entidades.Factura"%>
<%
    Factura prod = (Factura) request.getAttribute("factura");
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
            background: url('imagenes/FAC.jpg') no-repeat center center fixed;
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
        <h1>REGISTRO DE FACTURA</h1>
        <form action="FacturaServlet" method="post">
            <input type="hidden" name="id" value="<%= prod.getIdFactura() %>">
            <input type="hidden" name="fecha" value="<%= prod.getFecha() %>">
            <table>
                <tr>
                    <td>Precio:</td>
                    <td><input type="text" name="precio" value="<%= prod.getPrecio() %>"></td>
                </tr>
                <tr>
                    <td>Cliente:</td>
                    <td>
                        <select name="cliente_id">
                            <%
                                for(Cliente item: clientes){
                            %>
                            <option value="<%= item.getIdCliente()%>"<%= (item.getIdCliente()== prod.getClienteId().getIdCliente())? "selected":""%>>
                                <%= item.getNombre() %>
                            </option>
                            <%
                                }
                            %>
                        </select>
                    </td>
                </tr>
                
                <tr>
                    <td></td>
                    <td><input type="submit"></td>
                </tr>
            </table> 
        </form>
        <p><a href="index.jsp" class="button">Volver al incio</a></p>
    </body>
</html>
