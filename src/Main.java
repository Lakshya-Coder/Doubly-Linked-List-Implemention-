public class Main {
    public static void main(String[] args) {

        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);

        System.out.println(list);
        // System.out.println(list.peekFirst());
        // System.out.println(list.peekLast());
        // int x1 = list.removeFirst();
        // int x2 = list.removeLast();
        // System.out.println(list + " " + x1 + " " + x2);
        // int x3 = list.removeAt(list.indexOf(10));
        // boolean x4 = list.remove(10);
        // System.out.println(x3);
        // System.out.println(x4);
    }
}
