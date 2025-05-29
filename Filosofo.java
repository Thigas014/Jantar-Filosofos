// Classe que representa um Filósofo
public class Filosofo extends Thread {
    private int idFilosofo;  // Identificador do filósofo
    private Mesa mesa;  // Referência à mesa (com os garfos)
    private int maxIteracoes = 5;  // Número máximo de iterações de pensar e comer

    public Filosofo(int id, Mesa mesa) {
        this.idFilosofo = id;
        this.mesa = mesa;
    }

    @Override
    public void run() {
        int iteracoes = 0;
        while (iteracoes < maxIteracoes) {
            pensar();
            comer();
            iteracoes++;
        }
        System.out.println("Filósofo " + idFilosofo + " terminou de jantar.");
    }

    // Processo de pensar (simulado com uma espera)
    private void pensar() {
        System.out.println("Filósofo " + idFilosofo + " pensando...");
        try {
            Thread.sleep((int)(Math.random() * 5000));  // Dorme por um tempo aleatório simulando o ato de pensar
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Processo de comer (o filósofo tenta pegar dois garfos)
    private void comer() {
        int garfoEsquerdo = idFilosofo;
        int garfoDireito = (idFilosofo + 1) % 5;

        System.out.println("Filósofo " + idFilosofo + " tentando pegar os garfos...");

        try {
            // O filósofo adquire os semáforos dos garfos (esquerdo e direito)
            pegarGarfo(garfoEsquerdo);
            pegarGarfo(garfoDireito);

            System.out.println("Filósofo " + idFilosofo + " comendo...");
            Thread.sleep((int)(Math.random() * 10000));  // Dorme por um tempo aleatório simulando o ato de comer

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // Libera os semáforos dos garfos
            System.out.println("Filósofo " + idFilosofo + " liberando os garfos...");
            liberarGarfo(garfoEsquerdo);
            liberarGarfo(garfoDireito);
        }
    }

    // Método para adquirir o semáforo de um garfo
    private void pegarGarfo(int indice) throws InterruptedException {
        mesa.garfos[indice].adquirir();
    }

    // Método para liberar o semáforo de um garfo
    private void liberarGarfo(int indice) {
        mesa.garfos[indice].liberar();
    }
}
