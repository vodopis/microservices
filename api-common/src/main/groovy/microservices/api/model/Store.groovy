package microservices.api.model

import com.fasterxml.jackson.annotation.JsonInclude
import groovy.transform.Canonical
import groovy.transform.CompileStatic

@Canonical
@CompileStatic
@JsonInclude(JsonInclude.Include.NON_NULL)
class Store {
    Long id
    String name
    String description
    String slug
    String defaultCashback
    String image
}