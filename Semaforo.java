// Implementação de um semáforo manual
public class Semaforo {
    private int permissoes;  // Número de permissões disponíveis
    private final Object lock = new Object();  // Objeto para sincronização

    public Semaforo(int permissoes) {
        this.permissoes = permissoes;  // Inicializa com o número de permissões
    }

    // Método para adquirir o semáforo (reduz a permissão)
    public void adquirir() throws InterruptedException {
        synchronized (lock) {
            while (permissoes <= 0) {
                lock.wait();  // Espera até que haja permissão disponível
            }
            permissoes--;  // Reduz a permissão
        }
    }

    // Método para liberar o semáforo (aumenta a permissão)
    public void liberar() {
        synchronized (lock) {
            permissoes++;  // Aumenta a permissão
            lock.notify();  // Notifica uma thread esperando
        }
    }
}
