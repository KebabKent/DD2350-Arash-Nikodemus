
/* Labb 2 i DD2350 Algoritmer, datastrukturer och komplexitet    */
/* Se labbinstruktionerna i kursrummet i Canvas                  */
/* Ursprunglig författare: Viggo Kann KTH viggo@nada.kth.se      */
import java.util.LinkedList;
import java.util.List;

public class ClosestWords {
  LinkedList<String> closestWords = null;

  int closestDistance = -1;

  int[][] dynProgMat;

  public ClosestWords(String w, List<String> wordList) {

    // PartDistOrg pd = new PartDistOrg();
    // PartDist1 pd = new PartDist1();
    // PartDist2 pd = new PartDist2(w);
    // PartDist3 pd = new PartDist3(w);
    PartDist4 pd = new PartDist4(w);
    // PartDist5 pd = new PartDist5(w);

    for (String s : wordList) {

      if (Math.abs(w.length() - s.length()) > closestDistance && closestDistance != -1) {
        continue;
      }

      int dist = pd.distance(w, s, closestDistance);
      // int dist = pd.distance(w, s);

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
