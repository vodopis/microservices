package shopback.api.interceptor

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.aop.framework.Advised
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@CompileStatic
@Component
@Slf4j
class LoggingInterceptor implements HandlerInterceptor {

    @Autowired
    RequestData requestData

    @Autowired
    ObjectMapper objectMapper

    @Override
    boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        requestData.with {
            method = request.getMethod()
            path = request.getRequestURI()
            userAgent = request.getHeader("User-Agent")
            referer = request.getHeader("Referer")
        }

        true
    }

    @Override
    void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        requestData.with {
            status = response.status
            perfTotal = System.currentTimeMillis() - startTime

        }

        try {
            def target = ((Advised) requestData).getTargetSource().getTarget()
            String logMsg = objectMapper.writeValueAsString(target)
            log.info(logMsg)
        }
        catch (Exception e) {
            log.error("Error serializing log to JSON", e)
        }
    }
}
