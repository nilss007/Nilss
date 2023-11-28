<%@page import="com.emergentes.entidades.Categoria"%>
<%
    Categoria cate = (Categoria) request.getAttribute("categoria");
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
            background: url('imagenes/CATE.jpg') no-repeat center center fixed;
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
    <h1>EDITAR CATEGORIAS</h1>
    
    <form action="CategoriaServlet" method="post">
        <label>Descripción</label>
        <input type="hidden" name="id" value="<%= cate.getIdCategoria()%>">
        <input type="text" name="descripcion" value="<%= cate.getDescripcion()%>">
        <tr>
                <td></td>
                <td><input type="submit"></td>
            </tr>
    </form>

    <p><a href="index.jsp" class="button">Volver al inicio</a></p>
</body>
</html>


