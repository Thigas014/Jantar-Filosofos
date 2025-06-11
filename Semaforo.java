public class Semaforo {
    private int permissoes;
    private final Object lock = new Object();

    public Semaforo(int permissoes) {
        this.permissoes = permissoes;
    }

    public void adquirir() throws InterruptedException {
        synchronized (lock) {
            while (permissoes <= 0) {
                lock.wait();
            }
            permissoes--;
        }
    }

    public void liberar() {
        synchronized (lock) {
            permissoes++;
            lock.notify(); // Acorda uma thread esperando
        }
    }
}
