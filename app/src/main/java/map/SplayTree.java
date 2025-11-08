package map;

public class SplayTree<K extends Comparable<K>> {

  private class Node {

    K key;
    Node left, right;

    public Node(K _key) {
      key = _key;
      left = null;
      right = null;
    }

    public String toString() {
      return key.toString();
    }
  }

  private Node root;

  public SplayTree() {
    root = null;
  }

  public boolean search(K _key) {
    return get(_key) != null;
  }

  public K get(K _key) {
    if (root == null) return null;
    root = splay(root, _key);
    int cmp = _key.compareTo(root.key);
    if (cmp == 0) {
      return root.key;
    } else {
      return null;
    }
  }

  public void insert(K _key) {
    if (root == null) {
      root = new Node(_key);
      return;
    }

    root = splay(root, _key);

    int cmp = _key.compareTo(root.key);

    if (cmp < 0) {
      Node n = new Node(_key);
      n.left = root.left;
      n.right = root;
      root.left = null;
      root = n;
    } else if (cmp > 0) {
      Node n = new Node(_key);
      n.right = root.right;
      n.left = root;
      root.right = null;
      root = n;
    }
  }

  public void remove(K _key) {
    if (root == null) return;

    root = splay(root, _key);

    int cmp = _key.compareTo(root.key);

    if (cmp == 0) {
      if (root.left == null) {
        root = root.right;
      } else {
        Node x = root.right;
        root = root.left;
        splay(root, _key);
        root.right = x;
      }
    }
  }

  private Node splay(Node _node, K _key) {
    if (_node == null) return null;

    int cmp1 = _key.compareTo(_node.key);

    if (cmp1 < 0) {
      if (_node.left == null) {
        return _node;
      }
      int cmp2 = _key.compareTo(_node.left.key);
      if (cmp2 < 0) {
        _node.left.left = splay(_node.left.left, _key);
        _node = zig(_node);
      } else if (cmp2 > 0) {
        _node.left.right = splay(_node.left.right, _key);
        if (_node.left.right != null) _node.left = zag(_node.left);
      }

      if (_node.left == null) return _node;
      else return zig(_node);
    } else if (cmp1 > 0) {
      if (_node.right == null) {
        return _node;
      }

      int cmp2 = _key.compareTo(_node.right.key);
      if (cmp2 < 0) {
        _node.right.left = splay(_node.right.left, _key);
        if (_node.right.left != null) _node.right = zig(_node.right);
      } else if (cmp2 > 0) {
        _node.right.right = splay(_node.right.right, _key);
        _node = zag(_node);
      }

      if (_node.right == null) return _node;
      else return zag(_node);
    } else return _node;
  }

  public int height() {
    return __height__(root);
  }

  private int __height__(Node _node) {
    if (_node == null) return -1;
    return 1 + Math.max(__height__(_node.left), __height__(_node.right));
  }

  public int size() {
    return __size__(root);
  }

  private int __size__(Node _node) {
    if (_node == null) return 0;
    else return 1 + __size__(_node.left) + __size__(_node.right);
  }

  private Node zig(Node _node) {
    Node new_node = _node.left;
    _node.left = new_node.right;
    new_node.right = _node;
    return new_node;
  }

  private Node zag(Node _node) {
    Node new_node = _node.right;
    _node.right = new_node.left;
    new_node.left = _node;
    return new_node;
  }

  public String toString() {
    if (root == null) return "null";
    String data = inorder(root).trim();
    return data.substring(0, data.length() - 1);
  }

  private String inorder(Node _node) {
    if (_node == null) return "";
    else {
      String data = "";
      data += inorder(_node.left);
      data += _node + ", ";
      data += inorder(_node.right);

      return data;
    }
  }
}
