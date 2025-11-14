import java.util.ArrayList;

/**
 * Huvudklass för NP-reduktionen från Graffärgning till Rollbesättning.
 * Läser en Graffärgnings-instans och skriver ut en Rollbesättnings-instans.
 */
public class Color {
    Kattio io;

    int v, e, m;
    ArrayList<int[]> edges;

    /**
     * Läser in indata för Graffärgningsproblemet (v, e, m och alla kanter).
     */
    void readColor() {
        // v (antal noder, 1 <= V <= 300), e (antal kanter, 0 <= E <= 25000),
        // m (antal färger, 1 <= m <= 2^30)

        this.v = io.getInt();
        this.e = io.getInt();
        this.m = io.getInt();

        edges = new ArrayList<int[]>();
        edges.add(null); // Fyller index 0 med null för att kunna använda 1-indexering

        for (int i = 0; i < e; i++) {
            int from = io.getInt();
            int to = io.getInt();
            int[] edge = new int[2];
            edge[0] = from;
            edge[1] = to;
            edges.add(edge);
        }
    }

    void reduceColorToRoll() {
        // A = Graffärgning (v noder, e kanter, m färger)
        // B = Rollbesättning (n roller, s scener, k skådespelare)

        // Vi använder k=3 för att skapa en garanterad JA-instans utan konflikter
        // när m >= v, eftersom den ursprungliga logiken för k=2 bröt mot Villkor 4.
        if (v > 0 && m >= v) {
            // Fall 1: m >= v. Svaret på Graffärgning är trivialt "JA".

            io.println(3); // n = 3 roller
            io.println(2); // s = 2 scener
            io.println(3); // k = 3 skådespelare (p1, p2, p3)

            // Villkor 1 (Roller):
            io.println("1 1"); // r1 kan spelas av p1
            io.println("1 2"); // r2 kan spelas av p2
            io.println("1 3"); // r3 kan spelas av p3 (buffert)

            // Villkor 2 (Scener):
            // Scen 1: r1 (p1) och r3 (p3). OLIKA skådespelare.
            io.println("2 1 3");
            // Scen 2: r2 (p2) och r3 (p3). OLIKA skådespelare.
            io.println("2 2 3");
            // Divorna p1 och p2 är i olika scener (s1 respektive s2). "JA".

        } else {
            // Fall 2: m < v. (Ursprunglig polynomisk reduktion)

            // 1. Antal roller, n = V + 2
            int n = v + 2;

            // 2. Antal scener, s = E + V + 1
            int s = e + v + (v > 0 ? 1 : 0);

            // 3. Antal skådespelare, k = m + 2
            int k = m + 2;

            io.println(n);
            io.println(s);
            io.println(k);

            // 4. Villkor typ 1 (n rader):

            // Rad 1 till V: Nod-rollerna (r_1 ... r_V)
            for (int i = 0; i < v; i++) {
                io.print(m);
                for (int j = 3; j <= m + 2; j++) {
                    io.print(" " + j);
                }
                io.println();
            }

            // Rad V+1: Diva 1-rollen (r_{V+1})
            io.println("1 1");

            // Rad V+2: Diva 2-rollen (r_{V+2})
            io.println("1 2");

            // 5. Villkor typ 2 (s rader):

            // Scener 1 till E: "Konfliktscener" för varje kant
            for (int i = 1; i <= e; i++) {
                int[] edge = edges.get(i);
                io.println("2 " + edge[0] + " " + edge[1]);
            }

            // Scener E+1 till E+V: "Dummy-scener" (länka r_i till r_{V+1})
            for (int i = 1; i <= v; i++) {
                io.println("2 " + i + " " + (v + 1));
            }

            // Scen E+V+1: "Dummy-scen" (länka r_1 till r_{V+2})
            if (v > 0) {
                io.println("2 1 " + (v + 2));
            }
        }

        io.flush();
    }

    /**
     * Konstruktor som initierar Kattio och kör reduktionen.
     */
    Color() {
        io = new Kattio(System.in, System.out);

        readColor();
        reduceColorToRoll();

        io.close();
    }

    /**
     * Main-metod som startar programmet.
     */
    public static void main(String args[]) {
        new Color();
    }
}
