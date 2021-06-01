package melihvarilci.hrms.core.utilities.mail;

import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.core.utilities.results.SuccessResult;
import org.springframework.stereotype.Service;

@Service
public class EmailManager implements EmailService {
    @Override
    public Result send(String to, String title, String message) {
        return new SuccessResult("E-posta başarıyla gönderildi.");
    }
}
