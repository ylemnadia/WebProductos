<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigate.jspf"%>

<div class="container mt-4">
    
    <!-- Título Principal -->
    <div class="row text-center mt-4">
        <div class="col-12">
            <h1 class="display-4">¡Bienvenido a PlAsMa' System!</h1>
            <p class="lead">Gestiona tus productos y equipo de trabajo de manera eficiente.</p>
        </div>
    </div>
    
    <!-- Banner Rotativo / Carrusel -->
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
        </ol>
    
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="img/icons/banner1.png" class="d-block w-100" alt="Banner 1">
            <div class="carousel-caption d-none d-md-block" style="background: linear-gradient(to right, rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0.3)); padding: 15px; border-radius: 8px;">
                <h5 style="color: #ffffff;">Gestiona tus productos fácilmente</h5>
                <p style="color: #ffffff;">Accede a todas las funcionalidades desde un solo lugar.</p>
            </div>
        </div>
        
        <div class="carousel-item">
            <img src="img/icons/banner2.png" class="d-block w-100" alt="Banner 2">
            <div class="carousel-caption d-none d-md-block" style="background: linear-gradient(to right, rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0.3)); padding: 15px; border-radius: 8px;">
                <h5 style="color: #ffffff;">Optimiza el trabajo de tu equipo</h5>
                <p style="color: #ffffff;">Controla y monitorea información de tus empleados.</p>
            </div>
        </div>
        
        <div class="carousel-item">
            <img src="img/icons/banner3.png" class="d-block w-100" alt="Banner 3">
            <div class="carousel-caption d-none d-md-block" style="background: linear-gradient(to right, rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0.3)); padding: 15px; border-radius: 8px;">
                <h5 style="color: #ffffff;">Visualiza reportes detallados</h5>
                <p style="color: #ffffff;">Toma decisiones informadas con nuestras herramientas.</p>
            </div>
        </div>
    </div>
    
        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Anterior</span>
        </a>

        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Siguiente</span>
        </a>
    </div>
        
    <!-- Sección adicional con tarjetas informativas -->
    <div class="row mt-5">
        <div class="col-md-4">
            <div class="card border-primary">
                <div class="card-header bg-primary text-white">Noticias Recientes</div>
                <div class="card-body">
                    <p class="card-text">Mantente al tanto de las últimas novedades en gestión de productos y empleados.</p>
                    <a href="#" class="btn btn-outline-primary">Leer Más</a>
                </div>
            </div>
        </div>
        
        <div class="col-md-4">
            <div class="card border-success">
                <div class="card-header bg-success text-white">Ofertas y Promociones</div>
                <div class="card-body">
                    <p class="card-text">Descubre las promociones disponibles para optimizar tu inventario.</p>
                    <a href="#" class="btn btn-outline-success">Ver Detalles</a>
                </div>
            </div>
        </div>
        
        <div class="col-md-4">
            <div class="card border-danger">
                <div class="card-header bg-danger text-white">Alertas y Notificaciones</div>
                <div class="card-body">
                    <p class="card-text">Revisa las alertas más importantes para no perder oportunidades.</p>
                    <a href="#" class="btn btn-outline-danger">Ver Alertas</a>
                </div>
            </div>
        </div>
    </div>
    
    
    <!-- Tarjetas con Botones para Navegación -->
    <div class="row justify-content-center mt-4">
        <div class="col-md-6 col-lg-4">
            <div class="card text-white bg-warning mb-3">
                <div class="card-body text-center">
                    <h4 class="card-title">Accesos Rápidos</h4>
                    <a href="Controlador?menu=Producto&accion=Listar" class="btn btn-primary btn-block mb-2">Ver Productos</a>
                    <a href="Controlador?menu=Categoria&accion=Listar" class="btn btn-secondary btn-block mb-2">Ver Categorías</a>
                    <a href="Controlador?menu=Usuario&accion=Listar" class="btn btn-success btn-block mb-2">Ver Usuarios</a>
                    <a href="Controlador?menu=Proveedor&accion=Listar" class="btn btn-danger btn-block mb-2">Ver Proveedores</a>                    
                    <a href="privacidad.jsp" class="btn btn-info btn-block mb-2">Política de Privacidad</a>
                    <a href="politica.jsp" class="btn btn-dark btn-block">Términos y Condiciones</a>
                </div>
            </div>
        </div>
    </div>
    
</div>

<%@ include file="commons/footer.jspf"%>
