
package com.app.productos.interfaces;

import com.app.productos.modelo.Usuario;
import java.util.List;

public interface IUsuario {
    public List<Usuario> getUsuarios();
    public Usuario getUsuarioById(int id);
    public int saveUsuario(Usuario usuario);
    public int deleteUsuarioById(int id);
    public int contarUsuarios();
}
