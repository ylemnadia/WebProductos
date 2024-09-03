package com.app.productos.controlador;

import com.app.productos.interfaces.IEmpleado;
import com.app.productos.modelo.Empleado;
import com.app.productos.modeloDAO.EmpleadoDAO;
import java.io.IOException;
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
                String user = request.getParameter("txtUser");
                String pass = request.getParameter("txtPass");
                String rememberMe = request.getParameter("rememberMe");
                Empleado e = empDAO.getEmpleadoUserPass(user, pass);
                if (e != null) {
                    session.setAttribute("Empleado", e);                    
                    if (e.getPerfil().equals("ADMINISTRADOR")) {
                                              
                         // Manejar la opción "Recordar contraseña"
                        if (rememberMe != null && rememberMe.equals("on")) {
                            // Crear una cookie para el nombre de usuario
                            Cookie userCookie = new Cookie("username", user);
                            userCookie.setMaxAge(60 * 60 * 24 * 7); // Expira en una semana
                            response.addCookie(userCookie);
                        } else {
                            // Si "Recordar contraseña" no está seleccionado, eliminar la cookie si existe
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
                        request.getRequestDispatcher("Controlador?menu=Principal").forward(request, response);
                    } else {
                        request.setAttribute("Mensaje", "El Perfil no es Administrador");
                        request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                    }
                } else {
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            break;
            case "Salir":
            session.setAttribute("Empleado", new Empleado());
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
