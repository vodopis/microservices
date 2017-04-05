package microservices.api.storeservice.dao

import microservices.api.model.Store

interface StoreDao {

    Store getById(Long id)
    Collection<Store> getAll()
    void delete(Long id)
    void save(Store store)
}
