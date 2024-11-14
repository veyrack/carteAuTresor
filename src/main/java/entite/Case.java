package entite;

public class Case {

    private TypeCase type;
    private int nbTresors;

    public Case() {
        this.type = TypeCase.PLAINE;
        this.nbTresors = 0;
    }

    public TypeCase getType() {
        return type;
    }

    public void setType(TypeCase type) {
        this.type = type;
    }

    public int getNbTresors() {
        return nbTresors;
    }

    public void setTresors(int nbTresors) {
        this.nbTresors += nbTresors;
    }

    public void collecterTresor() {
        if (nbTresors > 0) {
            nbTresors--;
        }
    }
}
