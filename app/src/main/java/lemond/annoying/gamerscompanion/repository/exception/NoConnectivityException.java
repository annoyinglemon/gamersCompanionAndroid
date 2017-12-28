package lemond.annoying.gamerscompanion.repository.exception;

import java.io.IOException;



public class NoConnectivityException extends IOException {

    @Override
    public String getMessage() {
        return "No internet connection!";
    }

}
