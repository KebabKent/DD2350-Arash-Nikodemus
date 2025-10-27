public class PartDist4 {
    int[][] dynProgMat;
    int closestDistance;

    char[] w1Chars;
    char[] w2Chars;
    char[] lastW2Chars;
    int lastW2Len;

    public PartDist4(String w) {
        int wLen = w.length();
        dynProgMat = new int[wLen + 1][90]; // Längsta ordet i SAOL är 28 bokstäver.
                                            // Det längsta är 21 i ordlistan.
                                            // Kattis gav fel för 29 så körde 90 och det funkade
                                            // Skulle vara snyggt om den var dynamisk men ¯\_( ͡° ͜ʖ ͡°)_/¯.
        for (int i = 0; i <= wLen; i++) {
            dynProgMat[i][0] = i;
        }

        lastW2Len = 0;

        w1Chars = w.toCharArray();
        w2Chars = new char[90];
        lastW2Chars = new char[90];
    }

    int partDist4(String w1, String w2, int w1len, int w2len) {

        int i, j; // Ger säkert någon liten prestandaförbättring ;)

        w2.getChars(0, w2len, w2Chars, 0);

        int maxSame = Math.min(w2len, lastW2Len);
        for (i = 0; i < maxSame; i++) {
            if (w2Chars[i] != lastW2Chars[i]) {
                break;
            }
        }

        for (i += 1; i <= w2len; i++) { // Börja ifrån där dom skiljer sig
            dynProgMat[0][i] = i;
            int max = Math.min(w1len, closestDistance + i);
            for (j = 1; j <= max; j++) {
                int cost = (w1Chars[j - 1] == w2Chars[i - 1]) ? 0 : 1;

                dynProgMat[j][i] = Math.min(
                        Math.min(
                                dynProgMat[j - 1][i] + 1,
                                dynProgMat[j][i - 1] + 1),
                        dynProgMat[j - 1][i - 1] + cost);
            }
        }

        lastW2Len = w2len;
        lastW2Chars = w2Chars.clone();

        return dynProgMat[w1len][w2len];
    }

    public int distance(String w1, String w2, int closestDistance) {
        // För att bevisa att den gamla rekursiva funktionen ger samma resultat som den
        // nya kommentera ut följande rader och kommentera return partDist...

        // int gg = partDist2(w1, w2, w1.length(), w2.length());
        // if (gg != partDistOriginal(w1, w2, w1.length(), w2.length())) {
        // System.out.println("Fejl!!!");
        // }
        // return gg;

        return partDist4(w1, w2, w1.length(), w2.length());
    }
}
