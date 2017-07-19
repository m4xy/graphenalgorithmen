package de.fhws.fiw.mis.rucksack;

/**
 * Created by maxarndt on 07.06.17.
 */
public class Ware {
    int p_Nutzwert;
    int vol;

    public Ware(int vol, int p_Nutzwert) {
        this.p_Nutzwert = p_Nutzwert;
        this.vol = vol;
    }

    public int getP_Nutzwert() {
        return p_Nutzwert;
    }

    public void setP_Nutzwert(int p_Nutzwert) {
        this.p_Nutzwert = p_Nutzwert;
    }

    public int getVol() {
        return vol;
    }

    public void setVol(int vol) {
        this.vol = vol;
    }
}
