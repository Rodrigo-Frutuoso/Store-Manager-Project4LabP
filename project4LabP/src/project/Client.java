package project;

/**
 * Classe que representa um cliente, que possui um numero de cliente, um 
 * carrinho de compras na forma de lista de items, um tempo de chegada e a
 * duração de processamento do carrinho.
 * 
 * @author EquipaLabP2324
 */
public class Client {

    private final int clientCode;
    private final Product[] shoppingCart;
    private final int arrivalTime;
    private int clientProcessingDuration;

    /**
     * Construtor da classe Client.
     * Recebe o código do cliente, o seu carrinho, o tempo de chegada e o
     * duração de processamento do carrinho.
     * 
     * @param clientCode - código do cliente
     * @param shoppingCart - carrinho de compras
     * @param arrivalTime - tempo de chegada
     * @param clientProcessingDuration - duração de processamento do carrinho do cliente
     */
    public Client(int clientCode, Product[] shoppingCart, int arrivalTime, int clientProcessingDuration) {
        this.clientCode = clientCode;
        this.shoppingCart = shoppingCart.clone();
        this.arrivalTime = arrivalTime;
        this.clientProcessingDuration = clientProcessingDuration;
    }

    /**
     * @return o código do cliente
     */
    public int getClientCode() {
        return clientCode;
    }

    /**
     * @return o carrinho de compras
     */
    public Product[] getShoppingCart() {
        return shoppingCart.clone();
    }

    /**
     * @return o tempo de chegada
     */
    public int getArrivalTime() {
        return arrivalTime;
    }

    /**
     * @return a duração de processamento do carrinho do cliente
     */
    public int getClientProcessingDuration() {
        return clientProcessingDuration;
    }

    /**
     * altera a duração de processamento do carrinho do cliente
     * 
     * @param processingTime - a nova duração de processamento
     */
    public void setClientProcessingDuration(int clientProcessingDuration) {
        this.clientProcessingDuration = clientProcessingDuration;
    }
}
