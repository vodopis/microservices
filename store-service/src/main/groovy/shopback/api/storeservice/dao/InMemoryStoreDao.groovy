package shopback.api.storeservice.dao

import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service
import shopback.api.model.Store

@Service
@Slf4j
class InMemoryStoreDao implements StoreDao {

    static storageMap = [
            1l: new Store(id: 1, name: "Store 1", description: "Description 1", slug: "orange", defaultCashback: "3", image: "/pic1"),
            2l: new Store(id: 2, name: "Store 2", description: "Description 2", slug: "pineapple", defaultCashback: "6", image: "/pic2"),
            3l: new Store(id: 3, name: "Store 3", description: "Description 3", slug: "banana", defaultCashback: "9", image: "/pic3")
    ]

    @Override
    Store getById(Long id) {
        storageMap[id]
    }

    @Override
    Collection<Store> getAll() {
        storageMap.values()
    }

    @Override
    void delete(Long id) {
        storageMap.remove(id)
    }

    @Override
    void save(Store store) {
        storageMap[store.id] = store
    }

}
