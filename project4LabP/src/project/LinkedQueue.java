package project;

/**
 * Uma fila implementada com uma lista simplesmente ligada
 * 
 * @author Algoritmos e Estruturas de Dados 2023-24, Faculdade de Ciências,
 *         Universidade de Lisboa
 *
 * @param <E> O tipo dos elementos da fila
 */
public class LinkedQueue<E> implements Queue<E> {

    /**
     * Os nós desta fila
     */
    private class Node {
        E item;
        Node next;
    }

    /**
     * O nó de onde retiramos elementos na lista
     */
    private Node first;

    /**
     * O nó onde inserimos elementos na lista
     */
    private Node last;

    /**
     * O número de elementos nesta fila
     */
    private int size;

    @Override
    public void enqueue(E e) {
        Node oldLast = last;
        last = new Node();
        last.item = e;
        if (first == null) // estava vazia?
            first = last;
        else
            oldLast.next = last;
        size++;
    }

    @Override
    public void dequeue() {
        first = first.next;
        if (first == null) // ficou vazia?
            last = null;
        size--;
    }

    @Override
    public E front() {
        return first.item;
    }

    @Override
    public int size() {
        return size;
    }
}
