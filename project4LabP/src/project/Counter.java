package project;

import java.util.Locale;

/**
 * Esta class Counter simula um balcão de atendimento, ou uma caixa de
 * supermercado, contendo uma fila de clientes à espera de serem atendidos.
 * 
 * @author Rodrigo Frutuoso 61865
 */
public class Counter {

    private final int counterId;
    private int currentTime;
    private double salesAmount;
    private int totalProcessingDuration;
    private Queue<Client> clientQueue;
    private StringBuilder log;
    public static final String EOL = System.lineSeparator();

    /**
     * Constrói um novo objeto Counter com o identificador do balcão, o tempo atual
     * e um StringBuilder para o registro de atividades.
     * 
     * @param counterId   o identificador único do balcão
     * @param currentTime o tempo atual do balcão
     * @param sb          o StringBuilder para registro de atividades
     */
    public Counter(int counterId, int currentTime, StringBuilder sb) {
        this.counterId = counterId;
        this.currentTime = currentTime;
        this.salesAmount = 0.0;
        this.totalProcessingDuration = 0;
        this.clientQueue = new ArrayQueue<>();
        this.log = sb;
        log.append("[TS " + currentTime + "] Counter " + counterId + " open." + EOL);
    }

    /**
     * Adiciona o cliente client a este balcão, colocando-o no fim da fila de
     * clientes deste balcão.
     * 
     * 
     * @param client o cliente a ser adicionado à fila
     */
    public void addClient(Client client) {
        clientQueue.enqueue(client);
        totalProcessingDuration += client.getClientProcessingDuration();
        log.append("[TS " + client.getArrivalTime() + "] Client " + client.getClientCode() + " assigned to counter "
                + counterId + ", processing will take " + client.getClientProcessingDuration() + "." + EOL);
    }

    /**
     * Devolve o cliente no início da fila deste balcão.
     * 
     * @return o cliente no início da fila
     * @requires {@code !isEmpty()} A fila não esteja vazia
     */
    public Client currentClient() {
        return clientQueue.front();
    }

    /**
     * Remove o cliente que está sendo atendido pelo balcão.
     * 
     * @requires {@code !isEmpty()} A fila não esteja vazia
     */
    public void removeClient() {
        Client client = currentClient();
        salesAmount += calculateSales(client);
        log.append("[TS " + currentTime + "] Client " + client.getClientCode()
                + " has finished processing. Total wait time: " + (currentTime - client.getArrivalTime())
                + ". Payment: " + String.format(Locale.US, "%.2f", calculateSales(client)) + "€." + EOL);
        clientQueue.dequeue();
    }

    /**
     * Verifica se a fila do balcão está vazia.
     * 
     * @return true se a fila está vazia, caso contrário false
     */
    public boolean isEmpty() {
        return clientQueue.size() == 0;
    }

    /**
     * Processa a fila durante um determinado período de tempo.
     * 
     * @param duration a duração do processamento
     * @requires {@code duration > 0} A duração é maior que zero.
     * 
     */
    public void processQueueForDuration(int duration) {
        currentTime += duration;
        while (!isEmpty() && duration > 0) {
            Client client = currentClient();
            if (client.getClientProcessingDuration() <= duration) {
                duration -= client.getClientProcessingDuration();
                totalProcessingDuration -= client.getClientProcessingDuration();
                removeClient();
            } 
            else {
                client.setClientProcessingDuration(client.getClientProcessingDuration() - duration);
                totalProcessingDuration -= duration;
                duration = 0;
            }
        }
    }

    /**
     * Processa a fila até um determinado momento de tempo.
     * 
     * @param time o momento de tempo
     * @requires {@code time > 0} O time é maior que zero.
     * 
     */
    public void processQueueUntilTime(int time) {
        processQueueForDuration(time - currentTime);
    }

    /**
     * Devolve o código deste balcão
     * 
     * @return o código deste balcão
     */
    public int getCounterId() {
        return counterId;
    }

    /**
     * Devolve o tempo atual deste balcão
     * 
     * @return o tempo atual deste balcão
     */
    public int getCurrentTime() {
        return currentTime;
    }

    /**
     * Devolve o valor total de vendas deste balcão
     * 
     * @return o valor total de vendas deste balcão
     */
    public double getSalesAmount() {
        return salesAmount;
    }

    /**
     * Devolve a duração total de processamento do balcão.
     * 
     * @return a duração total de processamento do balcão
     */
    public int getTotalProcessingDuration() {
        return totalProcessingDuration;
    }

    /**
     * Calcula o valor total das compras de um cliente.
     * 
     * @param client o cliente
     * @return o valor total das compras do cliente
     */
    private static double calculateSales(Client client) {
        double total = 0.0;
        for (Product product : client.getShoppingCart()) {
            total += product.getProdPrice();
        }
        return total;
    }
}