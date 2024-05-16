package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

/**
 * Esta class Counter,representa uma loja com um conjunto de balcões. Esta
 * classe é responsável por atribuir a cada balcão os clientes que forem sendo
 * adicionados de acordo com os tempos de chegada, e de maneira a que cada novo
 * cliente vá para o balcão onde conseguirá ser processado mais rapidamente.
 * 
 * @author Rodrigo Frutuoso 61865
 */
public class Store {
    private Counter[] counters;
    private final Product[] productCatalog;
    private double totalSalesAmount;
    private StringBuilder sb;
    public static final String EOL = System.lineSeparator();

    /**
     * Constrói uma loja com base em um arquivo de entrada contendo informações
     * sobre os balcões e o catálogo de produtos.
     * 
     * @param fileName O nome do arquivo de entrada.
     * @param sb       O StringBuilder para registro de atividades
     * @throws FileNotFoundException Se o arquivo especificado não for encontrado.
     */
    public Store(String fileName, StringBuilder sb) throws FileNotFoundException {
        totalSalesAmount = 0.0;
        this.sb = sb;
        Scanner sc = new Scanner(new File(fileName));

        String[] info = sc.nextLine().split(" ");
        int numCounters = Integer.parseInt(info[0]);
        int numProducts = Integer.parseInt(info[1]);
        counters = new Counter[numCounters];
        productCatalog = new Product[numProducts];

        for (int i = 0; i < numProducts; i++) {
            String[] productInfo = sc.nextLine().split(" ");
            productCatalog[i] = new Product(productInfo[0], Double.parseDouble(productInfo[1]),
                    Integer.parseInt(productInfo[2]));
        }

        for (int i = 0; i < numCounters; i++) {
            counters[i] = new Counter(i, 0, sb);
        }
        sc.close();

    }

    /**
     * Processa os eventos descritos no arquivo especificado.
     * 
     * @param fileName O nome do arquivo contendo os eventos.
     * @throws FileNotFoundException Se o arquivo especificado não for encontrado.
     */
    public void processEvents(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName));
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] event = line.split(" ");
            int time = Integer.parseInt(event[0]);

            if (event[1].equals("CLIENT")) {
                processClient(createClient(event));

            } 
            else { // if (event[1].equals("COUNTER"))
                counters = Arrays.copyOf(counters, counters.length + 1);
                counters[counters.length - 1] = new Counter(counters.length - 1, time, sb);
            }
        }
        sb.append("All clients have been assigned to a counter!" + EOL);
        while (!isProcessingFinished()) {
            Counter counter = counters[firstCounterToFinishClient()];
            counter.processQueueForDuration(counter.currentClient().getClientProcessingDuration());
        }
        sb.append("All clients have been processed!" + EOL);
        sb.append("Total sales: " + String.format(Locale.US, "%.2f", totalSalesAmount) + "€.");
        sc.close();
    }

    /**
     * Cria um cliente a partir de um evento e atualiza o valor total das vendas.
     * 
     * @param event O evento a ser processado. O primeiro elemento do array deve ser
     *              o tempo do evento, o segundo elemento deve ser a string
     *              "CLIENT", o terceiro elemento deve ser o código do cliente, e os
     *              elementos restantes devem ser os códigos dos produtos no
     *              carrinho de compras do cliente.
     * @return O novo Client criado.
     */
    private Client createClient(String[] event) {
        int time = Integer.parseInt(event[0]);
        int clientCode = Integer.parseInt(event[2]);
        Product[] shoppingCart = new Product[event.length - 3];
        int processingTime = 0;
        int validProductCount = 0;
        for (int i = 3; i < event.length; i++) {
            for (Product product : productCatalog) {
                if (product.getProdCode().equals(event[i])) {
                    shoppingCart[validProductCount] = product;
                    validProductCount++;
                    processingTime += product.getProdProcessingDuration();
                    totalSalesAmount += product.getProdPrice();
                }
            }
        }
        return new Client(clientCode, Arrays.copyOf(shoppingCart, validProductCount), time, processingTime);
    }

    /**
     * Processa um novo cliente, atribuindo-o ao balcão com menos tempo de
     * processamento.
     * 
     * @param client O cliente a ser processado.
     * @return O tempo atual do balcão após a atribuição do cliente.
     */
    public int processClient(Client client) {
        for (Counter counter : counters) {
            counter.processQueueUntilTime(client.getArrivalTime());
        }
        counters[firstCounterToFinish()].addClient(client);
        return client.getArrivalTime();
    }

    /**
     * Obtém o índice do primeiro balcão a terminar o processamento de todos os
     * clientes.
     * 
     * @return O índice do primeiro balcão a terminar o processamento de todos os
     *         clientes.
     * @requires {@code !isProcessingFinished()} Todos os balcões não estejam vazios
     * @ensures índice de um balcão
     * 
     */
    public int firstCounterToFinish() {
        int min = 0;
        for (int i = 1; i < counters.length; i++) {
            if (getFinishTime(counters[i]) < getFinishTime(counters[min])) {
                min = i;
            }
        }
        return min;
    }

    /**
     * Obtém o tempo de conclusão do balcão especificado.
     * 
     * @param counter O balcão para obter o tempo de conclusão.
     * @return O tempo de conclusão do balcão.
     */
    private static double getFinishTime(Counter counter) {
        return counter.isEmpty() ? 0 : counter.getTotalProcessingDuration();
    }

    /**
     * Obtém o índice do primeiro balcão a terminar o processamento de um cliente.
     * 
     * @return O índice do primeiro balcão a terminar o processamento de um cliente.
     * @requires {@code !isProcessingFinished()} Todos os balcões não estejam vazios
     * @ensures índice de um balcão
     * 
     */
    public int firstCounterToFinishClient() {
        int min = -1;
        for (int i = 0; i < counters.length; i++) {
            if (!counters[i].isEmpty() && (min == -1 || counters[i].currentClient()
                    .getClientProcessingDuration() < counters[min].currentClient().getClientProcessingDuration())) {
                min = i;
            }
        }
        return min;
    }

    /**
     * Verifica se todos os balcões terminaram de processar todos os seus clientes.
     * 
     * @return True se todos os balcões terminaram de processar todos os clientes,
     *         False caso contrário.
     */
    public boolean isProcessingFinished() {
        for (Counter counter : counters) {
            if (!counter.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Obtém o valor total de vendas desta loja.
     * 
     * @return O valor total de vendas.
     */
    public double getTotalSalesAmount() {
        return totalSalesAmount;
    }
}
