package microservices.api.productservice.dao

import microservices.api.model.Product

interface ProductDao {
    Product getById(Long id)
    Collection<Product> getAll()
    void delete(Long id)
    void save(Product product)
}

