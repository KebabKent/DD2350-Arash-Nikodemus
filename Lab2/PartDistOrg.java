public class PartDistOrg {
    public static int partDistOriginal(String w1, String w2, int w1len, int w2len) {
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

    public int distance(String w1, String w2) {
        return partDistOriginal(w1, w2, w1.length(), w2.length());
    }
}
