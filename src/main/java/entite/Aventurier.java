package entite;

import elements.Direction;
import elements.Position;

import java.io.IOException;

public class Aventurier {

    private final String nom;
    private int x;
    private int y;
    private Direction direction;
    private final String sequenceMouvements;
    private int tresorsCollectes;
    private int indexMouvement;

    public Aventurier(String nom, int x, int y, Direction direction, String sequenceMouvements) {
        this.nom = nom;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.sequenceMouvements = sequenceMouvements;
        this.tresorsCollectes = 0;
    }

    public String getNom() {
        return nom;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getTresorsCollectes() {
        return tresorsCollectes;
    }

    public void tournerGauche() {
        direction = direction.gauche();
    }

    public void tournerDroite() {
        direction = direction.droite();
    }

    public boolean resteMouvements() {
        return indexMouvement < sequenceMouvements.length();
    }

    public Position calculerProchainePosition(Carte carte) throws IOException {
        if (!resteMouvements()) return new Position(x, y);

        char mouvement = sequenceMouvements.charAt(indexMouvement);
        switch (mouvement) {
            case 'A':
                return calculerAvancement(carte);
            case 'G':
                tournerGauche();
                break;
            case 'D':
                tournerDroite();
                break;
            default:
                throw new IOException("Le mouvement " + mouvement + " n'est pas reconnu");
        }
        return new Position(x, y);
    }

    public Position calculerAvancement(Carte carte) {
        int nx = x, ny = y;
        switch (direction) {
            case N: ny -= 1; break;
            case S: ny += 1; break;
            case E: nx += 1; break;
            case O: nx -= 1; break;
        }
        if (carte.positionDansCarte(nx, ny) && carte.estAccessible(nx, ny)) {
            return new Position(nx, ny);
        }
        return new Position(x, y);
    }

    public void deplacer(Position nouvellePosition, Carte carte) {
        boolean deplacer = nouvellePosition.getX() != x || nouvellePosition.getY() != y;
        this.x = nouvellePosition.getX();
        this.y = nouvellePosition.getY();
        if (deplacer) {
            collecterTresor(carte);
        }
        indexMouvement++;
    }

    public void collecterTresor(Carte carte) {
        Case c = carte.getCase(x, y);
        if (c.getNbTresors() > 0) {
            tresorsCollectes++;
            c.collecterTresor();
        }
    }
}
