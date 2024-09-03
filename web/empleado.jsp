<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigate.jspf"%>

<div class="ml-4 mt-4 mr-4 mb-4">
    <div class="row">
        <div class="col-sm-3">
            <div class="card">
                <div class="card-body">
                    <form action="Controlador?menu=Empleado" method="POST">
                        
                        <div class="form-group">
                            <label>DNI</label>
                            <input type="hidden" value="${empleado.id}" name="txtId">
                            <input type="text" id="txtDni" value="${empleado.dni}" name="txtDni" class="form-control">
                        </div>
                        
                        <div class="form-group">
                            <label>Nombres</label>
                            <input type="text" value="${empleado.nombres}" name="txtNombres" class="form-control">
                        </div>
                        
                        <div class="form-group">
                            <label>Correo</label>
                            <input type="email" value="${empleado.correo}" name="txtCorreo" class="form-control">
                        </div>                      
                        
                        <div class="form-group">
                            <label>Usuario</label>
                            <input type="text" value="${empleado.user}" name="txtUser" class="form-control">
                        </div>
                        
                        <div class="form-group">
                            <label>Contraseña</label>
                            <div class="input-group">
                            <input type="password" value="${empleado.password}" name="txtContra" id="password" class="form-control">
                            <div class="input-group-append">
                                    <button type="button" class="btn btn-outline-secondary" onclick="togglePassword()">
                                        <i class="bi bi-eye-slash" id="togglePasswordIcon"></i>
                                    </button>
                                </div>
                            </div>
                        </div>                                    
                        
                        <div class="form-group">
                            <label>Perfil</label>
                            <input type="text" value="${empleado.perfil}" name="txtPerfil" class="form-control">
                        </div>                        
                        
                        <div class="form-group">
                            <label>Número de Usuario</label>
                            <input type="text" id="txtIdu" value="${empleado.idu}" name="txtIdu" class="form-control" oninput="this.value = this.value.replace(/[^1-9]/g, '')">
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
                                <th>USUARIO</th>
                                <th>CONTRASEÑA</th>
                                <th>PERFIL</th>
                                <th>NUMERO USUARIO</th>
                                <th>ACCION</th>
                            </tr>
                        </thead>
                        <tbody> 
                            <c:forEach var="e" items="${empleados}" varStatus="empStatus">
                                <tr>
                                    <td class="text-center">${empStatus.index + 1}</td>
                                    <td>${e.dni}</td>
                                    <td>${e.nombres}</td>
                                    <td>${e.correo}</td>
                                    <td>${e.user}</td>
                                    <td><input type="password" value="${e.password}" class="form-control" readonly></td>
                                    <td>${e.perfil}</td>
                                    <td>${e.idu}</td>
                                    <td class="text-center d-flex">
                                        <form action="Controlador?menu=Empleado" method="POST">
                                            <input type="hidden" value="${e.id}" name="id">
                                            <button class="btn btn-outline-warning btn-sm" type="submit" name="accion" value="Editar"><i class="bi bi-pencil-square"></i></button>
                                        </form>
                                        <form action="Controlador?menu=Empleado" class="ml-1" method="POST">
                                            <input type="hidden" value="${e.id}" name="id">
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

    <script>
        function togglePassword() {
            const passwordField = document.getElementById('password');
            const toggleIcon = document.getElementById('togglePasswordIcon');
            const type = passwordField.getAttribute('type') === 'password' ? 'text' : 'password';
            passwordField.setAttribute('type', type);
            toggleIcon.classList.toggle('bi-eye');
            toggleIcon.classList.toggle('bi-eye-slash');
        }
    </script>
