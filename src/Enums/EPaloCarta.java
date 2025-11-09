package Enums;
public enum EPaloCarta {
    CORAZONES("♥"),
    PICAS("♠"),
    TREBOL("♣"),
    DIAMANTES("♦");

    private String palo;

    EPaloCarta(String palo) {
        this.palo = palo;
    }
    public String getPalo() {
        return palo;
    }
}

