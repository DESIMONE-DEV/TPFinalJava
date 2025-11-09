package Modelo.Gestores;

import Exceptions.ColleccionVaciaException;
import Exceptions.CuentaExistenteException;

import java.util.HashSet;
import java.util.Iterator;

public class GestorGenerico <T>{
    HashSet<T> conjunto;

    /// --------------------- CONSTRUCTORES -----------------------------

    public GestorGenerico(){
        conjunto = new HashSet<>();
    }

    /// --------------------- FIN CONSTRUCTORES --------------------------

    /// ----------------- GETTERS AND SETTERS ----------------------------
    public HashSet<T> getConjunto() {
        return conjunto;
    }

    public void setConjunto(HashSet<T> conjunto) {
        this.conjunto = conjunto;
    }
    /// ---------------- FIN GETTERS AND SETTERS -------------------------

    /// -------------------- METODOS -------------------------------------

    public boolean agregar(T nuevoDato)throws CuentaExistenteException {
        if (conjunto.add(nuevoDato)){
            return true;
        }throw new CuentaExistenteException("La cuenta ya existe en el sistema");
    }

    public boolean eliminar(T dato){
        return conjunto.remove(dato);
    }

    public boolean existe(T dato){
        return conjunto.contains(dato);
    }

    /**
     *  METODO PARA RECIBIR UN OBJETO CON SOLO SU ATRIBUTO UTILIZADO EN EL EQUALS Y
        RETORNAR EL OBJETO DENTRO DEL CONJUNTO CON TODOS SUS ATRIBUTOS CORRESPONDIENTES A LO QUE SE BUSCA
     *
     * @param datoRecibido ingresar un NEW dato(atributo utilizado en el equals)
     * @return retorna el Objeto dentro del gestor
     */
    public T getDato(T datoRecibido) throws ColleccionVaciaException{
        if(existe(datoRecibido)){
            T retorno;
            Iterator it = conjunto.iterator();
            while(it.hasNext()){
                retorno = (T) it.next();
                if(retorno.equals(datoRecibido)){
                    return retorno;
                }
            }
        }else if(conjunto.isEmpty()){   // Si no existe el dato compruebo si la colleccion esta vacia para lanzar la excepcion
            throw new ColleccionVaciaException();
        }
        return null;
    }

    public String listar() throws ColleccionVaciaException {
        StringBuilder msj = new StringBuilder();
        if(conjunto.isEmpty()){
            throw new ColleccionVaciaException();
        }else {
            Iterator it = conjunto.iterator();
            while (it.hasNext()) {
                msj.append("-" + it.next() + " \n");
            }
            return msj.toString();
        }
    }
}
