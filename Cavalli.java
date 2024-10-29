package JavaCavalli;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Cavallo extends Thread {
    private String nome;
    private Random random = new Random();
    private int percorso;
    private int posizione = 0;
    public Cavallo(String nome, int percorso) {
        this.nome = nome;
        this.percorso = percorso;
    }

    @Override
    public void run() {
        while (posizione < percorso) {
            posizione += random.nextInt(7) + 1;
            if (posizione > percorso) posizione = percorso;
            System.out.println(nome + " ha corso" + posizione + " metri su " + percorso);
            try {
                Thread.sleep(777);
            } catch (InterruptedException e) {
                e.printStackTrace(); 
            }
        }
        System.out.println(nome + " ha completato la gara!");
    }
}




public class GaraCavalli {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci la lunghezza del percorso della gara (in metri): ");
        int percorso = scanner.nextInt();
        
        System.out.print("Inserisci il numero di cavalli partecipanti: ");
        int numCavalli = scanner.nextInt();

        List<Cavallo> cavalli = new ArrayList<>();
        for (int i = 0; i < numCavalli; i++) {
            System.out.print("Inserisci il nome del cavallo " + (i) + ": ");
            String nome = scanner.nextLine();
            cavalli.add(new Cavallo(nome, percorso));
        }

        cavalli.forEach(Thread::start);
        cavalli.forEach(cavallo -> {
            try {
                cavallo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("La gara è terminata!");
    }
}