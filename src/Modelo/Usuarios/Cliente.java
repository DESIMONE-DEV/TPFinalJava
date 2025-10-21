package Modelo.Usuarios;

import java.util.UUID;

public class Cliente extends Usuario {
    private Double saldo;
    private String cuentaBancaria;
    private Boolean estadoCuenta;


    /// ------------------------Constructores------------------///
    public Cliente(String nombre, Long dni,String password, String cuentaBancaria) {
        super(nombre, dni,password);
        this.saldo = 0.00;
        this.cuentaBancaria = cuentaBancaria;
        this.estadoCuenta = true;
    }

    public Cliente(Long dni){
        super(dni);
    }

    /// -----------------------FIN DE CONSTRUCTORES-----------------------///

    /// ----------------------GETTERS AND SETTERS ------------------------///

    public Double getSaldo() {
        return saldo;
    }

    public String getcuentaBancaria() {
        return cuentaBancaria;
    }

    public Boolean getEstadoCuenta() {
        return estadoCuenta;
    }

    public String getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public void setEstadoCuenta(Boolean estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }

    /// ---------------------------FIN DE GETTERS AND SETTERS-----------------///

    /// -----------------------METODOS DE CLIENTES-------------///

    public Boolean cargarSaldo(Double monto) { ///(AGREGAR EXCEPCIONES VARIAS)
        saldo += monto;
        return true;
    }

    public Boolean retirarSaldo(Double monto) {  ///(AGREGAR EXCEPCIONES VARIAS)

        saldo -= monto;
        return true;
    }

    public boolean cambioBancario(String nuevaBancaria) { ///(AGREGAR EXCEPCIONES VARIAS)
       return true;
    }
    /// ----------------------FIN METODOS CLIENTES -----------------------///



}

