
class Node {
  private int value;
  private Node next = null;

  Node(int value) {
    this.value = value;
  }

  public int getValue() {
    return this.value;
  }

  public Node getNext() {
    return this.next;
  }

  public void setNext(Node next) {
    this.next = next;
  }
}

class LinkedList {
  private int size = 0;
  private Node head = null;

  public void insert(int value) {
    var node = new Node(value);
    this.size++;

    if (this.head == null) {
      this.head = node;
      return;
    }

    var tmp = this.head;

    while (tmp != null) {
      if (tmp.getNext() == null) {
        tmp.setNext(node);
        break;
      }

      tmp = tmp.getNext();
    }
  }

  public void insertAtBeginning(int value) {
    var node = new Node(value);

    node.setNext(this.head);
    this.head = node;
    this.size++;
  }

  public void pop() {
    if (this.head == null) {
      return;
    }

    Node previous = null;
    var current = this.head;

    while (current != null) {
      if (current.getNext() == null) {
        previous.setNext(null);
        break;
      }

      previous = current;
      current = current.getNext();
    }
  }

  public void show() {
    var node = this.head;

    while (node != null) {
      System.out.println(node.getValue());
      node = node.getNext();
    }
  }

  public int getSize() {
    return this.size;
  }
}

class Main {
  public static void main(String[] args) {
    var linkedList = new LinkedList();

    linkedList.insert(10);
    linkedList.insertAtBeginning(5);
    linkedList.insert(20);
    linkedList.insert(30);
    linkedList.insertAtBeginning(2);
    linkedList.insert(40);
    linkedList.pop();
    linkedList.show();
  }
}
