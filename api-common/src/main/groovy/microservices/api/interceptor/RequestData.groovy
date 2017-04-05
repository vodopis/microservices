package microservices.api.interceptor

import com.fasterxml.jackson.annotation.JsonInclude
import groovy.transform.CompileStatic
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component
import org.springframework.web.context.WebApplicationContext

@CompileStatic
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
@Scope(value=WebApplicationContext.SCOPE_REQUEST, proxyMode=ScopedProxyMode.TARGET_CLASS)
class RequestData {
    UUID requestId = UUID.randomUUID()
    long startTime = System.currentTimeMillis()
    String method
    String path
    String userAgent
    String referer
    String status
    Long perfTotal
}
