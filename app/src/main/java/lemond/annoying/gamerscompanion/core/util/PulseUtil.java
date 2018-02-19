package lemond.annoying.gamerscompanion.core.util;



import java.util.List;

import lemond.annoying.gamerscompanion.repository.objects.Pulse;

public class PulseUtil {

    /**
     * https://igdb.github.io/api/references/expander/
     * @param pulses list of pulse with id
     * @return comma separated pulseIds  e.g. "11111, 22222, 33333, 44444"
     */
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
