
package models;

public class empleado extends usuario{
    //Atributos
    private String address;
    private String rol;
    //Constructor sin parametros
    public empleado(){
        
    }
    //Constructor con parametros
    public empleado(String address, String rol, int id, String full_name, String username, String password, String telephone, String email, String created, String updated) {
        super(id, full_name, username, password, telephone, email, created, updated);
        this.address = address;
        this.rol = rol;
    }
    //Metodos get-set
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    
    
}

