package microservices.api.model

import com.fasterxml.jackson.annotation.JsonInclude
import groovy.transform.Canonical
import groovy.transform.CompileStatic

@Canonical
@CompileStatic
@JsonInclude(JsonInclude.Include.NON_NULL)
class Product {
    Long id
    String name
    String description
    String slug
    Float price
    String image
    Long storeId
    Collection<String> categories
    Store store
}