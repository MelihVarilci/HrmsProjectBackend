package melihvarilci.hrms.core.utilities.mail;

import melihvarilci.hrms.core.utilities.results.Result;

public interface EmailService {
    Result send(String to, String title, String message);
}
