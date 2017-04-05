package microservices.api.productservice.service

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import microservices.api.model.Product
import microservices.api.model.Store
import microservices.api.productservice.dao.ProductDao

@Service
@Slf4j
class ProductService {

    @Value('${store.service.url}')
    String storeServiceUrl

    @Autowired
    RestTemplate restTemplate

    @Autowired
    ProductDao dao

    // Retrieve store for id via restTemplate
    private Store getStoreById(Long id) {
        Store store = restTemplate.exchange( storeServiceUrl + "/stores/{id}", HttpMethod.GET, null,
                new ParameterizedTypeReference<Store>() { }, id).getBody()

        if (store == null)
            throw new RuntimeException("Failed to lookup store by id: ${id}")

        store
    }

    // Retrieve all stores via restTemplate
    private List<Store> getAllStores() {
        List<Store> stores = restTemplate.exchange( storeServiceUrl + "/stores", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Store>>() { }).getBody()

        stores
    }

    Product getById(Long id) {
        Product product = dao.getById(id)
        if (product == null)
            return null

        // lookup store
        product.store = getStoreById(product.storeId)

        product
    }

    Collection<Product> getAll() {
        Collection<Product> products = dao.getAll()

        // lookup stores
        Collection<Store> stores = getAllStores()
        products.forEach {
            it.store = stores.find { store -> store.id == it.storeId }
        }

        products
    }

    Collection<Product> search(Product searchCriteria) {
        Collection<Product> products = dao.getAll()

        searchCriteria.properties.each  { key, val ->
            if (key in ["metaClass","class"] || val == null) return
            products = products.findAll { p -> p."$key" == val}
        }

        products
    }
}
