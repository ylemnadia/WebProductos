<%@page import="com.app.productos.controlador.Controlador"%>
<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigate.jspf"%>

<%
    // Obtener los totales desde el controlador
    Controlador controlador = new Controlador();
    int totalCategorias = controlador.getTotalCategorias();
    int totalProductos = controlador.getTotalProductos();
    int totalEmpleados = controlador.getTotalEmpleados();
    int totalProveedores = controlador.getTotalProveedores();
    int totalUsuarios = controlador.getTotalUsuarios();
%>

    <div class="container mt-4">  
    
        <!-- Subtítulos informativos -->
        <div class="row text-center mt-4">
            <div class="col-12">
                <h3 class="mb-3">Panel de Control</h3>
                <p>Accede rápidamente a la información y navega a las secciones principales de la aplicación.</p>
            </div>
        </div>

        <!-- Tarjetas de Totales y Navegación -->
        <div class="row text-center mt-4">
            <!-- Tarjeta de Total de Categorías -->
            <div class="col-md-4">
                <div class="card text-white bg-info mb-3">
                    <div class="card-body">
                        <h4 class="card-title">Total de Categorías</h4>
                        <p class="card-text"><strong><%= totalCategorias %></strong></p>
                    </div>
                </div>
            </div>

            <!-- Tarjeta de Total de Productos -->
            <div class="col-md-4">
                <div class="card text-white bg-success mb-3">
                    <div class="card-body">
                        <h4 class="card-title">Total de Productos</h4>
                        <p class="card-text"><strong><%= totalProductos %></strong></p>
                    </div>
                </div>
            </div>

            <!-- Tarjeta de Total de Empleados -->        
            <div class="col-md-4">
                <div class="card text-white bg-danger mb-3">
                    <div class="card-body">
                        <h4 class="card-title">Total de Empleados</h4>
                        <p class="card-text"><strong><%= totalEmpleados %></strong></p>
                    </div>
                </div>
            </div>  

            <!-- Tarjeta de Total de Proveedores-->        
            <div class="col-md-4">
                <div class="card text-white bg-danger mb-3">
                    <div class="card-body">
                        <h4 class="card-title">Total de Proveedores</h4>
                        <p class="card-text"><strong><%= totalProveedores %></strong></p>
                    </div>
                </div>
            </div>   

            <!-- Tarjeta de Total de Usuarios-->        
            <div class="col-md-4">
                <div class="card text-white bg-warning mb-3">
                    <div class="card-body">
                        <h4 class="card-title">Total de Usuarios</h4>
                        <p class="card-text"><strong><%= totalUsuarios %></strong></p>
                    </div>
                </div>
            </div>                       
        </div>             
    </div>
                    
<%@ include file="commons/footer.jspf"%>
               