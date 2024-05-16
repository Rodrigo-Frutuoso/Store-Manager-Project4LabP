package project;

/**
 * Uma fila implementada com um array circular
 * 
 * @author Algoritmos e Estruturas de Dados 2023-24, Faculdade de Ciências,
 *         Universidade de Lisboa
 *
 * @param <E> O tipo dos elementos da fila
 */
public class ArrayQueue<E> implements Queue<E> {

    /**
     * Os elementos na fila
     */
    private E[] data;

    /**
     * O índice do elemento na frente da fila
     */
    private int front;

    /**
     * O primeiro índice livre na cauda da fila
     */
    private int rear;

    /**
     * O número de elementos na fila
     */
    private int size;

    /**
     * A capacidade inicial da fila
     */
    private static final int DEFAULT_CAPACITY = 1;

    public ArrayQueue() {
        data = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void enqueue(E e) {
        if (size == data.length)
            resize(size * 2);
        data[rear] = e;
        rear = (rear + 1) % data.length;
        size++;
    }

    @Override
    public void dequeue() {
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        if (size > 0 && size == data.length / 4)
            resize(data.length / 2);
    }

    @Override
    public E front() {
        return data[front];
    }

    @Override
    public int size() {
        return size;
    }

    private void resize(int capacity) {
        @SuppressWarnings("unchecked")
        E[] newData = (E[]) new Object[capacity];
        for (int i = 0, j = front; i < size; i++, j = (j + 1) % data.length)
            newData[i] = data[j];
        data = newData;
        front = 0;
        rear = size;
    }
}
