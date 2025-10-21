package Enums;

public enum ENumerosRuleta {
    N0 (0,"verde"),
    N1(1,"rojo"),
    N2(2,"negro"),
    N3(3,"rojo"),
    N4(4,"negro"),
    N5(5,"rojo"),
    N6(6,"negro"),
    N7(7,"rojo"),
    N8(8,"negro"),
    N9(9,"rojo"),
    N10(10,"negro"),
    N11(11,"negro"),
    N12(12,"rojo"),
    N13(13,"negro"),
    N14(14,"rojo"),
    N15(15,"negro"),
    N16(16,"rojo"),
    N17(17,"negro"),
    N18(18,"rojo"),
    N19(19,"rojo"),
    N20(20,"negro"),
    N21(21,"rojo"),
    N22(22,"negro"),
    N23(23,"rojo"),
    N24(24,"negro"),
    N25(25,"rojo"),
    N26(26,"negro"),
    N27(27,"rojo"),
    N28(28,"negro"),
    N29(29,"negro"),
    N30(30,"rojo"),
    N31(31,"negro"),
    N32(32,"rojo"),
    N33(33,"negro"),
    N34(34,"rojo"),
    N35(35,"negro"),
    N36(36,"rojo");

    private int numero;
    private String color;

    ENumerosRuleta(int  numero, String color) {
        this.numero = numero;
        this.color = color ;
    }
    public int getNumero(int numeroSalidor) {
        return numero;
    }
    public String getColor() {
        return color ;
    }

    
}
