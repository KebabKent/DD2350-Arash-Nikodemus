public class PartDist1 {

    int[][] dynProgMat;
    int cost;

    public PartDist1() {
    }

    public int partDist1(String w1, String w2, int w1len, int w2len) {
        dynProgMat = new int[w1len + 1][w2len + 1]; // +1 för att få med tomma strängen

        for (int i = 0; i <= w1len; i++) {
            dynProgMat[i][0] = i;
        }

        // System.out.println("Words: " + w1 + " " + w2);
        // System.out.println("Lengths: " + w1len + " " + w2len);

        for (int i = 1; i <= w2len; i++) {
            dynProgMat[0][i] = i; // istället för som w1. Casch fetchas färre gånger
            for (int j = 1; j <= w1len; j++) { // Kör igenom matrisen rad för rad
                if (w1.charAt(j - 1) == w2.charAt(i - 1)) {
                    cost = 0;
                } else {
                    cost = 1;
                }

                // System.out.println("i: " + i + " j: " + j + " cost: " + cost);
                dynProgMat[j][i] = Math.min(
                        Math.min(
                                dynProgMat[j - 1][i] + 1, // Ta bort
                                dynProgMat[j][i - 1] + 1 // Lägg till
                        ),
                        dynProgMat[j - 1][i - 1] + cost // Byt ut
                );
            }
        }

        return dynProgMat[w1len][w2len];
    }

    public int distance(String w1, String w2) {
        // För att bevisa att den gamla rekursiva funktionen ger samma resultat som den
        // nya kommentera ut följande rader och kommentera return partDist...

        // int gg = partDist1(w1, w2, w1.length(), w2.length());
        // if (gg != PartDistOrg.partDistOriginal(w1, w2, w1.length(), w2.length())) {
        // System.out.println("Fejl!!!");
        // }
        // return gg;

        return partDist1(w1, w2, w1.length(), w2.length());
    }
}
