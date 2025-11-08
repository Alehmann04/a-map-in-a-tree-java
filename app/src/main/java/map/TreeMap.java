package map;

public class TreeMap {

  SplayTree<KVPair> tree;

  public TreeMap() {
    tree = new SplayTree<>();
  }

  public boolean insert(String _key, String _value) {
    try {
      tree.insert(new KVPair(_key, _value));
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public boolean delete(String _key) {
    try {
      tree.remove(new KVPair(_key));
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public boolean search(String _key) {
    return tree.search(new KVPair(_key));
  }

  public String get(String _key) {
    KVPair entry = tree.get(new KVPair(_key));
    if (entry != null) {
      return entry.value;
    } else {
      return "null";
    }
  }

  public int height() {
    return tree.height();
  }

  public int size() {
    return tree.size();
  }

  public String toString() {
    return tree.toString();
  }
}
