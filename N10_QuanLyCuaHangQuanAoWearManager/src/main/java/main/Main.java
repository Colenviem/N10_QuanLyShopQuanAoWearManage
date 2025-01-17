package main;

import dao.StoreDao;
import data.DataGenerator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        //Tạo dữ liệu mẫu (dùng datafaker)
//        DataGenerator dataGenerator = new DataGenerator();
//        dataGenerator.generateAndPrintSampleData();

        //Test các chức năng CRUD
        EntityManager em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();
        StoreDao storeDao = new StoreDao(em);
        storeDao.getAllStores().forEach(s -> System.out.println(s.getId()));

        if(storeDao.deleteStore("1")){
            System.out.println("Xóa thành công");
        }else{
            System.out.println("Xóa thất bại");
        }
    }
}
