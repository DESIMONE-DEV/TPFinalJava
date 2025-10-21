package Modelo.Usuarios;

import java.util.UUID;

public class Cliente extends Usuario {
    private Double saldo;
    private String cuentaBancaria;
    private Boolean estadoCuenta;


    /// ------------------------Constructores------------------///
    public Cliente(String nombre, Long dni, String cuentaBancaria) {
        super(nombre, dni);
        this.saldo = 0.00;
        this.cuentaBancaria = cuentaBancaria;
        this.estadoCuenta = true;
    }

    /// -----------------------FIN DE CONSTRUCTORES-----------------------///

    /// ----------------------GETTERS AND SETTERS ------------------------///

    public Double getSaldo() {
        return saldo;
    }

    public String getcuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(String cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    /// ---------------------------FIN DE GETTERS AND SETTERS-----------------///

    /// -----------------------METODOS DE CLIENTES-------------///

    public void cargarSaldo(Double monto) {
        saldo += monto;
    }

    public String retirarSaldo(Double monto) {
        return "Saldo retirado correctamente: " + monto;
    }
    /// ----------------------FIN METODOS CLIENTES -----------------------///



}

