package lt.staupasedvinas.blog.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {

    private final MessageSource messageSource;

    public String getMessage(String key) {
        try {
            return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            log.error("Key " + key + " does not have a message!");
            e.printStackTrace();
        }
        return "";
    }

    public String commentTimeFormatter(long diff, String timeUnit, boolean one) {
        String commentTimeFormatted = "";
        timeUnit = "comment.ago." + timeUnit;
        if (!one) {
            timeUnit += "s";
        }
        if (LocaleContextHolder.getLocale().equals(Locale.ENGLISH)) {
            commentTimeFormatted = getMessage("post.comments.commented") + " " + diff + " " + getMessage(timeUnit) + " " + getMessage("comment.ago");
        } else {
            commentTimeFormatted = getMessage("post.comments.commented") + " " + getMessage("comment.ago") + " " + diff + " " + getMessage(timeUnit);
        }
        return commentTimeFormatted;
    }

}
