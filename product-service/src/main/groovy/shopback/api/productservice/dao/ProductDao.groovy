package shopback.api.productservice.dao

import shopback.api.model.Product

interface ProductDao {
    Product getById(Long id)
    Collection<Product> getAll()
    void delete(Long id)
    void save(Product product)
}

