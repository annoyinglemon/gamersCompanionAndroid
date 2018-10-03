package lemond.annoying.gamerscompanion.core.exception

import java.io.IOException


class NoConnectivityException : IOException() {

    override val message: String?
        get() = "No internet connection!"

}
