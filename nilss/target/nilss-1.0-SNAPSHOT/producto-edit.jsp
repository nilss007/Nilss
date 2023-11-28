<%@page import="com.emergentes.entidades.Proveedor"%>
<%@page import="com.emergentes.entidades.Categoria"%>
<%@page import="java.util.List"%>
<%@page import="com.emergentes.entidades.Producto"%>
<%
    Producto prod = (Producto) request.getAttribute("producto");
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
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
        <h1>REGISTRO DE PRODUCTOS</h1>
        <form action="ProductoServlet" method="post">
            <input type="hidden" name="id" value="<%= prod.getIdProducto() %>">
            <table>
                <tr>
                    <td>Descripcion:</td>
                    <td><input type="text" name="descripcion" value="<%= prod.getDescripcion()%>"></td>
                </tr>
                <tr>
                    <td>Precio:</td>
                    <td><input type="text" name="precio" value="<%= prod.getPrecio() %>"></td>
                </tr>
                <tr>
                    <td>Disponible:</td>
                    <td>
                        <input type="radio" name="disponible" value="0" <%= (prod.getDisponible()== 0)? "checked":""%>> No Disponible
                        <input type="radio" name="disponible" value="1" <%= (prod.getDisponible() == 1)? "checked":""%>> Disponible
                    </td>
                </tr>
                <tr>
                    <td>Categoria:</td>
                    <td>
                        <select name="categoria_id">
                            <%
                                for(Categoria item: categorias){
                            %>
                            <option value="<%= item.getIdCategoria()%>"<%= (item.getIdCategoria()== prod.getCategoriaId().getIdCategoria())? "selected":""%>>
                                <%= item.getDescripcion()%>
                            </option>
                            <%
                                }
                            %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Proveedor:</td>
                    <td>
                        <select name="proveedor_id">
                            <%
                                for(Proveedor itemm: proveedores){
                            %>
                            <option value="<%= itemm.getIdProveedor()%>"<%= (itemm.getIdProveedor()== prod.getProveedorId().getIdProveedor())? "selected":""%>>
                                <%= itemm.getNombreProv()%>
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

