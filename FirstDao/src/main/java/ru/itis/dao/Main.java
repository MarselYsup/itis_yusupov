package ru.itis.dao;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.dao.database.CrudRepository;
import ru.itis.dao.database.ProductRepository;
import ru.itis.dao.model.Product;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties property = new Properties();
        try {
            property.load(ClassLoader.getSystemResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(property.getProperty("db.driver"));
        dataSource.setUsername(property.getProperty("db.user"));
        dataSource.setPassword(property.getProperty("db.password"));
        dataSource.setUrl(property.getProperty("db.url"));
        CrudRepository<Product,Long> productDao = new ProductRepository(dataSource);
        //saving data
        /*
        System.out.println(productDao.save(new Product("White bread","bakery","Germany",70)));
        System.out.println(productDao.save(new Product("Laptop","technology","China",30000)));
        */

        //select by id

        //System.out.println(productDao.findById(1L));

        //select all
        for (Product product:
             productDao.findAll()) {
            System.out.println(product);
        }

        //delete by idx
        //productDao.delete(2L);

        productDao.update(1L,new Product("Cat food","Pet","UK",560));
        for (Product product:
                productDao.findAll()) {
            System.out.println(product);
        }

    }
}
