package map;

public class TreeMap {

  SplayTree<KVPair> tree;

  public TreeMap() {
    tree = new SplayTree<KVPair>();
  }

  public void insert(String _key, String _value) {
    tree.insert(new KVPair(_key, _value));
  }

  public String get(String _key) {
    Node<KVPair> node = tree.get(new KVPair(_key));
    if (node != null) {
      return node.key.value;
    } else {
      return "null";
    }
  }

  public String toString() {
    return tree.toString();
  }
}
