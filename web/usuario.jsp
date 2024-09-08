<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigate.jspf"%>

<div class="ml-4 mt-4 mr-4 mb-4">
    <div class="row">
        <div class="col-sm-3">
            <div class="card">
                <div class="card-body">
                    <form action="Controlador?menu=Usuario" method="POST">
                        <div class="form-group">
                            <label>Usuario</label>
                            <input type="hidden" value="${usuario.idu}" name="txtId">
                            <input type="text" value="${usuario.user}" name="txtUser" class="form-control">
                        </div>
                        
                        <div class="form-group">
                            <label>Contraseña</label>
                            <div class="input-group">
                                <input type="password" value="${usuario.password}" name="txtPassword" id="password" class="form-control">
                                <div class="input-group-append">
                                    <button type="button" class="btn btn-outline-secondary" onclick="togglePassword()">
                                        <i class="bi bi-eye-slash" id="togglePasswordIcon"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                        
                                <div class="form-group">
                            <label>Perfil</label>
                            <input type="text" value="${usuario.perfil}" name="txtPerfil" class="form-control">
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
                                <th>USUARIO</th>
                                <th>PERFIL</th>
                                <th>ACCION</th>
                            </tr>
                        </thead>
                        <tbody> 
                            <c:forEach var="u" items="${usuarios}" varStatus="userStatus">
                                <tr>
                                    <td class="text-center">${userStatus.index + 1}</td>
                                    <td>${u.user}</td>
                                    <td>${u.perfil}</td>
                                    <td class="text-center d-flex">
                                        <form action="Controlador?menu=Usuario" method="POST">
                                            <input type="hidden" value="${u.idu}" name="id">
                                            <button class="btn btn-outline-warning btn-sm" type="submit" name="accion" value="Editar"><i class="bi bi-pencil-square"></i></button>
                                        </form>
                                        <form action="Controlador?menu=Usuario" class="ml-1" method="POST">
                                            <input type="hidden" value="${u.idu}" name="id">
                                            <button class="btn btn-outline-danger btn-sm" type="submit" name="accion" value="Delete"><i class="bi bi-trash"></i></button>
                                        </form>
                                        <form action="Controlador?menu=Empleado" class="ml-1" method="POST">
                                            <input type="hidden" value="${u.user}" name="txtUser">
                                            <input type="hidden" value="${u.password}" name="txtContra">
                                            <input type="hidden" value="${u.perfil}" name="txtPerfil">
                                            <input type="hidden" value="${u.idu}" name="txtIdu">
                                            <button class="btn btn-outline-info btn-sm" type="submit" name="accion" value="CompletarDatos"><i class="bi bi-person-plus"></i> Completar Datos</button>
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
                                            
                        

        <!-- Nueva sección de comentarios -->
        <div class="card mt-4">
            <div class="card-body">
                <h5>Deja tu comentario:</h5>
                <form action="Controlador?menu=Usuario&accion=GuardarComentario" method="POST">
                    <div class="form-group">
                        <input type="text" name="comentario" class="form-control" placeholder="Escribe un comentario...">
                    </div>
                    <button type="submit" class="btn btn-primary">Enviar</button>
                </form>
            </div>
        </div>

        <!-- Mostrar comentario vulnerable a XSS -->
        <div class="card mt-4">
            <div class="card-body">
                <h5>Comentario Recibido:</h5>
                <p>${sessionScope.comentario}</p>
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
