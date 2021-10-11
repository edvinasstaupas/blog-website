package lt.staupasedvinas.blog.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.model.Comment;
import lt.staupasedvinas.blog.model.Post;
import lt.staupasedvinas.blog.service.entity.MessageService;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class DateMessageService {

    private final MessageService messageService;

    public String formatPostDateMessage(Post post) {
        return formatComment(post.getPostDate(), messageService.getMessage("post.posted"));
    }

    public String formatCommentDateMessage(Comment comment) {
        return formatComment(comment.getPostDate(), messageService.getMessage("comment.commented"));
    }

    private String formatComment(Date date, String entityMessage) {
        DateMessageData dateMessageData = getDateDiff(date);
        long diff = dateMessageData.diff;
        String timeUnit = "ago." + dateMessageData.timeUnit;
        String commentTimeFormatted = entityMessage;
        if (LocaleContextHolder.getLocale().equals(Locale.ENGLISH)) {
            timeUnit = formatENUnitMessage(timeUnit, diff);
            commentTimeFormatted += " " + diff + " " + messageService.getMessage(timeUnit) + " " + messageService.getMessage("ago");
        } else {
            timeUnit = formatLTUnitMessage(timeUnit, diff);
            commentTimeFormatted += " " + messageService.getMessage("ago") + " " + diff + " " + messageService.getMessage(timeUnit);
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


    private DateMessageData getDateDiff(Date date) {
        long diffInMillis = new Date().getTime() - date.getTime();
        long diffInMinutes = diffInMillis / 60000;
        long diff;
        String timeUnit;
        if (diffInMinutes < 60) { //smaller than one hours
            diff = TimeUnit.MILLISECONDS.toMinutes(diffInMillis);
            timeUnit = "minute";
        } else if (diffInMinutes >= 60 && diffInMinutes < 1440) { //more than one hour
            diff = TimeUnit.MILLISECONDS.toHours(diffInMillis);
            timeUnit = "hour";
        } else if (diffInMinutes >= 1400 && diffInMinutes < 43829) { //more than one day
            diff = TimeUnit.MILLISECONDS.toDays(diffInMillis);
            timeUnit = "day";
        } else if (diffInMinutes >= 43829 && diffInMinutes < 525948) { //more than one month
            diff = TimeUnit.MILLISECONDS.toDays(diffInMillis) / 30;
            timeUnit = "month";
        } else { //more than one year
            diff = TimeUnit.MILLISECONDS.toDays(diffInMillis) / 365;
            timeUnit = "year";
        }
        return new DateMessageData(diff, timeUnit);
    }

    @AllArgsConstructor
    private class DateMessageData {

        long diff;

        String timeUnit;
    }
}
