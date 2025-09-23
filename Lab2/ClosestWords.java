
/* Labb 2 i DD2350 Algoritmer, datastrukturer och komplexitet    */
/* Se labbinstruktionerna i kursrummet i Canvas                  */
/* Ursprunglig författare: Viggo Kann KTH viggo@nada.kth.se      */
import java.util.LinkedList;
import java.util.List;

public class ClosestWords {
  LinkedList<String> closestWords = null;

  int closestDistance = -1;

  int[][] dynProgMat;
  int cost;

  int partDistOriginal(String w1, String w2, int w1len, int w2len) {
    if (w1len == 0)
      return w2len;
    if (w2len == 0)
      return w1len;
    int res = partDistOriginal(w1, w2, w1len - 1, w2len - 1) +
        (w1.charAt(w1len - 1) == w2.charAt(w2len - 1) ? 0 : 1);
    int addLetter = partDistOriginal(w1, w2, w1len - 1, w2len) + 1;
    if (addLetter < res)
      res = addLetter;
    int deleteLetter = partDistOriginal(w1, w2, w1len, w2len - 1) + 1;
    if (deleteLetter < res)
      res = deleteLetter;
    return res;
  }

  int partDist(String w1, String w2, int w1len, int w2len) {
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

  int distance(String w1, String w2) {
    // För att bevisa att den gamla rekursiva funktionen ger samma resultat som den
    // nya kommentera ut följande rader och kommentera ut return partDist...

    // int gg = partDist(w1, w2, w1.length(), w2.length());
    // System.out.println(gg == partDistOriginal(w1, w2, w1.length(), w2.length()));
    // return gg;

    return partDist(w1, w2, w1.length(), w2.length());
  }

  public ClosestWords(String w, List<String> wordList) {
    for (String s : wordList) {
      int dist = distance(w, s);
      // System.out.println("d(" + w + "," + s + ")=" + dist);
      if (dist < closestDistance || closestDistance == -1) {
        closestDistance = dist;
        closestWords = new LinkedList<String>();
        closestWords.add(s);
      } else if (dist == closestDistance)
        closestWords.add(s);
    }
  }

  int getMinDistance() {
    return closestDistance;
  }

  List<String> getClosestWords() {
    return closestWords;
  }
}
