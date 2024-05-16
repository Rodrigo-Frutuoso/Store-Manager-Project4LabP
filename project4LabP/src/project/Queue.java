package project;

/**
 * Interface para uma fila mutável: uma colecção de objectos que são inseridos e
 * removidos de acordo com o princípio "first-in first-out"
 * 
 * @author Algoritmos e Estruturas de Dados 2023-24, Faculdade de Ciências,
 *         Universidade de Lisboa
 *
 * @param <E> O tipo dos elemento na fila
 */
public interface Queue<E> {

    /**
     * Inserir um elemento na cauda desta fila
     * 
     * @param e O elemento a inserir
     */
    void enqueue(E e);

    /**
     * Remover o elemento na cabeça desta fila
     * 
     * @requires A fila não está vazia
     */
    void dequeue();

    /**
     * @return e O elemento na cabeça desta fila
     * @requires A fila não está vazia
     */
    E front();

    /**
     * @return O número de elementos nesta fila
     */
    int size();

    /**
     * @return true se a fila está vazia, false caso contrário
     */
    default boolean isEmpty() { return size() == 0; }
}
