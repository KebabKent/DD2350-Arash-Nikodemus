/**
 * Exempel på in- och utdatahantering för maxflödeslabben i kursen
 * ADK.
 *
 * Använder Kattio.java för in- och utläsning.
 * Se http://kattis.csc.kth.se/doc/javaio
 *
 * @author: Per Austrin
 */

public class BipRed {
	Kattio io;

	int x, y, e;
	int vFlow, eFlow;
	int[][] edges;

	void readBipartiteGraph() {
		// Läs antal hörn och kanter
		this.x = io.getInt();
		this.y = io.getInt();
		this.e = io.getInt();

		this.eFlow = this.e + this.x + this.y;
		edges = new int[eFlow][2]; // Varje rad är en kant (a,b)

		// Läs in kanterna
		for (int i = 0; i < this.e; i++) {
			int a = io.getInt();
			int b = io.getInt();

			edges[i][0] = a;
			edges[i][1] = b;
		}
	}

	void writeFlowGraph() {
		this.vFlow = this.x + this.y + 2;
		int s = 1, t = vFlow;
		int c = 1; // Kapacitet 1 för alla kanter

		// Skriv ut antal hörn och kanter samt källa och sänka
		io.println(vFlow);
		io.println(s + " " + t);
		io.println(eFlow);

		// s till X
		for (int i = 2; i < x + 2; i++) {
			int a = i;
			io.println(s + " " + a + " " + c);
		}

		// a till b
		for (int i = 0; i < this.e; i++) {
			int a = edges[i][0] + 1;
			int b = edges[i][1] + 1;
			io.println(a + " " + b + " " + c);
		}

		// Y till t
		for (int i = x + 2; i < x + y + 2; i++) {
			int b = i;
			io.println(b + " " + t + " " + c);
		}

		// Var noggrann med att flusha utdata när flödesgrafen skrivits ut!
		io.flush();

		// Debugutskrift
		System.err.println("Skickade iväg flödesgrafen");
	}

	int[][] maxEdges;
	int maxX, maxY;
	int maxMatch;

	void readMaxFlowSolution() {
		// Läs in antal hörn, kanter, källa, sänka, och totalt flöde
		// (Antal hörn, källa och sänka borde vara samma som vi i grafen vi
		// skickade iväg)
		int v = io.getInt();
		int s = io.getInt();
		int t = io.getInt();
		int totflow = io.getInt();
		int e = io.getInt();

		this.maxEdges = new int[e][2];
		this.maxX = 0;
		this.maxY = 0;
		this.maxMatch = 0;

		for (int i = 0; i < e; ++i) {
			// Flöde f från a till b
			int a = io.getInt();
			int b = io.getInt();
			int f = io.getInt();

			if (a == s) {
				this.maxX++;
			} else if (b == t) {
				this.maxY++;
			} else {
				this.maxEdges[this.maxMatch][0] = a - 1;
				this.maxEdges[this.maxMatch][1] = b - 1;
				this.maxMatch++;
			}
		}
	}

	void writeBipMatchSolution() {
		// Skriv ut antal hörn och storleken på matchningen
		io.println(this.x + " " + this.y);
		io.println(this.maxMatch);

		for (int i = 0; i < this.maxMatch; i++) {
			int a = this.maxEdges[i][0];
			int b = this.maxEdges[i][1];

			io.println(a + " " + b);
		}

	}

	BipRed() {
		io = new Kattio(System.in, System.out);

		readBipartiteGraph();

		writeFlowGraph();

		readMaxFlowSolution();

		writeBipMatchSolution();

		// debugutskrift
		System.err.println("Bipred avslutar\n");

		// Kom ihåg att stänga ner Kattio-klassen
		io.close();
	}

	public static void main(String args[]) {
		new BipRed();
	}
}
