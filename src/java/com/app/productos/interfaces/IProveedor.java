
package com.app.productos.interfaces;

import com.app.productos.modelo.Proveedor;
import java.util.List;

public interface IProveedor {
    public List<Proveedor> getProveedores();
    public Proveedor getProveedorById(int id);
    public int saveProveedor(Proveedor proveedor);
    public int deleteProveedorById(int id);
    public int contarProveedores();
}
