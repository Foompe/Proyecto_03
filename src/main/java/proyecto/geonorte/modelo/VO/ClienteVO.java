/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.geonorte.modelo.VO;

/**
 *
 * @author FP DAM
 */
public class ClienteVO {
    
    private String cod_cliente;
    private String nif;
    private String razon_social;
    private String calle;
    private int numero;
    private String localidad;
    private int cod_postal;
    private int telefono;
    private String tipo_empresa;

    
    
    public ClienteVO(String pos, String id, String nif, String razon_social, String calle, int numero, String localidad, int cod_postal, int telefono, String tipo_empresa) {
        this.cod_cliente = pos.concat(id);
        this.nif = nif;
        this.razon_social = razon_social;
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.cod_postal = cod_postal;
        this.telefono = telefono;
        this.tipo_empresa = tipo_empresa;
    }

    public String getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(String cod_cliente) {
        this.cod_cliente = cod_cliente;
    }
    
    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public int getCod_postal() {
        return cod_postal;
    }

    public void setCod_postal(int cod_postal) {
        this.cod_postal = cod_postal;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getTipo_empresa() {
        return tipo_empresa;
    }

    public void setTipo_empresa(String tipo_empresa) {
        this.tipo_empresa = tipo_empresa;
    }   
    
    public String toString() {
    return String.format("%-8s  |  %-9s  |  %-20s  |  %-10s", cod_cliente, nif, razon_social,tipo_empresa); //esto es lo que mostraremos en el Jlist
}
    
}