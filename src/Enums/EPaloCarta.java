package Enums;
public enum EPaloCarta {
    CORAZONES("Corazones"),
    PICAS("Picas"),
    TREBOL("Trebol"),
    DIAMANTES("Diamantes");

    private String palo;

    EPaloCarta(String palo) {
        this.palo = palo;
    }
    public String getPalo() {
        return palo;
    }
}

