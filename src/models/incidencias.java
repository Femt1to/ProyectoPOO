package models;

public class incidencias {
    //Atributos
    private int id_incidense;
    private String description;
    private String created;
    private String status;
    private int id_student;
    private String fullname_student;
    private String email;
    private String carrera_student;
    private int id_employee;
    private String fullname_employee;
    //Constructor
    public incidencias(){
        
    }

    public incidencias(int id_incidense, String description, String created, String status, int id_student, String fullname_student, String email, String carrera_student, int id_employee, String fullname_employee) {
        this.id_incidense = id_incidense;
        this.description = description;
        this.created = created;
        this.status = status;
        this.id_student = id_student;
        this.fullname_student = fullname_student;
        this.email = email;
        this.carrera_student = carrera_student;
        this.id_employee = id_employee;
        this.fullname_employee = fullname_employee;
    }

    public int getId_incidense() {
        return id_incidense;
    }

    public void setId_incidense(int id_incidense) {
        this.id_incidense = id_incidense;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId_student() {
        return id_student;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }

    public String getFullname_student() {
        return fullname_student;
    }

    public void setFullname_student(String fullname_student) {
        this.fullname_student = fullname_student;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCarrera_student() {
        return carrera_student;
    }

    public void setCarrera_student(String carrera_student) {
        this.carrera_student = carrera_student;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public String getFullname_employee() {
        return fullname_employee;
    }

    public void setFullname_employee(String fullname_employee) {
        this.fullname_employee = fullname_employee;
    }

    
}
