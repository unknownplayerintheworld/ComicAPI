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
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ScraperServices {
    private static final Path STORAGE_FOLDER = Paths.get("upload");

    public void scrapeAndSaveImages(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements imgElements = document.select("img[src]");

            Files.createDirectories(STORAGE_FOLDER);

            int imageIndex = 1;
            for (Element imgElement : imgElements) {
                String imageUrl = imgElement.absUrl("src");
                System.out.println("Absolute URL of the image: " + imageUrl);
                String fileName = getImageFileName(url, imageIndex);
                saveImage(imageUrl, fileName);
                imageIndex++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveImage(String imageUrl, String fileName) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
                FileOutputStream outputStream = new FileOutputStream(STORAGE_FOLDER.resolve(fileName).toFile());

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

    private String getImageFileName(String url, int imageIndex) {
        String[] parts = url.split("-");
        String chapterNumber = parts[parts.length - 1].replaceAll("[^\\d]", "");
        return "toan_co_tu_" + chapterNumber + "_" + imageIndex + ".jpg";
    }
}

