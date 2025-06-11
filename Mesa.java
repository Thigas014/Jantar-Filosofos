public class Mesa {
    public Semaforo[] garfos = new Semaforo[5];
    public Semaforo semaforoGlobal = new Semaforo(4);

    public Mesa() {
        for (int i = 0; i < 5; i++) {
            garfos[i] = new Semaforo(1);
        }
    }
}
