package com.hung.comicapi.service;

import org.apache.commons.io.FileUtils;
import org.aspectj.util.FileUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service

public class ScraperServices {
    private static final Path storageFolder = Paths.get("upload");
//    public void scrapeAndSaveImages(String url, String destinationPath) {
//        try {
//            Document document = (Document) Jsoup.connect(url).get();
//            Elements imgElements = document.select("img.lozad");
//
//            for (Element imgElement : imgElements) {
//                String imgUrl = imgElement.attr("src");
//                downloadImage(imgUrl, destinationPath);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void downloadImage(String imageUrl, String destinationPath) throws IOException {
//        // Thực hiện logic lưu trữ ảnh vào destinationPath
//        // ...
//        URL url = new URL(imageUrl);
//        String fileName = url.getFile().substring(url.getFile().lastIndexOf("/") + 1);
//        FileUtil
//    }
//    public static void createStorage() throws IOException {
//        if(!Files.exists(storageFolder)){
//            try{
//                Files.createDirectories(storageFolder);
//            }
//            catch (IOException e){
//                throw new IOException("Cannot create storage Folder!");
//            }
//        }
//    }
public void scrapeImages(String url, String outputDirectory) {
    try {
        // Kết nối đến trang web và lấy HTML
        Document document = Jsoup.connect(url).get();

        // Lấy tất cả các thẻ img
        Elements imgElements = document.select("img.lozad");

        // Tạo thư mục đầu ra nếu nó chưa tồn tại
        new File(outputDirectory).mkdirs();

        // Lặp qua từng thẻ img và lưu hình ảnh xuống thư mục
        for (Element imgElement : imgElements) {
            String imageUrl = imgElement.absUrl("src");
            saveImage(imageUrl, outputDirectory);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    private void saveImage(String imageUrl, String outputDirectory) {
        try {
            // Tạo URL từ đường link hình ảnh
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Kiểm tra response code
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Mở luồng để đọc dữ liệu
                BufferedInputStream in = new BufferedInputStream(connection.getInputStream());

                // Tạo tên file ngẫu nhiên cho ảnh
                String fileName = UUID.randomUUID().toString() + ".jpg";

                // Tạo đối tượng File cho hình ảnh trong thư mục đầu ra
                File outputFile = new File(outputDirectory, fileName);

                // Tạo một đối tượng FileOutputStream cho outputFile
                FileOutputStream outputStream = new FileOutputStream(outputFile);

                // Lưu hình ảnh từ URL xuống đĩa
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer, 0, 1024)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                outputStream.close();
                in.close();
                System.out.println("Downloaded image: " + fileName);
            } else {
                System.out.println("Failed to download image. Response code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
