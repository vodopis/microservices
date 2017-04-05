package microservices.api.storeservice.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import microservices.api.model.Store
import microservices.api.storeservice.dao.StoreDao

@Service
class StoreService {

    @Autowired
    StoreDao dao

    Collection<Store> search(Store searchCriteria) {
        Collection<Store> stores = dao.getAll()

        searchCriteria.properties.each  { key, val ->
            if (key in ["metaClass","class"] || val == null) return
            stores = stores.findAll { p -> p."$key" == val}
        }

        stores
    }
}
