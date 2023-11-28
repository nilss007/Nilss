
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Farmacia Nilss</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Tangerine">
    <style>
        body {
            margin: 0;
            padding: 0;
            background-image: url('imagenes/principal.jpg');
            background-size: cover;
            background-position: center;
            font-family: 'Tangerine', serif;
            color: #D713E1;
            font-size: 30px;
        }

        h1 {
            text-align: center;
            padding: 0px;
            font-size: 80px; /* Tamaño de fuente modificado */
        }

        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            text-align: center;
        }

        li {
            display: inline;
            margin-right: 50px;
        }

        a {
            display: inline-block;
            padding: 10px;
            text-decoration: none;
            color: #D713E1;
            border: 2px solid #D713E1;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        a:hover {
            background-color: #D713E1;
            color: white;
        }

        select {
            padding: 5px;
        }
    </style>
</head>
<body>

    <h1>Farmacia Nilss</h1>

    <ul>
        <li><a href="CategoriaServlet">Categorias</a></li>
        <li><a href="ClienteServlet">Clientes</a></li>
        <li><a href="ProveedorServlet">Proveedores</a></li>
        <li><a href="FacturaServlet">Facturas</a></li>
        <li><a href="ProductoServlet">Productos</a></li>
        <li><a href="VentaServlet">Ventas</a></li>
    </ul>

</body>
</html>
