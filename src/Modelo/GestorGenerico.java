package Modelo;

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

    public boolean agregar(T nuevoDato){
        return conjunto.add(nuevoDato);
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
     */
    public T getDato(T datoRecibido){
        if(existe(datoRecibido)){
            T retorno;
            Iterator it = conjunto.iterator();
            while(it.hasNext()){
                retorno = (T) it.next();
                if(retorno.equals(datoRecibido)){
                    return retorno;
                }
            }
        }
        return null;
    }

    public String listar(){
        StringBuilder msj = new StringBuilder();
        Iterator it = conjunto.iterator();
        while(it.hasNext()){
            msj.append("-" + it.next() + " ");
        }
        return msj.toString();
    }
}
