// Classe que representa a Mesa com os garfos (controlados por semáforos)
public class Mesa {
    public Semaforo[] garfos = new Semaforo[5];  // Array de semáforos para os 5 garfos

    public Mesa() {
        // Inicializa os semáforos para os garfos (um semáforo por garfo)
        for (int i = 0; i < 5; i++) {
            garfos[i] = new Semaforo(1);  // Cada garfo tem uma permissão (só pode ser usado por um filósofo por vez)
        }
    }
}
