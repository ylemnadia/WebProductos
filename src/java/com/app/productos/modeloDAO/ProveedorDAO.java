
package com.app.productos.modeloDAO;

import com.app.productos.config.ConectarBD;
import com.app.productos.interfaces.IProveedor;
import com.app.productos.modelo.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ProveedorDAO implements IProveedor {

    private static final Logger LOG = Logger.getGlobal();

    @Override
    public Proveedor getProveedorById(int id) {
        Proveedor proveedor = null;
        String sql = "SELECT * FROM proveedor WHERE i_num_id_pro=?";
        try {
            Connection con = ConectarBD.Conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                proveedor = new Proveedor();
                proveedor.setId(rs.getInt("i_num_id_pro"));
                proveedor.setDni(rs.getString("c_dni"));
                proveedor.setNombres(rs.getString("c_nombres"));
                proveedor.setCorreo(rs.getString("c_correo"));
                proveedor.setPago(rs.getDouble("c_pago"));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            LOG.info("Error getProveedorById: ".concat(e.toString()));
        }
        return proveedor;
    }

    @Override
    public List<Proveedor> getProveedores() {
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM proveedor";
        try {
            Connection con = ConectarBD.Conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setId(rs.getInt("i_num_id_pro"));
                proveedor.setDni(rs.getString("c_dni"));
                proveedor.setNombres(rs.getString("c_nombres"));
                proveedor.setCorreo(rs.getString("c_correo"));
                proveedor.setPago(rs.getDouble("c_pago"));
                proveedores.add(proveedor);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            LOG.info("Error getProveedores: ".concat(e.toString()));
        }
        return proveedores;
    }

    @Override
    public int saveProveedor(Proveedor proveedor) {
        int r = 0;
        String sql;
        try {
            Connection con = ConectarBD.Conectar();
            PreparedStatement ps;
            if (proveedor.getId() == 0) {
                sql = "INSERT INTO proveedor(c_dni, c_nombres, c_correo, c_pago) VALUES (?, ?, ?, ?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, proveedor.getDni());
                ps.setString(2, proveedor.getNombres());
                ps.setString(3, proveedor.getCorreo());
                ps.setDouble(4, proveedor.getPago());
                r = ps.executeUpdate();
                ps.close();
            } else {
                sql = "UPDATE proveedor SET c_dni=?, c_nombres=?, c_correo=?, c_pago=? WHERE i_num_id_pro=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, proveedor.getDni());
                ps.setString(2, proveedor.getNombres());
                ps.setString(3, proveedor.getCorreo());
                ps.setDouble(4, proveedor.getPago());
                ps.setInt(5, proveedor.getId());
                r = ps.executeUpdate();
                ps.close();
            }
            con.close();
        } catch (SQLException e) {
            LOG.info("Error saveProveedor: ".concat(e.toString()));
        }
        return r;
    }

    @Override
    public int deleteProveedorById(int id) {
        int r = 0;
        String sql = "DELETE FROM proveedor WHERE i_num_id_pro=?";
        try {
            Connection con = ConectarBD.Conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            r = ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {
            LOG.info("Error deleteProveedorById: ".concat(e.toString()));
        }
        return r;
    }

    @Override
    public int contarProveedores() {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM proveedor";
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
            LOG.info("Error contarProveedores: ".concat(e.toString()));
        }
        return total;
    }
}