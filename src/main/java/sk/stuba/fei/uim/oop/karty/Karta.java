package sk.stuba.fei.uim.oop.karty;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.stol.Stol;

public abstract class Karta {
    protected String meno;
    protected Stol stol;

    public Karta(String meno, Stol stol) {
        this.meno = meno;
        this.stol = stol;
    }
    public String getMeno() {
        return meno;
    }
    public abstract boolean mozeHrat();
    public void zahrajKartu(Hrac hrac, Hrac[] hraci) {
        System.out.println("\n=== " + hrac.getMeno() + " zahral kartu " + this.meno + ". ===");
    }
}