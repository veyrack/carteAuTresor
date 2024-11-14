package elements;

/**
 * Directions dans l'ordre horaire
 */
public enum Direction {
    N(0, -1),
    E(1, 0),
    S(0, 1),
    O(-1, 0);

    private int directionX;
    private int directionY;

    Direction(int directionX, int directionY) {
        this.directionX = directionX;
        this.directionY = directionY;
    }

    public Direction gauche() {
        return Direction.values()[Math.floorMod(this.ordinal() - 1, 4)];
    }

    public Direction droite() {
        return Direction.values()[Math.floorMod(this.ordinal() + 1, 4)];
    }
}
