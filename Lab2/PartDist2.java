public class PartDist2 {
    int[][] dynProgMat;
    int cost;

    String lastW2;
    int lastW2Len;

    public PartDist2(String w) {
        int wLen = w.length();
        dynProgMat = new int[wLen + 1][90]; // Längsta ordet i SAOL är 28 bokstäver.
                                            // Det längsta är 21 i ordlistan.
                                            // Kattis gav fel för 29 så körde 90 och det funkade
                                            // Skulle vara snyggt om den var dynamisk men ¯\_( ͡° ͜ʖ ͡°)_/¯.
        for (int i = 0; i <= wLen; i++) {
            dynProgMat[i][0] = i;
        }

        lastW2 = "";
        lastW2Len = 0;
    }

    int partDist2(String w1, String w2, int w1len, int w2len) {
        // Vi vet att w1 kommer vara samma för alla anrop i en instans av ClosestWords
        // Vi vet därför att antalet kolumner i matrisen är konstant för samma w1.
        // Vi kan därför återanvända delar av matrisen för w2 som är lika.
        // Dom delar som är lika är dom första "i + 1" (max maxSame) raderna.
        // Det som inte stämmer skrivs över i matrisen.

        int i, j; // Ger säkert någon liten prestandaförbättring ;)

        int maxSame = Math.min(w2len, lastW2Len);
        for (i = 0; i < maxSame; i++) {
            if (w2.charAt(i) != lastW2.charAt(i)) {
                break;
            }
        }

        // System.out.println("Words: " + w1 + " " + w2 + " same: " + same);
        // System.out.println("Lengths: " + w1len + " " + w2len);

        for (i += 1; i <= w2len; i++) { // Börja ifrån där dom skiljer sig
            dynProgMat[0][i] = i;
            for (j = 1; j <= w1len; j++) {
                if (w1.charAt(j - 1) == w2.charAt(i - 1)) {
                    cost = 0;
                } else {
                    cost = 1;
                }

                // System.out.println("i: " + i + " j: " + j + " cost: " + cost);
                dynProgMat[j][i] = Math.min(
                        Math.min(
                                dynProgMat[j - 1][i] + 1,
                                dynProgMat[j][i - 1] + 1),
                        dynProgMat[j - 1][i - 1] + cost);
            }
        }

        lastW2 = w2;
        lastW2Len = w2len;
        return dynProgMat[w1len][w2len];
    }

    public int distance(String w1, String w2) {
        // För att bevisa att den gamla rekursiva funktionen ger samma resultat som den
        // nya kommentera ut följande rader och kommentera return partDist...

        // int gg = partDist2(w1, w2, w1.length(), w2.length());
        // if (gg != partDistOriginal(w1, w2, w1.length(), w2.length())) {
        // System.out.println("Fejl!!!");
        // }
        // return gg;

        return partDist2(w1, w2, w1.length(), w2.length());
    }
}
