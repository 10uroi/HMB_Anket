package tr.gov.hmb.survey.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
@RequiredArgsConstructor
public class LocaleMessage {

    private final MessageSource messageSource;

    public String getMessage(String key) {
        return messageSource.getMessage(key, null, Locale.getDefault());
    }

}
