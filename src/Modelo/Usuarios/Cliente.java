package Modelo.Usuarios;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

public class Cliente extends Usuario {
    private Double saldo;
    private String cuentaBancaria;
    private Boolean estadoCuenta;


    /// ------------------------Constructores------------------///
    public Cliente(String nombre, int dni,String password, String cuentaBancaria) {
        super(nombre, dni,password);
        this.saldo = 0.00;
        this.cuentaBancaria = cuentaBancaria;
        this.estadoCuenta = true;
    }

    public Cliente(int dni, String password) {
        super(dni, password);
    }

    public Cliente(int dni){
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

    public void setCuentaBancaria(String cuentaBancaria) { this.cuentaBancaria = cuentaBancaria; }

    public void setEstadoCuenta(Boolean estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }

    /// ---------------------------FIN DE GETTERS AND SETTERS-----------------///

    /// -----------------------METODOS DE CLIENTES-------------///

    public Boolean cargarSaldo(Double monto) {
        saldo += monto;
        return true;
    }

    public Boolean retirarSaldo(Double monto) {

        saldo -= monto;
        return true;
    }

    public boolean cambioBancario(String nuevaBancaria) {
       return true;
    }
    /// ----------------------FIN METODOS CLIENTES -----------------------///
    ///
    /// ----------------------TO JSON  -----------------------------///
    ///
    @Override
    public  JSONObject toJSON() throws JSONException {

        JSONObject objeto = new JSONObject();

        objeto.put("Tipo", "Cliente");
        objeto.put("Id",getId());
        objeto.put("Nombre",getNombre());
        objeto.put("Dni",getDni());
        objeto.put("Password",getPassword());
        objeto.put("CuentaBancaria",getCuentaBancaria());
        objeto.put("Saldo",getSaldo());
        objeto.put("EstadoCuenta",getEstadoCuenta());

        return objeto;

    }

    /// --------------------FROM JSON -------------------------------------///
    ///
    public Cliente (JSONObject objeto)  {

        super( objeto.getString("Nombre") ,
                UUID.fromString(objeto.getString("Id")),
                objeto.getInt("Dni"),
                objeto.getString("Password"));

        this.cuentaBancaria = objeto.getString("CuentaBancaria");
        this.saldo = objeto.getDouble("Saldo");
        this.estadoCuenta = objeto.getBoolean("EstadoCuenta");


    }

}

