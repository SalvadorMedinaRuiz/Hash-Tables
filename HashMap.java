import java.util.ArrayList;

public class HashMap {
	class Element{
		Object key;
		String value;
		
		Element(Object key, String value){
			this.key = key;
			this.value = value;
		}
	}
	
	ArrayList<Element> chains[];
	int bucketCount;
	
	HashMap(int cardinality){
		// cardnality, aka, bucket count, or m
		this.chains = new ArrayList[cardinality];
		this.bucketCount = cardinality;
		for (int i = 0; i <bucketCount; i++) {
			this.chains[i] = new ArrayList<Element>();
		}
	}
	
	private int hashFunction(Object key) {
		int hashCode = key.hashCode();
		return hashCode % this.bucketCount;
	}
	
	boolean hasKey(Object key) {
		int hashCode = hashFunction(key);
		ArrayList<Element> chain = this.chains[hashCode];
		for (Element e : chain) {
			if (e.key.equals(key)) {
				return true;
			}
		}
		return false;
	}
	
	void set(Object key, String value) {
		int hashCode = hashFunction(key);
		ArrayList<Element> chain = this.chains[hashCode];
		
		for (int i =0; i < chain.size(); i++) {
			if (chain.get(i).key.equals(key)) {
				chain.get(i).value = value;
				return;
			}
		}
		chain.add(0, new Element(key, value));
	}
	
	String get(Object key) {
		int hashCode = hashFunction(key);
		ArrayList<Element> chain = this.chains[hashCode];
		
		for (Element e : chain) {
			if (e.key.equals(key)) {
				return e.value;
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		String key1 = "abc";
		String key2 = "abc";
		
		HashMap myMap = new HashMap(100);
		myMap.set(key1, "hello");
		myMap.set(key2, "world");
		String value  = myMap.get(key1);
		System.out.println(value);
	}
}
