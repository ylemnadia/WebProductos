package com.app.productos.modelo;

public class Usuario {
    private int idu;
    private String user;
    private String password;
    private String perfil;

    public Usuario() {
    }

    public Usuario(int idu, String user, String password, String perfil) {
        this.idu = idu;
        this.user = user;
        this.password = password;
        this.perfil = perfil;
    }

    public Usuario(String user, String perfil) {
        this.user = user;
        this.perfil = perfil;
    }

    
    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
    
    
}
