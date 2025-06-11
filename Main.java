public class Main {
    public static void main(String[] args) {
        System.out.println("O JANTAR DOS FILÓSOFOS");

        Mesa mesa = new Mesa();
        Thread[] threads = new Thread[5];

        for (int i = 0; i < 5; i++) {
            Filosofo filosofo = new Filosofo(i, mesa);
            threads[i] = filosofo;
            filosofo.start();
        }

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
