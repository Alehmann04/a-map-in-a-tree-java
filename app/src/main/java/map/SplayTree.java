package map;

class Node<K> {

  public K key;
  public Node<K> left;
  public Node<K> right;

  public Node(K _key) {
    key = _key;
    left = null;
    right = null;
  }

  public String toString() {
    return key.toString();
  }
}

public class SplayTree<K extends Comparable<K>> {

  public Node<K> root;

  public SplayTree() {
    root = null;
  }

  public SplayTree(K _key) {
    root = new Node<>(_key);
  }

  public void insert(K _key) {
    root = insert(root, _key);
  }

  public void delete(K _key) {}

  public Node<K> get(K _key) {
    return get(root, _key);
  }

  private Node<K> insert(Node<K> _node, K _key) {
    if (_node == null) {
      return new Node<>(_key);
    }

    _node = splay(_node, _key);

    if (_node.key == _key) {
      return _node;
    }

    Node<K> new_node = new Node<>(_key);

    int comparison = _node.key.compareTo(_key);
    if (comparison > 0) {
      new_node.right = _node;
      new_node.left = _node.left;
      _node.left = null;
    } else {
      new_node.left = _node;
      new_node.right = _node.right;
      _node.right = null;
    }
    return new_node;
  }

  private Node<K> get(Node<K> _node, K _key) {
    if (_node == null) {
      return null;
    }
    int comparison = _node.key.compareTo(_key);
    if (comparison == 0) {
      return _node;
    } else if (comparison < 0) {
      return get(_node.right, _key);
    } else {
      return get(_node.left, _key);
    }
  }

  private Node<K> splay(Node<K> _node, K _key) {
    if (_node == null || _node.key == _key) {
      return _node;
    }

    int comparison = _node.key.compareTo(_key);
    if (comparison > 0) {
      if (_node.left == null) {
        return _node;
      }
      comparison = _node.left.key.compareTo(_key);
      if (comparison > 0) {
        _node.left.left = splay(_node.left.left, _key);
        _node = zag(_node);
      } else if (comparison < 0) {
        _node.left.right = splay(_node.left.right, _key);
        if (_node.left.right != null) _node.left = zig(_node.left);
      }
      return (_node.left == null) ? _node : zag(_node);
    } else {
      if (_node.right == null) {
        return _node;
      }
      comparison = _node.right.key.compareTo(_key);
      if (comparison > 0) {
        _node.right.left = splay(_node.right.left, _key);
        if (_node.right.left != null) _node.right = zag(_node.right);
      } else if (comparison < 0) {
        _node.right.right = splay(_node.right.right, _key);
        _node = zig(_node);
      }
      return (_node.right == null) ? _node : zig(_node);
    }
  }

  private Node<K> zig(Node<K> _node) {
    Node<K> new_node = _node.left;
    _node.left = new_node.right;
    new_node.right = _node;
    return new_node;
  }

  private Node<K> zag(Node<K> _node) {
    Node<K> new_node = _node.right;
    _node.right = new_node.left;
    new_node.left = _node;
    return new_node;
  }

  public String toString() {
    if (root != null) {
      String data = toString(root);
      return data.substring(0, data.length() - 2);
    } else {
      return "null";
    }
  }

  private String toString(Node<K> _node) {
    String data = "";
    if (_node != null) {
      data = _node.toString() + ", ";
      data += toString(_node.left);
      data += toString(_node.right);
    }
    return data;
  }
}
