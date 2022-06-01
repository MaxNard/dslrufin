package mainpack;

public class List {
    public Node head;

    public List() {
        head = null;
    }

    public void add(Object data) {
        if (head != null) {
            head.appendToTail(data);
        } else {
            head = new Node(data);
        }
    }


    public Object get(int index){
        Node current = head;
        for(int i=0;i<index;i++){
            if(current==null){
                return -1;
            }
            else{
                current=current.next;
            }
        }
        return current.data;
    }

    public void delete(Object d) {
        Node newHead = new Node(0);
        newHead.next = head;
        Node previous = newHead;
        Node current = head;

        while (current != null) {
            if (current.data.equals(d)) {
                previous.next = current.next;
            } else {
                previous = current;
            }
            current = current.next;
        }
        head = newHead.next;
    }

    public class Node {
        Node next = null;
        Object data;

        public Node(Object data) {
            this.data = data;
        }

        void appendToTail(Object d) {

            Node end = new Node(d);
            Node n = head;
            while (n.next != null) {
                n = n.next;
            }
            n.next = end;

        }
    }
}
