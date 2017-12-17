package lemond.annoying.gamerscompanion.game.objects;


public class ESRB {

    public Rating rating;
    public String synopsis;

    public enum Rating {
        RP(1),
        EC(2),
        E(3),
        E10PLUS(4),
        T(5),
        M(6),
        AO(7);

        private final int value;

        Rating(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
