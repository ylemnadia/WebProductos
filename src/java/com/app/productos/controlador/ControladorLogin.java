package com.app.productos.controlador;

import com.app.productos.config.ConectarBD;
import com.app.productos.interfaces.IEmpleado;
import com.app.productos.modelo.Empleado;
import com.app.productos.modeloDAO.EmpleadoDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ControladorLogin extends HttpServlet {

    private final IEmpleado empDAO = new EmpleadoDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        HttpSession session = request.getSession();
        
        switch (accion) {
            case "Login":
                String username = request.getParameter("txtUser");
                String password = request.getParameter("txtPass");
                String rememberMe = request.getParameter("rememberMe");

                try {
                    // Crear la conexión a la base de datos
                    Connection connection = ConectarBD.Conectar();

                    // Consulta vulnerable (solo como ejemplo; no se recomienda en producción)
                    String query = "SELECT * FROM usuario WHERE c_user = '" + username + "' AND c_password = '" + password + "'";
                    Statement stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    if (rs.next()) {
                        // Crear el objeto Empleado basado en los datos obtenidos
                        Empleado empleado = empDAO.getEmpleadoUserPass(username, password);

                        if (empleado != null) {
                            // Guardar el empleado en la sesión
                            session.setAttribute("Empleado", empleado);

                            // Manejar la opción "Recordar contraseña"
                            if (rememberMe != null && rememberMe.equals("on")) {
                                Cookie userCookie = new Cookie("username", username);
                                userCookie.setMaxAge(60 * 60 * 24 * 7); // Expira en una semana
                                response.addCookie(userCookie);
                            } else {
                                Cookie[] cookies = request.getCookies();
                                if (cookies != null) {
                                    for (Cookie cookie : cookies) {
                                        if (cookie.getName().equals("username")) {
                                            cookie.setMaxAge(0); // Eliminar la cookie
                                            response.addCookie(cookie);
                                        }
                                    }
                                }
                            }

                            // Redirigir al menú principal
                            request.getRequestDispatcher("Controlador?menu=Principal").forward(request, response);
                        } else {
                            request.setAttribute("Mensaje", "El Perfil no es Administrador");
                            request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                        }
                    } else {
                        // Si las credenciales son incorrectas
                        request.setAttribute("Mensaje", "Usuario o contraseña incorrectos");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }

                    // Cerrar recursos
                    rs.close();
                    stmt.close();
                    connection.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "Salir":
                session.invalidate(); // Limpiar la sesión al salir
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controlador para el manejo de la autenticación de usuarios";
    }
}
