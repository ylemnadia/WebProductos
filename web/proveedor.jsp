<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigate.jspf"%>

<div class="ml-4 mt-4 mr-4 mb-4">
    <div class="row">
        <div class="col-sm-3">
            <div class="card">
                <div class="card-body">
                    <form action="Controlador?menu=Proveedor" method="POST">
                        
                        <div class="form-group">
                            <label>DNI</label>
                            <input type="hidden" value="${proveedor.id}" name="txtId">
                            <input type="text" value="${proveedor.dni}" name="txtDni" class="form-control">
                        </div>
                        
                        <div class="form-group">
                            <label>Nombres</label>
                            <input type="text" value="${proveedor.nombres}" name="txtNombres" class="form-control">
                        </div>
                        
                        <div class="form-group">
                            <label>Correo</label>
                            <input type="email" value="${proveedor.correo}" name="txtCorreo" class="form-control">
                        </div>
                        
                        <div class="form-group">
                            <label>Pago</label>
                            <input type="number" step="0.01" value="${proveedor.pago}" name="txtPago" class="form-control">
                        </div>
                        
                        <input type="submit" name="accion" value="Guardar" class="btn btn-primary">
                    </form>
                </div>
            </div>
        </div>
        
        <div class="col-sm-9">
            <div class="card">
                <div class="card-body">
                    <table class="table table-hover" style="width: 100%" id="example">
                        <thead>
                            <tr class="text-center">
                                <th>#</th>
                                <th>DNI</th>
                                <th>NOMBRES</th>
                                <th>CORREO</th>
                                <th>PAGO</th>
                                <th>ACCION</th>
                            </tr>
                        </thead>
                        <tbody> 
                            <c:forEach var="p" items="${proveedores}" varStatus="provStatus">
                                <tr>
                                    <td class="text-center">${provStatus.index + 1}</td>
                                    <td>${p.dni}</td>
                                    <td>${p.nombres}</td>
                                    <td>${p.correo}</td>
                                    <td>${p.pago}</td>
                                    <td class="text-center d-flex">
                                        <form action="Controlador?menu=Proveedor" method="POST">
                                            <input type="hidden" value="${p.id}" name="id">
                                            <button class="btn btn-outline-warning btn-sm" type="submit" name="accion" value="Editar"><i class="bi bi-pencil-square"></i></button>
                                        </form>
                                        <form action="Controlador?menu=Proveedor" class="ml-1" method="POST">
                                            <input type="hidden" value="${p.id}" name="id">
                                            <button class="btn btn-outline-danger btn-sm" type="submit" name="accion" value="Delete"><i class="bi bi-trash"></i></button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
                        
    </div>
</div>

    <!-- Botón rojo al final con icono de casa -->
        <div class="text-center mt-4">
            <a href="Principal.jsp" class="btn btn-danger btn-lg">
                <i class="fas fa-home"></i> Volver a la Página Principal
            </a>
        </div>                            
                            
<%@ include file="commons/footer.jspf"%>
