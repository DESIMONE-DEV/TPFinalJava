package Enums;

public enum EValorCarta {
    AS(14,"A", 11),
    DOS(2,"2", 2),
    TRES(3,"3", 3),
    CUATRO(4,"4", 4),
    CINCO(5,"5",5),
    SEIS(6,"6", 6),
    SIETE(7, "7", 7),
    OCHO(8,"8", 8),
    NUEVE(9, "9", 9),
    DIEZ(10,"10", 10),
    J(11,"J", 10),
    Q(12,"Q", 10),
    K(13,"K", 10);

    /// Valor num referido a valor entero, para comparaciones
    /// Valor simb referido a valor simbolico, para mostrarlo

    private int valorNum;
    private int valorBlackJack;
    private String valorSimb;
    EValorCarta(int valorNum, String valorSimb, int valorBlackJack) {
        this.valorNum = valorNum;
        this.valorSimb = valorSimb;
        this.valorBlackJack = valorBlackJack;
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
