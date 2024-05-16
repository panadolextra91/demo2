package com.example.demo.config;

import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class DatabaseSeeder {

    @Autowired
    CustomerService customerService;
    @Autowired
    SuppliersService suppliersService;
    @Autowired
    AreaService areaService;
    @Autowired
    WishlistService wishlistService;
    @Autowired
    WishlistItemService wishlistItemService;
    @Autowired
    ProductService productService;
    @Autowired
    InventoryManagerService inventoryManagerService;
    @Autowired
    InventoryTransactionsService inventoryTransactionsService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            // Check and seed Customers
            if (customerService.findAllCustomers().isEmpty()) {
                for (int i = 1; i <= 40; i++) {
                    Customer customer = new Customer();
                    customer.setUserName("User" + i);
                    customer.setPhone("100-200-300" + i);
                    customer.setAddress("1234 Main St, City" + i);
                    customer.setEmail("user" + i + "@example.com");
                    customer.setPassword("pass" + ThreadLocalRandom.current().nextInt(1000, 9999));
                    customerService.saveCustomer(customer);
                }
            }
            List<Customer> customers = customerService.findAllCustomers();
            // Check and seed Suppliers
            if (suppliersService.findAllSuppliers().isEmpty()) {
                for (Long i = 1L; i <= 4; i++) {
                    Suppliers supplier = new Suppliers();
                    supplier.setSuppliersName("Supplier" + i);
                    supplier.setPhone("111-222-333" + i);
                    supplier.setAddress("Street" + ThreadLocalRandom.current().nextInt(1000, 9999));
                    supplier.setEmail("supplier" + i + "@email.com");
                    suppliersService.saveSupplier(supplier);
                }
            }
            List<Suppliers> suppliers = suppliersService.findAllSuppliers();

            // Check and seed Inventory Managers
            if (inventoryManagerService.findAllInventoryManagers().isEmpty()) {
                for (Long i = 1L; i <= 2; i++) {
                    InventoryManager inventoryManager = new InventoryManager();
                    inventoryManager.setEmail("im" + i + "@example.com");
                    inventoryManager.setPassword("im" + ThreadLocalRandom.current().nextInt(1000, 9999));
                    inventoryManager.setPhone("101-202-303" + i);
                    inventoryManager.setUserName("Anh Thu " + i);
                    inventoryManagerService.saveInventoryManager(inventoryManager);
                }
            }
            List<InventoryManager> inventoryManagers = inventoryManagerService.findAllInventoryManagers();

            // Check and seed Areas
            if (areaService.findAllAreas().isEmpty()) {
                Long[] managers = {1L, 1L, 2L, 2L};
                String[] areaNames = {"East Area", "West Area", "South Area", "North Area"};
                for (int i = 0; i < 4; i++) {
                    Area area = new Area();
                    area.setAreaName(areaNames[i]);
                    area.setInventoryManager(inventoryManagerService.findInventoryManagerById(managers[i]));
                    areaService.saveArea(area);
                }
            }
            List<Area> areas = areaService.findAllAreas();

            // Check and seed Products
            if (productService.findAllProducts().isEmpty()) {
                for (int i = 1; i <= 20; i++) {
                    Product product = new Product();
                    product.setProductName("Product " + i);
                    product.setProductPrice(ThreadLocalRandom.current().nextDouble(50.0, 200.0));
                    product.setStock(ThreadLocalRandom.current().nextInt(10, 100));

                    // Assign a random area and supplier to each product
                    product.setArea(areaService.findAllAreas().get(ThreadLocalRandom.current().nextInt(4)));
                    product.setSupplier(suppliersService.findAllSuppliers().get(ThreadLocalRandom.current().nextInt(4)));
                    productService.saveProduct(product);
                }
            }
            List<Product> products = productService.findAllProducts();

            // Insert 1 order belonging to customer ID 1, with products ID 1 (quantity 1) and ID 2 (quantity 2)
            if (orderService.findAllOrders().isEmpty()) {
                Customer customer = customerService.findCustomerById(1);
                Order order = new Order();
                order.setOrderDate(new Date());
                order.setTotalPrice(0.0); // This will be calculated based on order items
                order.setCustomer(customer);

                orderService.saveOrder(order); // Save the order first to get the order ID

                OrderItem orderItem1 = new OrderItem();
                Product product1 = productService.findProductById(1L);
                orderItem1.setProduct(product1);
                orderItem1.setQuantity(1);
                orderItem1.setPrice(product1.getProductPrice() * orderItem1.getQuantity());
                orderItem1.setOrder(order);
                orderItemService.saveOrderItem(orderItem1); // Save each order item

                OrderItem orderItem2 = new OrderItem();
                Product product2 = productService.findProductById(2L);
                orderItem2.setProduct(product2);
                orderItem2.setQuantity(2);
                orderItem2.setPrice(product2.getProductPrice() * orderItem2.getQuantity());
                orderItem2.setOrder(order);
                orderItemService.saveOrderItem(orderItem2); // Save each order item

                // Recalculate total price using the OrderService method
                orderService.updateOrderTotalPrice(order.getOrderId());
            }


// Seed wishlists for each customer
            if (wishlistService.findAllWishlists().isEmpty()) {
                for (Customer customer : customers) {
                    Wishlist wishlist = new Wishlist();
                    wishlist.setCustomer(customer);
                    wishlistService.saveWishlist(wishlist); // Save the wishlist to get the wishlist ID
                }
            }

            // Seed wishlist items for each wishlist
            List<Wishlist> wishlists = wishlistService.findAllWishlists();
            for (Wishlist wishlist : wishlists) {
                int numOfWishlistItems = ThreadLocalRandom.current().nextInt(1, 6); // Random number of items between 1 and 5
                for (int i = 0; i < numOfWishlistItems; i++) {
                    WishlistItem wishlistItem = new WishlistItem();
                    Product randomProduct = products.get(ThreadLocalRandom.current().nextInt(products.size()));
                    int quantity = ThreadLocalRandom.current().nextInt(1, 10); // Random quantity between 1 and 10
                    wishlistItem.setProduct(randomProduct);
                    wishlistItem.setQuantity(quantity);
                    wishlistItem.setSubtotalPrice(randomProduct.getProductPrice() * quantity);
                    wishlistItem.setWishlist(wishlist);
                    wishlistItemService.saveWishlistItem(wishlistItem);
                }
            }
        };
    }
}
