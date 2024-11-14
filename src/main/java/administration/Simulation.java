package administration;

import entite.Aventurier;
import entite.Carte;
import elements.Direction;
import elements.Position;
import entite.TypeCase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simulation {
    private Carte carte;
    private List<Aventurier> aventuriers;

    public Simulation() {
        aventuriers = new ArrayList<>();
    }

    public Simulation(Carte carte, List<Aventurier> aventuriers) {
        this.carte = carte;
        this.aventuriers = aventuriers;
    }

    public void demarrer() throws IOException {
        boolean mouvementRestant;

        do {
            mouvementRestant = false;
            Map<Position, Aventurier> mouvementsDemandes = new HashMap<>();

            //On recupere les mouvements possibles
            for (Aventurier aventurier : aventuriers) {
                if (aventurier.resteMouvements()) {
                    mouvementRestant = true;
                    Position positionSouhaitee = aventurier.calculerProchainePosition(carte);

                    if (!mouvementsDemandes.containsKey(positionSouhaitee)
                        && carte.estAccessible(positionSouhaitee.getX(), positionSouhaitee.getY())) {
                        mouvementsDemandes.put(positionSouhaitee, aventurier);
                    }
                }
            }

            //On effectue les mouvements
            for (Map.Entry<Position, Aventurier> mouvement : mouvementsDemandes.entrySet()) {
                Aventurier aventurier = mouvement.getValue();
                Position nouvellePosition = mouvement.getKey();
                aventurier.deplacer(nouvellePosition, carte);
            }
        } while (mouvementRestant);
    }

    public void lireFichierEntree(String cheminFichier) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(cheminFichier));
        String ligne;

        while ((ligne = br.readLine()) != null) {
            // On ignore les commentaires
            if (ligne.startsWith("#")) continue;

            // On recupere les lignes
            String[] elements = ligne.split(" - ");
            switch (elements[0]) {
                case "C":
                    carte = new Carte(Integer.parseInt(elements[1]), Integer.parseInt(elements[2]));
                    break;
                case "M":
                    carte.ajouterMontagne(Integer.parseInt(elements[1]), Integer.parseInt(elements[2]));
                    break;
                case "T":
                    carte.ajouterTresor(Integer.parseInt(elements[1]),
                        Integer.parseInt(elements[2]),
                        Integer.parseInt(elements[3]));
                    break;
                case "A":
                    aventuriers.add(new Aventurier(elements[1],
                        Integer.parseInt(elements[2]),
                        Integer.parseInt(elements[3]),
                        Direction.valueOf(elements[4]),
                        elements[5]));
                    break;
                default:
                    br.close();
                    throw new IOException("La case " + elements[0] + " n'est pas reconnu");
            }
        }
        br.close();
    }

    public void ecrireFichierSortie(String cheminFichier) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(cheminFichier));

        // Informations de la carte
        bw.write(String.format("C - %d - %d%n", carte.getLargeur(), carte.getHauteur()));

        // Informations sur les montagnes
        for (int y = 0; y < carte.getHauteur(); y++) {
            for (int x = 0; x < carte.getLargeur(); x++) {
                if (carte.getCase(x, y).getType() == TypeCase.MONTAGNE) {
                    bw.write(String.format("M - %d - %d%n", x, y));
                }
            }
        }

        // Informations sur les trésors qui restent
        for (int y = 0; y < carte.getHauteur(); y++) {
            for (int x = 0; x < carte.getLargeur(); x++) {
                int tresorsRestants = carte.getCase(x, y).getNbTresors();
                if (tresorsRestants > 0) {
                    bw.write(String.format("T - %d - %d - %d%n", x, y, tresorsRestants));
                }
            }
        }

        // Informations sur les aventuriers
        for (Aventurier aventurier : aventuriers) {
            bw.write(String.format("A - %s - %d - %d - %s - %d%n",
                aventurier.getNom(),
                aventurier.getX(),
                aventurier.getY(),
                aventurier.getDirection(),
                aventurier.getTresorsCollectes()));
        }
        bw.close();
    }
}
