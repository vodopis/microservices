package microservices.api.productservice.controller

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import microservices.api.model.Product
import microservices.api.productservice.dao.ProductDao
import microservices.api.productservice.service.ProductService

@CompileStatic
@RestController
@RequestMapping(value = "/products")
@Slf4j
class ProductController {

    @Autowired
    ProductService service

    @Autowired
    ProductDao dao

    @RequestMapping(value = "{id}", method=RequestMethod.GET)
    ResponseEntity<Product> getById(@PathVariable Long id) {

        Product product = service.getById(id)
        if (product == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND)

        new ResponseEntity(product, HttpStatus.OK)
    }

    @RequestMapping(method=RequestMethod.GET)
    Collection<Product> getAll() {
        service.getAll()
    }

    @RequestMapping(value = "{id}", method=RequestMethod.DELETE)
    ResponseEntity<Map> delete(@PathVariable Long id) {
        dao.delete(id)
        new ResponseEntity(HttpStatus.OK)
    }

    @RequestMapping(value = "{id}", method=RequestMethod.PUT)
    ResponseEntity<Map> update(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Product existingProduct = dao.getById(id)
        if (existingProduct == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND)

        updatedProduct.id = id
        dao.save(updatedProduct)

        new ResponseEntity(HttpStatus.OK)
    }

    @RequestMapping(method=RequestMethod.POST)
    ResponseEntity<Map> create(@RequestBody Product newProduct) {
        Product existingProduct = dao.getById(newProduct.id)
        if (existingProduct != null)
            return new ResponseEntity(HttpStatus.CONFLICT)

        dao.save(newProduct)

        new ResponseEntity(HttpStatus.CREATED)
    }

    @RequestMapping(value = "search", method=RequestMethod.POST)
    Collection<Product> search(@RequestBody Product searchCriteria) {
        service.search(searchCriteria)
    }
}
