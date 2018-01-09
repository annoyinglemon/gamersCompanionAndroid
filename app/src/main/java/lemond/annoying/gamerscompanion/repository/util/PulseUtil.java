package lemond.annoying.gamerscompanion.repository.util;



import java.util.List;

import lemond.annoying.gamerscompanion.repository.objects.Pulse;

public class PulseUtil {

    public static String createGameIdsPath(List<Pulse> pulses) {
        String commaSeparatedIds = "";
        StringBuilder stringBuilder = new StringBuilder(commaSeparatedIds);
        for (int i = 0; i < pulses.size(); i++) {
            stringBuilder.append(pulses.get(i).id);
            if (i <(pulses.size() - 1)) {
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }

}
