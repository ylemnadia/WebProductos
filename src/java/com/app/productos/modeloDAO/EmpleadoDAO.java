package com.app.productos.modeloDAO;

import com.app.productos.config.ConectarBD;
import com.app.productos.interfaces.IEmpleado;
import com.app.productos.modelo.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EmpleadoDAO implements IEmpleado {  
    private static final Logger LOG = Logger.getGlobal();

    @Override
    public Empleado getEmpleadoUserPass(String user, String pass) {
        Empleado empleado = null;
        String sql = "select e.i_num_id_emp,e.c_dni,e.c_nombres,e.c_correo,u.c_user,u.c_perfil "
                + "from empleado e INNER JOIN usuario u ON e.i_num_id_user=u.i_num_id_user "
                + "WHERE u.c_user=? AND u.c_password=?";
        try {
            Connection con = ConectarBD.Conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                empleado = new Empleado();
                empleado.setId(rs.getInt("e.i_num_id_emp"));
                empleado.setDni(rs.getString("e.c_dni"));
                empleado.setNombres(rs.getString("e.c_nombres"));
                empleado.setCorreo(rs.getString("e.c_correo"));
                empleado.setUser(rs.getString("u.c_user"));
                empleado.setPerfil(rs.getString("u.c_perfil"));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            LOG.info("Error getEmpleadoUserPass:".concat(e.toString()));
        }
        return empleado;
    }

    // Nuevo método para contar el total de empleados
    public int contarEmpleados() {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM empleado";
        try {
            Connection con = ConectarBD.Conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            LOG.info("Error contarEmpleados: ".concat(e.toString()));
        }
        return total;
    }

    public List<Empleado> getEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT e.i_num_id_emp, e.c_dni, e.c_nombres, e.c_correo, u.c_user, u.c_password, u.c_perfil, u.i_num_id_user \n"
		+" FROM empleado e INNER JOIN usuario u \n"
                + "ON e.i_num_id_user = u.i_num_id_user";
        try {
            Connection con = ConectarBD.Conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Empleado emp = new Empleado();
                emp.setId(rs.getInt("e.i_num_id_emp"));
                emp.setDni(rs.getString("e.c_dni"));
                emp.setNombres(rs.getString("e.c_nombres"));
                emp.setCorreo(rs.getString("e.c_correo"));
                emp.setUser(rs.getString("u.c_user"));
                emp.setPassword(rs.getString("u.c_password"));
                emp.setPerfil(rs.getString("u.c_perfil"));
                emp.setIdu(rs.getInt("u.i_num_id_user"));
                empleados.add(emp);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            LOG.info("Error getEmpleados: ".concat(e.toString()));
        }
        return empleados;
    }

    public Empleado getEmpleadoId(int id) {
        Empleado emp = null;
        String sql = "SELECT e.i_num_id_emp, e.c_dni, e.c_nombres, e.c_correo, u.c_user, u.c_password, u.c_perfil, u.i_num_id_user \n"
		+" FROM empleado e INNER JOIN usuario u \n"		
                + "ON e.i_num_id_user = u.i_num_id_user WHERE e.i_num_id_emp=? ";
        try {
            Connection con = ConectarBD.Conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                emp = new Empleado();
                emp.setId(rs.getInt("e.i_num_id_emp"));
                emp.setDni(rs.getString("e.c_dni"));
                emp.setNombres(rs.getString("e.c_nombres"));
                emp.setCorreo(rs.getString("e.c_correo"));
                emp.setUser(rs.getString("c_user"));
                emp.setPassword(rs.getString("c_password"));
                emp.setPerfil(rs.getString("c_perfil"));
                emp.setIdu(rs.getInt("u.i_num_id_user"));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            LOG.info("Error getEmpleadoId: ".concat(e.toString()));
        }
        return emp;
    }

    public int saveEmpleado(Empleado emp) {
        int r = 0;
        String sqlUsuario, sqlEmpleado;
        Connection con = null;
        PreparedStatement psUsuario = null;
        PreparedStatement psEmpleado = null;
        ResultSet rsUsuario = null;

        try {
            con = ConectarBD.Conectar();
            con.setAutoCommit(false);  // Inicia la transacción

            // Si el ID de usuario es 0, entonces es un nuevo usuario
            if (emp.getIdu() == 0) {
                // 1. Insertar en la tabla usuario
                sqlUsuario = "INSERT INTO usuario (c_user, c_password, c_perfil) VALUES (?, ?, ?)";
                psUsuario = con.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);
                psUsuario.setString(1, emp.getUser());
                psUsuario.setString(2, emp.getPassword());
                psUsuario.setString(3, emp.getPerfil());
                psUsuario.executeUpdate();

                // Obtener el i_num_id_user generado
                rsUsuario = psUsuario.getGeneratedKeys();
                if (rsUsuario.next()) {
                    emp.setIdu(rsUsuario.getInt(1));  // Asigna el ID generado al empleado
                }
                rsUsuario.close();
                psUsuario.close();
             } else {  // Actualizar usuario existente
                // 2. Actualizar en la tabla usuario
                sqlUsuario = "UPDATE usuario SET c_user=?, c_password=?, c_perfil=? WHERE i_num_id_user=?";
                psUsuario = con.prepareStatement(sqlUsuario);
                psUsuario.setString(1, emp.getUser());
                psUsuario.setString(2, emp.getPassword());
                psUsuario.setString(3, emp.getPerfil());
                psUsuario.setInt(4, emp.getIdu());
                psUsuario.executeUpdate();
                psUsuario.close();
            }

        // 1. Insertar o actualizar en la tabla empleado
        if (emp.getId() == 0) {
            // Si el ID de empleado es 0, entonces es un nuevo empleado
            sqlEmpleado = "INSERT INTO empleado (c_dni, c_nombres, c_correo, i_num_id_user) VALUES (?, ?, ?, ?)";
            psEmpleado = con.prepareStatement(sqlEmpleado);
            psEmpleado.setString(1, emp.getDni());
            psEmpleado.setString(2, emp.getNombres());
            psEmpleado.setString(3, emp.getCorreo());
            psEmpleado.setInt(4, emp.getIdu());  // Usa el i_num_id_user del usuario recién insertado
            r = psEmpleado.executeUpdate();
        } else { // Actualizar empleado existente
            // Si el ID de empleado no es 0, entonces es una actualización
            sqlEmpleado = "UPDATE empleado SET c_dni=?, c_nombres=?, c_correo=?, i_num_id_user=? WHERE i_num_id_emp=?";
            psEmpleado = con.prepareStatement(sqlEmpleado);
            psEmpleado.setString(1, emp.getDni());
            psEmpleado.setString(2, emp.getNombres());
            psEmpleado.setString(3, emp.getCorreo());
            psEmpleado.setInt(4, emp.getIdu());
            psEmpleado.setInt(5, emp.getId());
            r = psEmpleado.executeUpdate();
        }

        con.commit();  // Confirma la transacción

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (con != null) {
                    con.rollback();  // Revertir la transacción en caso de error
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            LOG.info("Error saveEmpleado: ".concat(e.toString()));
        } finally {
            try {
                if (psUsuario != null) psUsuario.close();
                if (psEmpleado != null) psEmpleado.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return r;
    }
    
    public int deleteEmpleadoId(int id) {
    int r = 0;
    String sqlDeleteEmpleado = "DELETE FROM empleado WHERE i_num_id_emp=?";
    String sqlDeleteUsuario = "DELETE FROM usuario WHERE i_num_id_user=?";
    Connection con = null;
    PreparedStatement psEmpleado = null;
    PreparedStatement psUsuario = null;
    ResultSet rs = null;

        try {
            con = ConectarBD.Conectar();
            con.setAutoCommit(false);  // Inicia la transacción

            // Primero, obtenemos el i_num_id_user relacionado con el empleado
            String sqlGetUserId = "SELECT i_num_id_user FROM empleado WHERE i_num_id_emp=?";
            psEmpleado = con.prepareStatement(sqlGetUserId);
            psEmpleado.setInt(1, id);
            rs = psEmpleado.executeQuery();
            int userId = 0;
            if (rs.next()) {
                userId = rs.getInt("i_num_id_user");
            }

            rs.close();
            psEmpleado.close();

            // Segundo, eliminamos el registro en la tabla empleado
            psEmpleado = con.prepareStatement(sqlDeleteEmpleado);
            psEmpleado.setInt(1, id);
            r = psEmpleado.executeUpdate();

            // Tercero, eliminamos el registro en la tabla usuario
            if (userId != 0) {
                psUsuario = con.prepareStatement(sqlDeleteUsuario);
                psUsuario.setInt(1, userId);
                psUsuario.executeUpdate();
            }

            con.commit();  // Confirma la transacción

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (con != null) {
                    con.rollback();  // Revertir la transacción en caso de error
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            LOG.info("Error deleteEmpleadoId: ".concat(e.toString()));
        } finally {
            try {
                if (psEmpleado != null) psEmpleado.close();
                if (psUsuario != null) psUsuario.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return r;
    }    
}