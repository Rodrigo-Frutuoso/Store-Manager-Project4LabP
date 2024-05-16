package project;

/**
 * Classe que representa um produto.
 * A cada produto é associado um código, um preço e duração de processamento.
 * 
 * @author EquipaLabP2324
 */
public class Product {

    private final String prodCode;
    private final double prodPrice;
    private final int prodProcessingDuration;

    /**
     * Construtor da classe Product, que recebe o código do produto, o seu
     * preço e a duração de processamento.
     * 
     * @param prodCode - código do produto
     * @param prodPrice - preço do produto
     * @param prodProcessingDuration - duração de processamento do produto
     */
    public Product(String prodCode, double prodPrice, int prodProcessingDuration) {
        this.prodCode = prodCode;
        this.prodPrice = prodPrice;
        this.prodProcessingDuration = prodProcessingDuration;
    }

    /**
     * @return o código do produto
     */
    public String getProdCode() {
        return prodCode;
    }

    /**
     * @return o preço do produto
     */
    public double getProdPrice() {
        return prodPrice;
    }

    /**
     * @return a duração de processamento do produto
     */
    public int getProdProcessingDuration() {
        return prodProcessingDuration;
    }
}
