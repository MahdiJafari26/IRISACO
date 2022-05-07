package com.irisaco.Jafari_Mahdi;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

// *** NOTE: This project is dependent on an external MySQL database
//This is the main CONTROLLER: Products & Customers & Carts
@RestController
@RequestMapping(value = "/api", produces = "application/json")
public class Controller {

    final ProductsRepository productRepository;
    final CustomersRepository customersRepository;
    final CartsRepository cartsRepository;
    final HttpServletResponse servletResponse;
    final PasswordEncoder passwordEncoder;

    //---------- Replaced by IDE, instead of @AUTOWIRED (Field injection)
    public Controller(ProductsRepository productRepository,
                      CustomersRepository customersRepository,
                      CartsRepository cartsRepository,
                      HttpServletResponse servletResponse,
                      PasswordEncoder passwordEncoder) {

        this.productRepository = productRepository;
        this.customersRepository = customersRepository;
        this.cartsRepository = cartsRepository;
        this.servletResponse = servletResponse;
        this.passwordEncoder = passwordEncoder;
    }


    //region API's of products

    /**
     * URL : api/products/...
     */
    @GetMapping(value = "/products")
    @ResponseBody
    public HashMap<String, Iterable<Products>> show_all_products() {
        try {
            HashMap<String, Iterable<Products>> map = new HashMap<>();
            map.put("products", productRepository.findAll());
            servletResponse.setStatus(HttpServletResponse.SC_OK);
            if (map.get("products").iterator().hasNext())
                return map;
            throw new Exception();
        } catch (Exception exception) {
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            System.err.println(exception.getMessage());
        }
        return null;
    }

    @PostMapping(value = "/products")
    @ResponseBody
    public Products add_new_product(@RequestBody Products newProduct) {
        try {
            servletResponse.setStatus(HttpServletResponse.SC_CREATED);
            Products returner = productRepository.save(newProduct);
            if (returner.getId() > 0)
                return returner;
            throw new Exception();
        } catch (Exception exception) {
            servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            System.err.println(exception.getMessage());
        }
        return null;
    }

    @GetMapping(value = "/products/{id}")
    @ResponseBody
    public Products show_product_by_id(@PathVariable int id) {
        try {
            servletResponse.setStatus(HttpServletResponse.SC_OK);
            Products returner = productRepository.findById(id);
            if (returner.getId() > 0)
                return returner;
            throw new Exception();
        } catch (Exception exception) {
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            System.err.println(exception.getMessage());
        }
        return null;
    }

    @PutMapping(value = "/products/{id}")
    @ResponseBody
    public Products change_product_by_id(@RequestBody Products newProduct, @PathVariable int id) {
        try {
            servletResponse.setStatus(HttpServletResponse.SC_OK);
            Products product_to_change = productRepository.findById(id);
            product_to_change.setTitle(newProduct.getTitle());
            product_to_change.setPrice(newProduct.getPrice());
            product_to_change.setDescription(newProduct.getDescription());
            product_to_change.setCategory(newProduct.getCategory());
            product_to_change.setImage(newProduct.getImage());
            product_to_change.setRating(newProduct.getRating());
            Products returner = productRepository.save(product_to_change);
            if (returner.getId() > 0)
                return returner;
            throw new Exception();
        } catch (Exception exception) {
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            System.err.println(exception.getMessage());
        }
        return null;
    }

    @DeleteMapping(value = "/products/{id}")
    @ResponseBody
    public Object delete_product_by_id(@PathVariable int id) {
        try {
            servletResponse.setStatus(HttpServletResponse.SC_OK);
            productRepository.deleteById(id);
        } catch (Exception exception) {
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            System.err.println(exception.getMessage());
        }
        return null;
    }
    //endregion


    //region API's of customers

    /**
     * URL : api/customers/...
     */
    @GetMapping(value = "/customers")
    @ResponseBody
    public HashMap<String, Iterable<Customers>> show_all_customers() {
        try {
            HashMap<String, Iterable<Customers>> map = new HashMap<>();
            map.put("customers", customersRepository.findAll());
            servletResponse.setStatus(HttpServletResponse.SC_OK);
            if (map.get("customers").iterator().hasNext())
                return map;
            throw new Exception();
        } catch (Exception exception) {
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            System.err.println(exception.getMessage());
        }
        return null;
    }

