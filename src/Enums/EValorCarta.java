package Enums;

public enum EValorCarta {
    AS(1,"A"),
    DOS(2,"2"),
    TRES(3,"3"),
    CUATRO(4,"4"),
    CINCO(5,"5"),
    SEIS(6,"6"),
    SIETE(7, "7"),
    OCHO(8,"8"),
    NUEVE(9, "9"),
    DIEZ(10,"10"),
    J(11,"J"),
    Q(12,"Q"),
    K(13,"K");

    /// Valor num referido a valor entero, para comparaciones
    /// Valor simb referido a valor simbolico, para mostrarlo

    private int valorNum;
    private String valorSimb;
    EValorCarta(int valorNum, String valorSimb) {
        this.valorNum = valorNum;
        this.valorSimb = valorSimb;
    }
    public int getValorNum() {
        return valorNum;
    }
    public String getValorSimb() {
        return valorSimb;
    }

    @Override
    public String toString() {
        return valorSimb;
    }
}
