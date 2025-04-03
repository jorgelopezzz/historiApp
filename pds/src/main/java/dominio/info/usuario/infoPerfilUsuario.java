package dominio.info.usuario;

import java.util.Optional;

import dominio.info.Info;

public class infoPerfilUsuario extends Info{

    private String nombre;
    private String correo;
    private String saludo;
    private String rol;
    private String imagen;


    public infoPerfilUsuario (String texto, String nombre, String correo, String saludo, String rol, String imagen){
        super(texto);
        this.nombre = nombre;
        this.correo = correo;
        this.saludo = saludo;
        this.rol = rol;
        this.imagen = imagen;
    }

    /* Getters */

    public String getNombre (){
        return nombre;
    }

    public String getCorreo (){
        return correo;
    }

    public String getSaludo (){
        return saludo;
    }

    public String getRol (){
        return rol;
    }

    public String getImagen (){
        return imagen;
    }

}
