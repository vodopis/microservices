package microservices.api.productservice.dao

import org.springframework.stereotype.Component
import microservices.api.model.Product

@Component
class InMemoryProductDao implements ProductDao {

    static storageMap = [
            100l: new Product(id: 100, name: "Product 100", storeId: 1,  description: "Description", slug: "toyota"),
            101l: new Product(id: 101, name: "Product 101", storeId: 1,  description: "Description", slug: "honda"),
            102l: new Product(id: 102, name: "Product 102", storeId: 3,  description: "Description", slug: "suzuki")
    ]

    @Override
    Product getById(Long id) {
        storageMap[id]
    }

    @Override
    Collection<Product> getAll() {
        storageMap.values()
    }

    @Override
    void delete(Long id) {
        storageMap.remove(id)
    }

    @Override
    void save(Product product) {
        storageMap[product.id] = product
    }
}
