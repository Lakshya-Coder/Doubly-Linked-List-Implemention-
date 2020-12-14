public class DoublyLinkedList<T> implements Iterable<T> {

    private int size = 0;
    private Node head = null;
    private Node tail = null;

    private class Node {
        T data;
        Node prev;
        Node next;

        public Node(T data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    // Empty this linked list, O(n)
    public void clear() {
        Node trav = head;

        while (trav != null) {
            Node next = trav.next;
            trav.prev = trav.next = null;
            trav.data = null;
            trav = next;
        }

        head = tail = trav = null;
        size = 0;
    }

    // Return the size of linked list
    public int size() {
        return size;
    }

    // Is this linked list is empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // Add a element to tail of the linked list, O(1)
    public void add(T elem) {
        addLast(elem);
    }

    // Add an element to beginning of this linked list, O(1)
    public void addFirst(T elem) {
        // The linked list is empty
        if (isEmpty()) {
            head = tail = new Node(elem, null, null);
        } else {
            head.prev = new Node(elem, null, head);
            head = head.prev;
        }

        size++;
    }

    public void addLast(T elem) {
        // The linked list is empty
        if (isEmpty()) {
            head = tail = new Node(elem, null, null);
        } else {
            tail.next = new Node(elem, tail, null);
            tail = tail.next;
        }

        size++;
    }

    // Check the value of first node if it exists, O(1)
    public T peekFirst() {
        if (isEmpty()) {
            throw new RuntimeException("Empty list");
        }
        return head.data;
    }

    // Check the value of the last node is it exists, O(1)
    public T peekLast() {
        if (isEmpty()) {
            throw new RuntimeException("Empty list");
        }
        return tail.data;
    }

    // Remove the first value at the head of the linked list, O(1)
    public T removeFirst() {

        // Can't remove from an empty list -_-
        if (isEmpty()) {
            throw new RuntimeException("Empty list");
        }

        // Extract the data at the head and move
        // the head pointer forwards one node
        T data = head.data;
        head = head.next;
        --size;

        // If list is empty set the tail to null as well
        if (isEmpty()) {
            tail = null;
        }

        // Do memory clean of the previous node
        else {
            head.prev = null;
        }

        // Return the data that was at the first node we just removed
        return data;
    }

    public T removeLast() {

        // Can't remove data from an empty list -_-
        if (isEmpty()) {
            throw new RuntimeException("Empty list");
        }

        // Extract the data at the tail and move
        // the tail pointer backwards one node
        T data = tail.data;
        tail = tail.prev;
        --size;

        // If list is now empty set the head to null as well
        if (isEmpty()) {
            head = null;
        }

        // Do the memory clean of the node that just removed
        else {
            tail.next = null;
        }

        // Return the data that was at the first node we just removed
        return data;
    }

    // Remove an arbitrary node from the linked list
    public T remove(Node node) {

        // If the node to remove is somewhere either at the
        // head or the tail handle those indepently
        if (node.prev == null) {
            return removeFirst();
        }

        if (node.next == null) {
            return removeLast();
        }

        // Make the pointers of adjacent node skip over 'node'
        node.next.prev = node.prev;
        node.prev.next = node.next;

        // Temporary store the data we want to return
        T data = node.data;
        // Memory cleanup
        node.data = null;
        node = node.prev = node.next = null;

        --size;

        // Return the data at node we just removed
        return data;
    }

    // Remove a node at a particular inde, O(n)
    public T removeAt(int index) {

        // Make sure the index is valid -_-
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }

        int i;
        Node trav;

        // Search from the front of list
        if (index < size / 2) {
            for (i = 0, trav = head; i != index; i++) {
                trav = trav.next;
            }
            // Search from the back of list
        } else {
            for (i = size - 1, trav = tail; i != index; i--) {
                trav = trav.prev;
            }
        }

        return remove(trav);
    }

    // Remove a particular
    public boolean remove(Object obj) {

        Node trav = head;

        // Support for searching for null
        if (obj == null) {
            for (trav = head; trav != null; trav = trav.next) {
                if (trav.data == null) {
                    remove(trav);
                    return true;
                }
            }
            // Search for non null object
        } else {
            for (trav = head; trav != null; trav = trav.next) {
                if (obj.equals(trav.data)) {
                    remove(trav);
                    return true;
                }
            }
        }

        return false;
    }

    // Find the index of a particular value in linked list, O(n)
    public int indexOf(Object obj) {

        int index = 0;
        Node trav = head;

        // Support for searching for null
        if (obj == null) {
            for (trav = head; trav != null; trav = trav.next) {
                if (trav.data == null) {
                    return index;
                }
            }

            // Search for non null object
        } else {
            for (trav = head; trav != null; trav = trav.next) {
                if (obj.equals(trav.data)) {
                    return index;
                }
            }
        }

        return -1;
    }

    // Check is a value is contained within the linked list
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private Node trav = head;

            @Override
            public boolean hasNext() {
                return trav != null;
            }

            @Override
            public T next() {
                T data = trav.data;
                trav = trav.next;
                return data;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[ ");

        Node trav = head;

        while (trav.next != null) {
            sb.append(trav.data + ", ");
            trav = trav.next;
        }

        sb.append(trav.data + " ]");

        return sb.toString();
    }

}
