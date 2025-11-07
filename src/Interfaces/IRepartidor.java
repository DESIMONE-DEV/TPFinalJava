package Interfaces;

import Exceptions.MazoVacioException;
import Modelo.Juegos.MazoCartas.Carta;

import java.util.List;

public interface IRepartidor {
    void repartir() throws MazoVacioException;
}