package lemond.annoying.gamerscompanion.repository.objects;


public enum GameStatus {
    RELEASED(0),
    ALPHA(1),
    BETA(2),
    EARLY_ACCESS(3),
    OFFLINE(4),
    CANCELLED(5);

    private final int value;

    GameStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}