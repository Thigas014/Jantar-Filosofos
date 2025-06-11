public class Filosofo extends Thread {
    private int idFilosofo;
    private Mesa mesa;
    private int maxIteracoes = 5;

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

    private void pensar() {
        System.out.println("Filósofo " + idFilosofo + " pensando...");
        try {
            Thread.sleep((int) (Math.random() * 5000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void comer() {
        int garfoEsquerdo = idFilosofo;
        int garfoDireito = (idFilosofo + 1) % 5;

        System.out.println("Filósofo " + idFilosofo + " tentando pegar permissão para comer...");

        try {
            mesa.semaforoGlobal.adquirir();

            System.out.println("Filósofo " + idFilosofo + " tentando pegar os garfos...");

            pegarGarfo(garfoEsquerdo);
            pegarGarfo(garfoDireito);

            System.out.println("Filósofo " + idFilosofo + " comendo...");
            Thread.sleep((int) (Math.random() * 10000));

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("Filósofo " + idFilosofo + " liberando os garfos...");
            liberarGarfo(garfoEsquerdo);
            liberarGarfo(garfoDireito);
            mesa.semaforoGlobal.liberar();
        }
    }

    private void pegarGarfo(int indice) throws InterruptedException {
        mesa.garfos[indice].adquirir();
    }

    private void liberarGarfo(int indice) {
        mesa.garfos[indice].liberar();
    }
}
