package entite;

public class Carte {

    private int largeur;
    private int hauteur;
    private Case[][] cases;

    public Carte(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.cases = new Case[hauteur][largeur];
        initialiserCases();
    }

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public Case getCase(int x, int y) {
        return cases[y][x];
    }

    private void initialiserCases() {
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                cases[i][j] = new Case();
            }
        }
    }

    public void ajouterMontagne(int x, int y) {
        cases[y][x].setType(TypeCase.MONTAGNE);
    }

    public void ajouterTresor(int x, int y, int nbTresors) {
        cases[y][x].setTresors(nbTresors);
    }

    public boolean estAccessible(int x, int y) {
        return cases[y][x].getType() != TypeCase.MONTAGNE;
    }

    public boolean positionDansCarte(int x, int y) {
        return x >= 0 && x < largeur && y >= 0 && y < hauteur;
    }
}
