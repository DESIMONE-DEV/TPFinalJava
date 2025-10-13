package Modelo.Usuarios;

import java.util.UUID;

public class Cliente extends Usuario {
    private Double saldo;
    private String CuentaBancaria;

    /// ------------------------Constructores------------------///
    public Cliente(String nombre, Long dni, String cuentaBancaria) {
        super(nombre, dni);
        this.saldo = 0.00;
        this.CuentaBancaria = cuentaBancaria;
    }

    /// -----------------------FIN DE CONSTRUCTORES-----------------------///

    /// ----------------------GETTERS AND SETTERS ------------------------///

    public Double getSaldo() {
        return saldo;
    }

    public String getCuentaBancaria() {
        return CuentaBancaria;
    }

    public void setCuentaBancaria(String cuentaBancaria) {
        CuentaBancaria = cuentaBancaria;
    }

    /// ---------------------------FIN DE GETTERS AND SETTERS-----------------///

}

