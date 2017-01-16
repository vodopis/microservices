package shopback.api.storeservice.dao

import shopback.api.model.Store

interface StoreDao {

    Store getById(Long id)
    Collection<Store> getAll()
    void delete(Long id)
    void save(Store store)
}
