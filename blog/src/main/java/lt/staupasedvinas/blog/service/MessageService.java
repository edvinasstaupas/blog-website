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

    public String commentTimeFormatter(long diff, String timeUnit) {
        timeUnit = "comment.ago." + timeUnit;
        String commentTimeFormatted = "";
        if (LocaleContextHolder.getLocale().equals(Locale.ENGLISH)) {
            timeUnit = formatENUnitMessage(timeUnit, diff);
            commentTimeFormatted = getMessage("post.comments.commented") + " " + diff + " " + getMessage(timeUnit) + " " + getMessage("comment.ago");
        } else {
            timeUnit = formatLTUnitMessage(timeUnit, diff);
            commentTimeFormatted = getMessage("post.comments.commented") + " " + getMessage("comment.ago") + " " + diff + " " + getMessage(timeUnit);
        }
        return commentTimeFormatted;
    }

    private String formatENUnitMessage(String timeUnit, long diff) {
        if (diff != 1) {
            timeUnit += "s";
        }
        return timeUnit;
    }

    private String formatLTUnitMessage(String timeUnit, long diff) {
        if (diff > 10 && diff < 20) {
            timeUnit += "0";
            return timeUnit;
        }
        if (diff % 10 == 1L) {
            timeUnit += "2";
        } else if (diff % 10 == 2L || diff % 10 == 3L || diff % 10 == 4L || diff % 10 == 5L || diff % 10 == 6L || diff % 10 == 7L || diff % 10 == 8L || diff % 10 == 9L) {
            timeUnit += "1";
        } else {
            timeUnit += "0";
        }
        return timeUnit;
    }
}
