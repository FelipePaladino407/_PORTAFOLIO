// ParNegro.java
package org.example;

public class ParNegro {
    private final int sueroId, farmacoId;
    public ParNegro(int sueroId, int farmacoId) {
        this.sueroId = sueroId;
        this.farmacoId = farmacoId;
    }
    public int getSueroId()  { return sueroId; }
    public int getFarmacoId(){ return farmacoId; }
}
