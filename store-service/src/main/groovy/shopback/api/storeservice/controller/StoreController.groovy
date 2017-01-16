package shopback.api.storeservice.controller

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import shopback.api.model.Store
import shopback.api.storeservice.dao.StoreDao
import shopback.api.storeservice.service.StoreService

@CompileStatic
@RestController
@RequestMapping(value = "/stores")
@Slf4j
class StoreController {

    @Autowired
    StoreService service

    @Autowired
    StoreDao dao

    @RequestMapping(value = "{id}", method=RequestMethod.GET)
    ResponseEntity<Store> getById(@PathVariable Long id) {
        Store store = dao.getById(id)
        if (store == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND)

        new ResponseEntity(store, HttpStatus.OK)
    }

    @RequestMapping(method=RequestMethod.GET)
    Collection<Store> getAll() {
        dao.getAll()
    }

    @RequestMapping(value = "{id}", method=RequestMethod.DELETE)
    ResponseEntity<Map> delete(@PathVariable Long id) {
        dao.delete(id)
        new ResponseEntity(HttpStatus.OK)
    }

    @RequestMapping(value = "{id}", method=RequestMethod.PUT)
    ResponseEntity<Map> update(@PathVariable Long id, @RequestBody Store updatedStore) {
        Store existingStore = dao.getById(id)
        if (existingStore == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND)

        updatedStore.id = id
        dao.save(updatedStore)

        new ResponseEntity(HttpStatus.OK)
    }

    @RequestMapping(method=RequestMethod.POST)
    ResponseEntity<Map> create(@RequestBody Store newStore) {
        Store existingStore = dao.getById(newStore.id)
        if (existingStore != null)
            return new ResponseEntity(HttpStatus.CONFLICT)

        dao.save(newStore)

        new ResponseEntity(HttpStatus.CREATED)
    }

    @RequestMapping(value = "search", method=RequestMethod.POST)
    Collection<Store> search(@RequestBody Store searchCriteria) {
        service.search(searchCriteria)
    }
}
