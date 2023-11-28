<%@page import="com.emergentes.entidades.Factura"%>
<%@page import="java.util.List"%>
<%
     List<Factura> facturas = (List<Factura>) request.getAttribute("facturas");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
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
    <h1>LISTADO DE FACTURAS</h1>
    <p><a href="FacturaServlet?action=add" class="button">Nuevo</a></p>
    <table border="3">
        <tr>
            <th>Id</th>
            <th>Precio</th>
            <th>Fecha</th>
            <th>Cliente</th>
            <td></td>
            <td></td>
        </tr>
        <%
            for(Factura item: facturas){
        %>
        <tr>
            <td><%= item.getIdFactura()%></td>
            <td><%= item.getPrecio() %></td>
            <td><%= item.getFecha() %></td>
            <td><%= item.getClienteId().getNombre() %></td>

            <td>
                <a href="#" onclick="generarPDF('<%= item.getIdFactura()%>', '<%= item.getFecha() %>', '<%= item.getClienteId().getNombre() %>')">Imprimir</a>
            </td>
            <td><a href="FacturaServlet?action=dele&id=<%= item.getIdFactura()%>" onclick="return(confirm('Esta seguro?'))">Eliminar</a></td>
        </tr>
        <%
            }
        %>
    </table>

    <script>
        
        function generarPDF(id, precio, fecha, cliente) {
            var pdfContent = `
                <h1>Detalle de Factura</h1>
                <p>ID: ${id}</p>
                <p>ID: ${precio}</p>
                <p>Fecha: ${fecha}</p>
                <p>Cliente: ${cliente}</p>
            `;

            html2pdf().from(pdfContent).save();
        }
    </script>
    <p><a href="index.jsp" class="button">Volver al incio</a></p>
</body>
</html>