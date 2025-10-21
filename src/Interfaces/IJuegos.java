package Interfaces;

import Modelo.Usuarios.Cliente;

public interface IJuegos {
    boolean pagarFichas(Cliente jugador);
    void jugar(Cliente jugador);
}
