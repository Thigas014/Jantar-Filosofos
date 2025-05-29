// Classe principal que executa a simulação
public class Main {
    public static void main(String[] args) {
        System.out.println("O JANTAR DOS FILÓSOFOS");

        Mesa mesa = new Mesa();  // Cria a mesa com os garfos
        Thread[] threads = new Thread[5];  // Array de threads para os 5 filósofos

        // Cria e inicia os 5 filósofos
        for (int i = 0; i < 5; i++) {
            Filosofo filosofo = new Filosofo(i, mesa);
            threads[i] = filosofo;
            filosofo.start();
        }

        // Espera todos os filósofos terminarem
        for (int i = 0; i < 5; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Todos os filósofos terminaram.");
    }
}
