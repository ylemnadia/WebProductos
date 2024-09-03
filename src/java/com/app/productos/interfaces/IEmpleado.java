
package com.app.productos.interfaces;
import com.app.productos.modelo.Empleado;
import java.util.List;


public interface IEmpleado {
    public Empleado getEmpleadoUserPass(String user,String pass);
    
    
    public List<Empleado> getEmpleados();
    public Empleado getEmpleadoId(int id);
    public int saveEmpleado(Empleado emp);
    public int deleteEmpleadoId(int id);
    public int contarEmpleados();
}
