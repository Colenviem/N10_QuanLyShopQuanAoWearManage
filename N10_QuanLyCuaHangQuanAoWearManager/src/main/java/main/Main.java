package main;

import dao.AccountDao;
import dao.OrderDetailDao;
import dao.StoreDao;
import data.DataGenerator;
import dto.Account;
import dto.OrderDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        //Tạo dữ liệu mẫu (dùng datafaker)
//        DataGenerator dataGenerator = new DataGenerator();
//        dataGenerator.generateAndPrintSampleData();

        //Test các chức năng CRUD
        EntityManager em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();

        AccountDao account = new AccountDao(em);
        Account acc = account.getAccountById("ACC11267118");
        System.out.println("Tài khoản có mã ACC11267118");
        System.out.println(acc);
        System.out.println("Cập nhật tài khoản có mã ACC11267118");
        acc.setId("ACC11267118");
        acc.setUsername("admin");
        acc.setPassword("admin");
        acc.setStatus(true);
        acc.setCreatedDate(LocalDate.now());
        if(account.updateAccount(acc)) {
            System.out.println("Cập nhật thành công");
            System.out.println(acc);
        }else{
            System.out.println("Cập nhật thất bại");
        }
    }
}
