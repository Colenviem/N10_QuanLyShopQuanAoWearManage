package data;

import dto.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import net.datafaker.Faker;
import util.JPAUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DataGenerator {
    private final Faker faker = new Faker();
    private final Random random = new Random();

    public Account generateAccount() {
        Account account = new Account();
        account.setUsername(faker.internet().username());
        account.setPassword(faker.internet().password());
        account.setStatus(random.nextBoolean());
        LocalDate randomDate = LocalDate.of(
                ThreadLocalRandom.current().nextInt(1950, 2000),
                ThreadLocalRandom.current().nextInt(1, 13),
                ThreadLocalRandom.current().nextInt(1, 29)
        );
        account.setCreatedDate(randomDate);
        return account;
    }

    public Store generateStore() {
        Store store = new Store();
        store.setName(faker.company().name());
        store.setAddress(faker.address().fullAddress());
        store.setPhone(faker.phoneNumber().cellPhone());
        store.setStatus(random.nextBoolean());
        return store;
    }

    public Supplier generateSupplier() {
        Supplier supplier = new Supplier();
        supplier.setSupplierName(faker.company().name());
        supplier.setAddress(faker.address().fullAddress());
        supplier.setPhone(faker.phoneNumber().cellPhone());
        supplier.setStatus(random.nextBoolean());
        return supplier;
    }

    public Category generateCategory() {
        Category category = new Category();
        category.setName(faker.commerce().brand());
        category.setDescription(faker.lorem().sentence());
        return category;
    }

    public Employee generateEmployee() {
        Employee employee = new Employee();
        employee.setFullName(faker.name().fullName());
        employee.setPhone(faker.phoneNumber().cellPhone());
        employee.setSalary(faker.number().numberBetween(1000000, 10000000));
        employee.setHireDate(LocalDate.now());
        employee.setStatus(random.nextBoolean());
        employee.setRole(faker.options().option(Role.class));
        employee.setAccount(generateAccount());
        employee.setStore(generateStore());
        return employee;
    }

    public Customer generateCustomer() {
        Customer customer = new Customer();
        customer.setName(faker.name().fullName());
        customer.setPhone(faker.phoneNumber().cellPhone());
        customer.setAccount(generateAccount());
        if(customer.getOrders() == null){
            customer.setOrders(new HashSet<>());
        }
        return customer;
    }

    public Product generateProduct() {
        Product product = new Product();
        product.setProductName(faker.commerce().productName());
        product.setDescription(faker.lorem().sentence());
        product.setDiscount(faker.number().randomDouble(2, 0, 1));
        product.setColor(faker.color().name());
        product.setStatus(random.nextBoolean());
        product.setImageUrl(faker.internet().image());
        product.setSize(faker.options().option(Size.class));
        product.setCategory(generateCategory());
        if(product.getPurchaseOrderDetails() == null){
            product.setPurchaseOrderDetails(new HashSet<>());
        }
        if(product.getOrderDetails() == null){
            product.setOrderDetails(new HashSet<>());
        }
        return product;
    }

    public PurchaseOrder generatePurchaseOrder() {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setOrderDate(LocalDate.now());
        purchaseOrder.setStatus(random.nextBoolean());
        purchaseOrder.setEmployee(generateEmployee());
        purchaseOrder.setSupplier(generateSupplier());
        if (purchaseOrder.getPurchaseOrderDetails() == null) {
            purchaseOrder.setPurchaseOrderDetails(new HashSet<>());
        }
        return purchaseOrder;
    }

    public PurchaseOrderDetail generatePurchaseOrderDetail() {
        PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail();
        purchaseOrderDetail.setQuantity(faker.number().numberBetween(1, 100));
        purchaseOrderDetail.setPurchasePrice(faker.number().randomDouble(2, 1000, 1000000));
        Product product = generateProduct();
        purchaseOrderDetail.setProduct(product);
        product.getPurchaseOrderDetails().add(purchaseOrderDetail);
        PurchaseOrder purchaseOrder = generatePurchaseOrder();
        purchaseOrder.getPurchaseOrderDetails().add(purchaseOrderDetail);
        purchaseOrderDetail.setPurchaseOrder(purchaseOrder);
        return purchaseOrderDetail;
    }

    public Order generateOrder() {
        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setStatus(faker.options().option(Status.class));
        Customer customer = generateCustomer();
        order.setCustomer(customer);
        customer.getOrders().add(order);
        order.setEmployee(generateEmployee());
        if (order.getOrderDetails() == null) {
            order.setOrderDetails(new HashSet<>());
        }
        return order;
    }

    public OrderDetail generateOrderDetail() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setQuantity(faker.number().numberBetween(1, 100));
        orderDetail.setPrice(faker.number().randomDouble(2, 1000, 1000000));
        Product product = generateProduct();
        orderDetail.setProduct(product);
        product.getOrderDetails().add(orderDetail);
        Order order = generateOrder();
        orderDetail.setOrder(order);
        order.getOrderDetails().add(orderDetail);
        return orderDetail;
    }

    public void generateAndPrintSampleData(){
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tr = em.getTransaction();
        for (int i = 0; i < 20; i++) {
            PurchaseOrderDetail purchaseOrderDetail = generatePurchaseOrderDetail();
            OrderDetail orderDetail = generateOrderDetail();
            tr.begin();
            em.persist(purchaseOrderDetail.getProduct().getCategory());
            em.persist(purchaseOrderDetail.getProduct());
            em.persist(purchaseOrderDetail.getPurchaseOrder());
            em.persist(purchaseOrderDetail.getPurchaseOrder().getEmployee());
            em.persist(purchaseOrderDetail.getPurchaseOrder().getEmployee().getStore());
            em.persist(purchaseOrderDetail.getPurchaseOrder().getEmployee().getAccount());
            em.persist(purchaseOrderDetail.getPurchaseOrder().getSupplier());
            em.persist(purchaseOrderDetail);
            em.persist(orderDetail.getProduct().getCategory());
            em.persist(orderDetail.getProduct());
            em.persist(orderDetail.getOrder());
            em.persist(orderDetail.getOrder().getEmployee());
            em.persist(orderDetail.getOrder().getEmployee().getStore());
            em.persist(orderDetail.getOrder().getEmployee().getAccount());
            em.persist(orderDetail.getOrder().getCustomer().getAccount());
            em.persist(orderDetail.getOrder().getCustomer());
            em.persist(orderDetail);
            tr.commit();
        }
    }
}
