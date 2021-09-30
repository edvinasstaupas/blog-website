package lt.staupasedvinas.blog.filter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class TimeElapsedFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // do something before (logic)
        log.trace("--------- TimeElapsedFilter started ----------");

        var stopWatch = new StopWatch();
        stopWatch.start();

        // call rest of the chain
        chain.doFilter(request, response);

        // do something after (logic)
        stopWatch.stop();
        log.trace("--------- TimeElapsedFilter time was: {} ms ----------", stopWatch.getLastTaskTimeMillis());
    }
}