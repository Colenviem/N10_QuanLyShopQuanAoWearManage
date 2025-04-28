package dao;

import dto.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import util.JPAUtil;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public class ProductDAO {
    private EntityManager em;

    public ProductDAO(){
        em = JPAUtil.getEntityManager();
    }



    public List<Product> getProductByName(String name) {
        return em.createQuery("SELECT p FROM Product p WHERE p.productName LIKE :name", Product.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }


    public List<Product> getProductByColor(String color) {
        return em.createQuery("SELECT p FROM Product p WHERE p.color = :color", Product.class)
                .setParameter("color", color)
                .getResultList();
    }

    public List<Product> getProductBySize(String size) {
        return em.createQuery("SELECT p FROM Product p WHERE p.size = :size", Product.class)
                .setParameter("size", size)
                .getResultList();
    }

    public List<Product> getProductByStatus(boolean status) {
        return em.createQuery("SELECT p FROM Product p WHERE p.status = :status", Product.class)
                .setParameter("status", status)
                .getResultList();
    }

    public List<Product> getAllProducts() {
        return em.createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
    }

    public List<Object[]> getProductDetails() {
        return em.createQuery("SELECT p.id, p.productName, c.name, p.imageUrl, (p.price * (1 - p.discount/100)), p.stockQuantity, p.status, p.color, p.description, p.discount FROM Product p JOIN p.category c", Object[].class).getResultList();
    }


    public List<Object[]> getProductDetailsDashboard() {
        String jpql = "SELECT p.id, p.productName, c.name, p.stockQuantity, p.price, p.status " +
                "FROM Product p JOIN Category c on p.id = c.id";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }

    public Product getProductById(int id) {
        return em.find(Product.class, id);
    }

    public int getTotalProductsSold(String timePeriod) {
        try {
            String jpql;
            LocalDate startDate;
            LocalDate endDate;

            // Xác định khoảng thời gian
            switch (timePeriod.toLowerCase()) {
                case "tháng":
                    YearMonth currentMonth = YearMonth.now();
                    startDate = currentMonth.atDay(1);
                    endDate = currentMonth.atEndOfMonth();
                    break;
                case "quý":
                    int quarter = (LocalDate.now().getMonthValue() - 1) / 3 + 1;
                    startDate = LocalDate.of(LocalDate.now().getYear(), (quarter * 3) - 2, 1);
                    endDate = LocalDate.of(LocalDate.now().getYear(), (quarter * 3), LocalDate.of(LocalDate.now().getYear(), (quarter * 3), 1).lengthOfMonth());
                    break;
                case "năm":
                    startDate = LocalDate.of(LocalDate.now().getYear(), 1, 1);
                    endDate = LocalDate.of(LocalDate.now().getYear(), 12, 31);
                    break;
                default:
                    return 0; // Trả về 0 nếu không hợp lệ
            }

            // Truy vấn JPQL để tính tổng số lượng sản phẩm đã bán
            jpql = "SELECT SUM(od.quantity) FROM OrderDetail od WHERE od.order.orderDate BETWEEN :startDate AND :endDate";
            Query query = em.createQuery(jpql);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);

            // Kết quả có thể là null, cần kiểm tra
            Long result = (Long) query.getSingleResult();
            return (result != null) ? result.intValue() : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean addProduct(Product product) {
        try {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProduct(Product product) {
        try {
            em.getTransaction().begin();
            em.merge(product);
            em.flush();
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProduct(String id) {
        try {
            Product product = em.find(Product.class, id);
            if (product == null) {
                return false;
            }
            em.getTransaction().begin();
            em.remove(product);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }
}
