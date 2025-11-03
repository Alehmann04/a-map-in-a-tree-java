package map;

public class KVPair implements Comparable<KVPair> {

  public String key;
  public String value;

  public KVPair() {
    key = "";
    value = "";
  }

  public KVPair(String _key) {
    key = _key;
    value = "";
  }

  public KVPair(String _key, String _value) {
    key = _key;
    value = _value;
  }

  public int compareTo(KVPair _pair) {
    return key.compareTo(_pair.key);
  }

  public String toString() {
    return ("[ \"" + key + "\" : \"" + value + "\" ]");
  }
}
