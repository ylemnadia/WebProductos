package com.app.productos.controlador;

import com.app.productos.interfaces.ICategoria;
import com.app.productos.interfaces.IEmpleado;
import com.app.productos.interfaces.IProducto;
import com.app.productos.interfaces.IProveedor;
import com.app.productos.interfaces.IUsuario;

import com.app.productos.modelo.Categoria;
import com.app.productos.modelo.Empleado;
import com.app.productos.modelo.Producto;
import com.app.productos.modelo.Proveedor;
import com.app.productos.modelo.Usuario;

import com.app.productos.modeloDAO.CategoriaDAO;
import com.app.productos.modeloDAO.EmpleadoDAO;
import com.app.productos.modeloDAO.ProductoDAO;
import com.app.productos.modeloDAO.ProveedorDAO;
import com.app.productos.modeloDAO.UsuarioDAO;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controlador extends HttpServlet {
    private final IProducto prodDAO;
    private final ICategoria catDAO;
    private final IEmpleado empDAO;
    private final IProveedor provDAO;
    private final IUsuario userDAO;

    public Controlador() {
        prodDAO = new ProductoDAO();
        catDAO = new CategoriaDAO();
        empDAO = new EmpleadoDAO();  
        provDAO = new ProveedorDAO();
        userDAO = new UsuarioDAO(); 
    }
    
    public int getTotalCategorias() {
    return catDAO.contarCategorias();
    }

    public int getTotalProductos() {
    return prodDAO.contarProductos();
    }
    
    public int getTotalEmpleados() {
    return empDAO.contarEmpleados();
    }

    public int getTotalProveedores() {
    return provDAO.contarProveedores();
    }
     
    public int getTotalUsuarios() {
    return userDAO.contarUsuarios();
    }
    
    
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
            if (menu.equals("Principal")) {
                request.getRequestDispatcher("Principal.jsp").forward(request, response);
            }
        
            if (menu.equals("Producto")){
                switch (accion) {
                    case "Filtrar":
                        int idCategori = Integer.valueOf(request.getParameter("filtroCategoria"));
                        List<Producto> productosFiltrados;
                        if (idCategori == 0) {
                            productosFiltrados = prodDAO.getProductos();
                        } else {
                            productosFiltrados = prodDAO.getProductosPorCategoria(idCategori);
                        }
                        request.setAttribute("productos", productosFiltrados);
                        request.setAttribute("categorias", catDAO.getCategoria());
                        request.getRequestDispatcher("producto.jsp").forward(request, response);
                    break;                                       
                    case "Listar":
                        List<Producto> productos = prodDAO.getProductos();
                        request.setAttribute("productos", productos);
                        Producto producto = (Producto) request.getAttribute("producto");
                        request.setAttribute("producto", producto != null ? producto : new Producto());
                        request.setAttribute("categorias", catDAO.getCategoria());
                        request.getRequestDispatcher("producto.jsp").forward(request, response);
                    break;              
                    case "Guardar":
                        int idProd = Integer.valueOf(request.getParameter("txtId"));
                        String nom = request.getParameter("txtNombre");
                        String des = request.getParameter("txtDescripcion");
                        double precio = Double.valueOf(request.getParameter("txtPrecio"));
                        int idCategoria = Integer.valueOf(request.getParameter("txtCategoria"));
                        String estado = request.getParameter("txtEstado");
                        Producto p = new Producto();
                        p.setId(idProd);
                        p.setNombre(nom);
                        p.setDescripcion(des);
                        p.setPrecio(precio);
                        p.setIdcat(idCategoria);
                        p.setEstado(estado);
                        int r = prodDAO.saveProducto(p);
                        if (r == 1) {
                            request.setAttribute("Mensaje", "Error al guardar Producto");
                            request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                        } else {
                            request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                        }
                    break;
                    case "Editar":
                        int idpr = Integer.parseInt(request.getParameter("id"));
                        Producto product = prodDAO.getProductoId(idpr);
                        request.setAttribute("producto", product);
                        request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                    case "Delete":
                        int ide = Integer.parseInt(request.getParameter("id"));
                        int res = prodDAO.deleteProductoId(ide);
                        if (res == 1) {
                            request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                        } else {
                            request.setAttribute("Mensaje", "Error o No se puede Eliminar Producto");
                            request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                        }
                    break; 
                    default:
                        throw new AssertionError();
                }
            }
                        
            if (menu.equals("Categoria")){
                switch (accion) {
                    case "Listar":
                        List<Categoria> categorias = catDAO.getCategoria();
                        request.setAttribute("Categorias", categorias);
                        Categoria cat = (Categoria) request.getAttribute("categoria");
                        request.setAttribute("categoria", cat != null ? cat : new Categoria());
                        request.setAttribute("categorias", categorias);
                        request.getRequestDispatcher("categoria.jsp").forward(request, response);
                    break;
                    case "Guardar":
                        String idca = request.getParameter("txtId");
                        String nom = request.getParameter("txtNombre");
                        String est = request.getParameter("txtEstado");
                        Categoria cate = new Categoria(Integer.valueOf(idca), nom, est);
                        int res1 = catDAO.saveCategoria(cate);
                        if (res1 == 1) {
                            request.getRequestDispatcher("Controlador?menu=Categoria&accion=Listar").forward(request, response);
                        } else {
                            request.setAttribute("menu", "Categoria");
                            request.setAttribute("accion", "Listar");
                            request.setAttribute("Mensaje", "Error al guardar Categoría");
                            request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                        }
                    break;
                    case "Editar":
                        int idpc = Integer.parseInt(request.getParameter("id"));
                        Categoria cl = catDAO.getCategoriaId(idpc);
                        request.setAttribute("categoria", cl);
                        request.getRequestDispatcher("Controlador?menu=Categoria&accion=Listar").forward(request, response);
                        break;
                    case "Delete":
                        String id_cat = request.getParameter("iddel");
                        int ress = catDAO.deleteCategoriaId(Integer.valueOf(id_cat));
                        if (ress == 1) {
                            request.getRequestDispatcher("Controlador?menu=Categoria&accion=Listar").forward(request, response);
                        } else {
                            request.setAttribute("Mensaje", "Error o No se puede Eliminar Categoría");
                            request.setAttribute("menu", "Categoria");
                            request.setAttribute("accion", "Listar");
                            request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                        }
                    break;                        
                    default:
                        throw new AssertionError();
                }
            }
                  
            if (menu.equals("Empleado")) {
                 switch (accion) {
                     case "Listar":
                         List<Empleado> empleados = empDAO.getEmpleados();
                         request.setAttribute("empleados", empleados);
                         Empleado empleado = (Empleado) request.getAttribute("empleado");
                         request.setAttribute("empleado", empleado != null ? empleado : new Empleado());
                         request.getRequestDispatcher("empleado.jsp").forward(request, response);
                    break;
                     case "Guardar":
                         int idEmp = Integer.valueOf(request.getParameter("txtId"));
                         String dni = request.getParameter("txtDni");
                         String nombres = request.getParameter("txtNombres");
                         String correo = request.getParameter("txtCorreo");
                         String user = request.getParameter("txtUser");
                         String contra = request.getParameter("txtContra");
                         String perfil = request.getParameter("txtPerfil");

                         int idUs = Integer.valueOf(request.getParameter("txtIdu"));
                         Empleado emp = new Empleado();
                         emp.setId(idEmp);
                         emp.setDni(dni);
                         emp.setNombres(nombres);
                         emp.setCorreo(correo);
                         emp.setUser(user);
                         emp.setPassword(contra);
                         emp.setPerfil(perfil);

                         emp.setIdu(idUs);
                         int r = empDAO.saveEmpleado(emp);
                         if (r == 1) {
                             request.setAttribute("Mensaje", "Error al guardar Empleado");
                             request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                         } else {
                             request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                         }
                     break;
                     case "Editar":
                         int idpr = Integer.parseInt(request.getParameter("id"));
                         Empleado empEdit = empDAO.getEmpleadoId(idpr);
                         request.setAttribute("empleado", empEdit);
                         request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                     break;
                     case "Delete":
                         int ide = Integer.parseInt(request.getParameter("id"));
                         int res = empDAO.deleteEmpleadoId(ide);
                         if (res == 1) {
                             request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                         } else {
                             request.setAttribute("Mensaje", "Error o No se puede Eliminar Empleado");
                             request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                         }
                     break;
                     case "CompletarDatos":
                         String userE = request.getParameter("txtUser");
                         String passwordE = request.getParameter("txtContra");
                         String perfilE = request.getParameter("txtPerfil");
                         int iduE = Integer.parseInt(request.getParameter("txtIdu"));

                         Empleado emplead = new Empleado();
                         emplead.setUser(userE);
                         emplead.setPassword(passwordE);
                         emplead.setPerfil(perfilE);
                         emplead.setIdu(iduE);

                         request.setAttribute("empleado", emplead);

                          // Listar todos los empleados para la tabla
                         List<Empleado> empleas = empDAO.getEmpleados();
                         request.setAttribute("empleados", empleas);

                         request.getRequestDispatcher("empleado.jsp").forward(request, response);
                     break;
                     default:
                         throw new AssertionError();
                 }
             } 
             
            if (menu.equals("Proveedor")) {
                switch (accion) {
                    case "Listar":
                        List<Proveedor> proveedores = provDAO.getProveedores();
                        request.setAttribute("proveedores", proveedores);
                        Proveedor proveedor = (Proveedor) request.getAttribute("proveedor");
                        request.setAttribute("proveedor", proveedor != null ? proveedor : new Proveedor());
                        request.getRequestDispatcher("proveedor.jsp").forward(request, response);
                    break;
                    case "Guardar":
                        int idProv = Integer.valueOf(request.getParameter("txtId"));
                        String dni = request.getParameter("txtDni");
                        String nombres = request.getParameter("txtNombres");
                        String correo = request.getParameter("txtCorreo");
                        double pago = Double.parseDouble(request.getParameter("txtPago"));
                        Proveedor prov = new Proveedor();
                        prov.setId(idProv);
                        prov.setDni(dni);
                        prov.setNombres(nombres);
                        prov.setCorreo(correo);
                        prov.setPago(pago);
                        int r = provDAO.saveProveedor(prov);
                        if (r == 1) {
                            request.setAttribute("Mensaje", "Error al guardar Proveedor");
                            request.getRequestDispatcher("Controlador?menu=Proveedor&accion=Listar").forward(request, response);
                        } else {
                            request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                        }
                    break;
                    case "Editar":
                        int idpr = Integer.parseInt(request.getParameter("id"));
                        Proveedor provEdit = provDAO.getProveedorById(idpr);
                        request.setAttribute("proveedor", provEdit);
                        request.getRequestDispatcher("Controlador?menu=Proveedor&accion=Listar").forward(request, response);
                    break;
                    case "Delete":
                        int ide = Integer.parseInt(request.getParameter("id"));
                        int res = provDAO.deleteProveedorById(ide);
                        if (res == 1) {
                            request.getRequestDispatcher("Controlador?menu=Proveedor&accion=Listar").forward(request, response);
                        } else {
                            request.setAttribute("Mensaje", "Error o No se puede Eliminar Proveedor");
                            request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                        }
                    break;
                    default:
                        throw new AssertionError();
                }
            }
       
            if (menu.equals("Usuario")) {
                switch (accion) {
                    case "Listar":
                        List<Usuario> usuarios = userDAO.getUsuarios();
                        request.setAttribute("usuarios", usuarios);
                        Usuario usuario = (Usuario) request.getAttribute("usuario");
                        request.setAttribute("usuario", usuario != null ? usuario : new Usuario());
                        request.getRequestDispatcher("usuario.jsp").forward(request, response);
                    break;
                    case "Guardar":
                        int idUser = Integer.valueOf(request.getParameter("txtId"));
                        String user = request.getParameter("txtUser");
                        String password = request.getParameter("txtPassword");
                        String perfil = request.getParameter("txtPerfil");
                        Usuario u = new Usuario();
                        u.setIdu(idUser);
                        u.setUser(user);
                        u.setPassword(password);
                        u.setPerfil(perfil);
                        int r = userDAO.saveUsuario(u);
                        if (r == 1) {
                            request.setAttribute("Mensaje", "Error al guardar Usuario");
                            request.getRequestDispatcher("Controlador?menu=Usuario&accion=Listar").forward(request, response);
                        } else {
                            request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                        }
                    break;
                    case "Editar":
                        int idu = Integer.parseInt(request.getParameter("id"));
                        Usuario userEdit = userDAO.getUsuarioById(idu);
                        request.setAttribute("usuario", userEdit);
                        request.getRequestDispatcher("Controlador?menu=Usuario&accion=Listar").forward(request, response);
                        break;
                    case "Delete":
                        int idDel = Integer.parseInt(request.getParameter("id"));
                        int res = userDAO.deleteUsuarioById(idDel);
                        if (res == 1) {
                            request.getRequestDispatcher("Controlador?menu=Usuario&accion=Listar").forward(request, response);
                        } else {
                            request.setAttribute("Mensaje", "Error o No se puede Eliminar Usuario");
                            request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                        }
                    break;
                    default:
                        throw new AssertionError();
                    }
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
        return "Short description";
    }

}
