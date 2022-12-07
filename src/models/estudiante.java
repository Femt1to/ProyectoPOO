package models;

public class estudiante extends usuario {

    //Atributos
    private String carrera_universitaria;
    private String rol;

    //Constructor sin parametros
    public estudiante() {

    }

    //Constructor con parametros
    public estudiante(String carrera_universitaria, String rol, int id, String full_name, String username, String password, String telephone, String email, String created, String updated) {
        super(id, full_name, username, password, telephone, email, created, updated);
        this.carrera_universitaria = carrera_universitaria;
        this.rol = rol;
    }

    //Metodos get-set
    public String getCarrera_universitaria() {
        return carrera_universitaria;
    }

    public void setCarrera_universitaria(String carrera_universitaria) {
        this.carrera_universitaria = carrera_universitaria;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
