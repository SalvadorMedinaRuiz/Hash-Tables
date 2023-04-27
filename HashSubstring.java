import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        //printOccurrences(getOccurrencesFast(readInput()));
        out.close();
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }
    
    ///SLOW FIX///
    
    private static long PolyHash2(String s) { //class
		long hash = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			hash = (hash * 31 + s.charAt(i));
		}
    	return hash;
    }
    
    private static boolean AreEqual(String s1, String s2) { //class
    	for (int i = 0; i <s1.length(); i++) {
    		if (s1.charAt(i) != s2.charAt(i)) {
    			return false;
    		}
    	}
    	return true;
    }
    
    ///////////////////////
    private static int[] PrecomputeHashes(String T, int absolouteP) {
		int[] H = new int[T.length() - absolouteP + 1];
		String S = T.substring(T.length() - absolouteP);
		H[T.length() - absolouteP] = (int) PolyHash2(S);
		int y = 1;
		for (int i = 0; i < absolouteP; i++) {
			y = (y * 31);
		}
    	for (int i = T.length() - absolouteP -1; i >= 0; i--) {
    		H[i] = (31 * H[i + 1] + T.charAt(i) - y * T.charAt(i + absolouteP));
    	}
    	return H;
    }
    //////////////////////////
    
    /*
    private static int[] precomputeHashes( String text, int m) {
    	int[] hashes = new int[text.length() -m+1];
		for(int i =0; i <= text.length() - m; i++){
			int hashCode = (int) PolyHash2( text.substring(i, i +m));
			hashes[i] = hashCode;
		}
    	return hashes;    	
    }
    */
	private static List<Integer> getOccurrences(Data input) { //class
		String s = input.pattern, t = input.text;
		int m = s.length(), n = t.length();
		List<Integer> occurrences = new ArrayList<Integer>();
		int pHash = (int) PolyHash2(s);
		int[] tHashes = PrecomputeHashes(t, m);
		
		for (int i =0; i +m<=n; i++){
			int tHash = tHashes[i];
			if (pHash != tHash) {
				continue;
			}
			boolean equal = AreEqual(s, t.substring(i, i+m));
		if (equal){
				occurrences.add(i);
			}
		}
		return occurrences;
	}
	
	///SLOW FIX///
	
    static class Data {
        String pattern;
        String text;
        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