    @PostMapping(value = "/customers")
    @ResponseBody
    public Customers add_new_customer(@RequestBody Customers newCustomer) {
        try {
            servletResponse.setStatus(HttpServletResponse.SC_CREATED);
            //----------Hashing the password before saving
            newCustomer.setPassword(passwordEncoder.encode(newCustomer.getPassword()));
            Customers returner = customersRepository.save(newCustomer);
            if (returner.getId() > 0)
                return returner;
            throw new Exception();
        } catch (Exception exception) {
            servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            System.err.println(exception.getMessage());
        }
        return null;
    }

    @GetMapping(value = "/customers/{id}")
    @ResponseBody
    public Customers show_customer_by_id(@PathVariable int id) {
        try {
            servletResponse.setStatus(HttpServletResponse.SC_GONE);
            Customers returner = customersRepository.findById(id);
            if (returner.getId() > 0)
                return returner;
            throw new Exception();
        } catch (Exception exception) {
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            System.err.println(exception.getMessage());
        }
        return null;
    }

    @PutMapping(value = "/customers/{id}")
    @ResponseBody
    public Customers change_customer_by_id(@RequestBody Customers newCustomer, @PathVariable int id) {
        try {
            servletResponse.setStatus(HttpServletResponse.SC_OK);
            Customers customer_to_change = customersRepository.findById(id);
            customer_to_change.setCity(newCustomer.getCity());
            customer_to_change.setStreet(newCustomer.getStreet());
            customer_to_change.setZipcode(newCustomer.getZipcode());
            customer_to_change.setEmail(newCustomer.getEmail());
            customer_to_change.setUsername(newCustomer.getUsername());
            //----------Hashing the password before saving
            customer_to_change.setPassword(passwordEncoder.encode(newCustomer.getPassword()));
            Customers returner = customersRepository.save(customer_to_change);
            if (returner.getId() > 0)
                return returner;
            throw new Exception();
        } catch (Exception exception) {
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            System.err.println(exception.getMessage());
        }
        return null;
    }
    //endregion


    //region API's of carts

    /**
     * URL : api/carts/...
     */

    @GetMapping(value = "/carts")
    @ResponseBody
    public HashMap<String, Iterable<Carts>> show_all_carts() {
        try {
            HashMap<String, Iterable<Carts>> map = new HashMap<>();
            map.put("carts", cartsRepository.findAll());
            servletResponse.setStatus(HttpServletResponse.SC_OK);
            if (map.get("carts").iterator().hasNext())
                return map;
            throw new Exception();
        } catch (Exception exception) {
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            System.err.println(exception.getMessage());
        }
        return null;
    }

    @PostMapping(value = "/carts")
    @ResponseBody
    public Carts add_new_cart(@RequestBody Carts newCart) {
        try {
            servletResponse.setStatus(HttpServletResponse.SC_CREATED);
            newCart.setId(idGeneratorForCart());
            Carts returner = cartsRepository.save(newCart);
            List<CartsProducts> products = returner.getProducts();
            for (CartsProducts tmp : products) {
                Products product = productRepository.findById(tmp.getProductId());
                product.setQuantity(product.getQuantity() - tmp.getQuantity());
                productRepository.save(product);
            }
            if (returner.getId() > 0)
                return returner;
            throw new Exception();
        } catch (Exception exception) {
            servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            System.err.println(exception.getMessage());
        }
        return null;
    }

    @GetMapping(value = "/carts/{id}")
    @ResponseBody
    public Carts show_cart_by_id(@PathVariable int id) {
        try {
            servletResponse.setStatus(HttpServletResponse.SC_GONE);
            Carts returner = cartsRepository.findById(id);
            if (returner.getId() > 0)
                return returner;
            throw new Exception();
        } catch (Exception exception) {
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            System.err.println(exception.getMessage());
        }
        return null;
    }

    @DeleteMapping(value = "/carts/{id}")
    @ResponseBody
    public Object delete_cart_by_id(@PathVariable int id) {
        try {
            servletResponse.setStatus(HttpServletResponse.SC_OK);
            cartsRepository.deleteById(id);
        } catch (Exception exception) {
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            System.err.println(exception.getMessage());
        }
        return null;
    }

    //endregion


    //---------- I generate Id's for carts, bcz no one else does!
    int idGeneratorForCart() {
        int last_id = 0;
        if (cartsRepository.findTopByOrderByIdDesc() != null)
            last_id = cartsRepository.findTopByOrderByIdDesc().getId();
        last_id++;
        return last_id;
    }
}