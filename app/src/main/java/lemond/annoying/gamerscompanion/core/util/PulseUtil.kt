package lemond.annoying.gamerscompanion.core.util


import lemond.annoying.gamerscompanion.repository.objects.Pulse

object PulseUtil {

    /**
     * https://igdb.github.io/api/references/expander/
     * @param pulses list of pulse with id
     * @return comma separated pulseIds  e.g. "11111, 22222, 33333, 44444"
     */
    fun createGameIdsPath(pulses: List<Pulse>): String {
        val commaSeparatedIds = ""
        val stringBuilder = StringBuilder(commaSeparatedIds)
        for (i in pulses.indices) {
            stringBuilder.append(pulses[i].id)
            if (i < pulses.size - 1) {
                stringBuilder.append(",")
            }
        }
        return stringBuilder.toString()
    }

}
