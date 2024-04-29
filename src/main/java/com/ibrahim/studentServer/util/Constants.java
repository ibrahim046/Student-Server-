package com.ibrahim.studentServer.util;

public class Constants {

    public static final short STATE_ACTIVATED = 0, STATE_DELETED = 2, STATE_ARCHIVE = 1, STATE_DEACTIVATED = 3;

	// Codes d'erreurs
	public static final int USER_NON_AUTHENTICATED = 401, CONNECTION_TIMEOUT = 403, ERROR_PAGE_NOT_FOUND = 404,
			SERVER_ERROR = 500, SERVER_DENY_RESPONSE = 504;

}
