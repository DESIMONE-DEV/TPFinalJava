package Interfaces;

import Modelo.Usuarios.Cliente;

public interface IJuegos {
    boolean pagarFichas(Cliente jugador, int cantFichas);
    void jugar(Cliente jugador);
}
