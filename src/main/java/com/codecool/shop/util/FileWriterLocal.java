package com.codecool.shop.util;

import com.codecool.shop.model.order.Order;
import com.codecool.shop.model.order.OrderType;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileWriterLocal {

    private static final Logger logger = LoggerFactory.getLogger(FileWriterLocal.class);

    public static void serializeObj(Order order) {
        String serializedMovie = new Gson().toJson(order);
        FileWriter file = null;
        try {
            Date date = new Date() ;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
            String fileName = dateFormat.format(date);
            file = new FileWriter("src/main/webapp/static/orders/" + fileName + ".json");
            file.write(serializedMovie);
            logger.info(String.format("Order placed from: %s, as order_id: %s", order.getName(), order.getID()));
            file.close();
        } catch (IOException e) {
            logger.error(String.format("Problem during payment. Order ID: %s", order.getID()));
            e.printStackTrace();
        }
    }

    public static void stringToFile (String content, String filename) {
        FileWriter file = null;
        try {
            file = new FileWriter(filename);
            file.write(content);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String fileToString(String filename) throws IOException {
        Path fileName = Path.of(filename);
        String actual = Files.readString(fileName);
        return actual;
    }

    public static void saveAdminLog(Order order) throws IOException {
        if (order.getOrderType() == OrderType.CHECKEDOUT) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
            String filename = "src/main/webapp/static/admin_log/" + order.getID() + "_" + dateFormat.format(new Date()) + ".txt";
            order.setFilename(filename);
            logger.info(String.format("Successfully checked out. Order id: %s", order.getID()));
            stringToFile(new Gson().toJson(order), filename);
        } else {
            String filename = order.getFilename();
            String content = fileToString(filename) + "\n" + new Gson().toJson(order);
            logger.info(String.format("Successfully paid. Order id: %s", order.getID()));
            stringToFile(content, filename);
        }
    }


}