package org.codejudge.sb.db.service.api;

import org.codejudge.sb.error.CustomException;

public interface DatabaseService {
    public void resetDB() throws CustomException;
}
