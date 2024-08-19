package www.udb.edu.sv.gestionempleados.model;

import java.sql.Date;

public class Empleado {
    private int idEmpleado;
    private String numeroDUI;
    private String nombrePersona;
    private String usuario;
    private String numeroTelefono;
    private String correoInstitucional;
    private Date fechaNacimiento;

    public Empleado() {
    }

    public Empleado(int idEmpleado, String numeroDUI, String nombrePersona, String usuario, String numeroTelefono, String correoInstitucional, Date fechaNacimiento) {
        this.idEmpleado = idEmpleado;
        this.numeroDUI = numeroDUI;
        this.nombrePersona = nombrePersona;
        this.usuario = usuario;
        this.numeroTelefono = numeroTelefono;
        this.correoInstitucional = correoInstitucional;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Empleado(String numeroDUI, String nombrePersona, String usuario, String numeroTelefono, String correoInstitucional, Date fechaNacimiento) {
        this.numeroDUI = numeroDUI;
        this.nombrePersona = nombrePersona;
        this.usuario = usuario;
        this.numeroTelefono = numeroTelefono;
        this.correoInstitucional = correoInstitucional;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNumeroDUI() {
        return numeroDUI;
    }

    public void setNumeroDUI(String numeroDUI) {
        this.numeroDUI = numeroDUI;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
