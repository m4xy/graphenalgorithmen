package de.fhws.fiw.mis.rucksack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by maxarndt on 07.06.17.
 */
public class DynRucksack {
    int b_kapazitaet;
    List<Ware> waren;

    public DynRucksack(int b_kapazitaet, List<Ware> waren) {
        this.b_kapazitaet = b_kapazitaet;
        this.waren = waren;
    }

    public int solve() {
        List<Integer>[] werte = new ArrayList[waren.size()+1];
        for(int i = 0;i<werte.length;i++) {
            werte[i] = new ArrayList<>();
            werte[i].add(0);
        }
        for(int i = 0;i<1000;i++) {
            werte[0].add(Integer.MAX_VALUE);
        }
        int a = 0;

        do {
            a++;

            for(int j = 1;j <= waren.size();j++) {
                int prevItemVal = werte[j-1].get(a);
                int index = a - waren.get(j).getP_Nutzwert();
                int currItemVal = werte[j-1].get(index > 0 ? index : 0) + waren.get(j).getVol();;


                werte[j].add(prevItemVal < currItemVal ? prevItemVal : currItemVal);
            }
        } while(b_kapazitaet >= werte[waren.size()].get(a));

        System.out.println("Durchlaeufe: " + (a - 1));
        return 99;
    }

    public static void main(String[] args) {
        List<Ware> waren = Arrays.asList(new Ware(3, 2), new Ware(6,4), new Ware(5,3), new Ware(4,4), new Ware(5,5), new Ware(2,3));
        DynRucksack dynRucksack = new DynRucksack(20, waren);

        dynRucksack.solve();

        System.out.println("Dyn Rucksack terminated.");
    }
}
