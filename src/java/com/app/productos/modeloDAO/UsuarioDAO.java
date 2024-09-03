package com.app.productos.modeloDAO;

import com.app.productos.config.ConectarBD;
import com.app.productos.interfaces.IUsuario;
import com.app.productos.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UsuarioDAO implements IUsuario {

    private static final Logger LOG = Logger.getGlobal();

    @Override
    public Usuario getUsuarioById(int id) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE i_num_id_user=?";
        try {
            Connection con = ConectarBD.Conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdu(rs.getInt("i_num_id_user"));
                usuario.setUser(rs.getString("c_user"));
                usuario.setPassword(rs.getString("c_password"));
                usuario.setPerfil(rs.getString("c_perfil"));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            LOG.info("Error getUsuarioById: ".concat(e.toString()));
        }
        return usuario;
    }

    @Override
    public List<Usuario> getUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try {
            Connection con = ConectarBD.Conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdu(rs.getInt("i_num_id_user"));
                usuario.setUser(rs.getString("c_user"));
                usuario.setPassword(rs.getString("c_password"));
                usuario.setPerfil(rs.getString("c_perfil"));
                usuarios.add(usuario);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            LOG.info("Error getUsuarios: ".concat(e.toString()));
        }
        return usuarios;
    }

    @Override
    public int saveUsuario(Usuario usuario) {
        int r = 0;
        String sql;
        try {
            Connection con = ConectarBD.Conectar();
            PreparedStatement ps;
            if (usuario.getIdu() == 0) {
                sql = "INSERT INTO usuario(c_user, c_password, c_perfil) VALUES (?, ?, ?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, usuario.getUser());
                ps.setString(2, usuario.getPassword());
                ps.setString(3, usuario.getPerfil());
                r = ps.executeUpdate();
                ps.close();
            } else {
                sql = "UPDATE usuario SET c_user=?, c_password=?, c_perfil=? WHERE i_num_id_user=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, usuario.getUser());
                ps.setString(2, usuario.getPassword());
                ps.setString(3, usuario.getPerfil());
                ps.setInt(4, usuario.getIdu());
                r = ps.executeUpdate();
                ps.close();
            }
            con.close();
        } catch (SQLException e) {
            LOG.info("Error saveUsuario: ".concat(e.toString()));
        }
        return r;
    }

    @Override
    public int deleteUsuarioById(int id) {
        int r = 0;
        String sql = "DELETE FROM usuario WHERE i_num_id_user=?";
        try {
            Connection con = ConectarBD.Conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            r = ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {
            LOG.info("Error deleteUsuarioById: ".concat(e.toString()));
        }
        return r;
    }

    @Override
    public int contarUsuarios() {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM usuario";
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
            LOG.info("Error contarUsuarios: ".concat(e.toString()));
        }
        return total;
    }
}
