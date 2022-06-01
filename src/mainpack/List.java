package mainpack;

public class List {
    public Node head;

    public List() {
        head = null;
    }

    public void add(Object data) {
        if (head != null) {
            head.addToTail(data);
        } else {
            head = new Node(data);
        }
    }


    public Object get(int index){
        Node current = head;
        for(int i=0;i < index;i++){
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
        Node currentNode = head;
        Node previousNode = null;

        while(currentNode.next != null)
        {
            if (currentNode.data.equals(d)) {
                if (currentNode == head) {
                    head = currentNode.next;
                } else {
                    previousNode.next = currentNode.next;
                }
            }
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
    }

    public class Node {
        Node next = null;
        Object data;

        public Node(Object data) {
            this.data = data;
        }

        void addToTail(Object d) {

            Node end = new Node(d);
            Node n = head;
            while (n.next != null) {
                n = n.next;
            }
            n.next = end;

        }
    }
}
