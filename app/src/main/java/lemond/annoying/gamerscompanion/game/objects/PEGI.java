package lemond.annoying.gamerscompanion.game.objects;


public class PEGI {

    public Rating rating;
    public String sypnosis;

    public enum Rating {
        //minimum age
        MIN3(1),
        MIN7(2),
        MIN12(3),
        MIN16(4),
        MIN18(5);

        private final int value;

        Rating(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
