<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.Cookie"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Login</title>
    </head>
    <body>
        <div class="container mt-4 col-lg-4">
            <div class="card col-sm-10">
                <div class="card-body">
                    <form class="form-sign" action="ControladorLogin" method="POST">
                        <div class="form-group text-center">
                            <h3>Login</h3>
                            <img src="img/login.png" alt="70" width="170"/>
                            <label>Bienvenidos al Sistema</label>
                        </div>
                        
                        <div class="form-group">
                            <label>Usuario:</label>
                            <input type="text" name="txtUser" value="" class="form-control">
                        </div>
                        
                        <div class="form-group">
                            <label>Password:</label>
                            <div class="input-group">
                                <input type="password" name="txtPass" id="password" class="form-control">
                                <div class="input-group-append">
                                    <button type="button" class="btn btn-outline-secondary" onclick="togglePasswordVisibility()">
                                        <i id="toggleIcon" class="fa fa-eye"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <div class="form-check">
                                <input type="checkbox" class="form-check-input" name="rememberMe" id="rememberMe">
                                <label class="form-check-label" for="rememberMe">Recordar contraseña</label>
                            </div>
                        </div>
                        <input type="submit" name="accion" value="Login" class="btn btn-primary btn-block">
                    </form>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

        <!-- Integración del ícono de FontAwesome para el ojo -->
        <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
        
        <script>
            function togglePasswordVisibility() {
                const passwordInput = document.getElementById('password');
                const toggleIcon = document.getElementById('toggleIcon');
                
                // Alternar entre el tipo de input
                if (passwordInput.type === 'password') {
                    passwordInput.type = 'text';
                    toggleIcon.className = 'fa fa-eye-slash';
                } else {
                    passwordInput.type = 'password';
                    toggleIcon.className = 'fa fa-eye';
                }
            }
        </script>
    </body>
</html>