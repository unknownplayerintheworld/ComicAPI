package com.hung.comicapi.database;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource dataSource(){
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
//        dataSourceBuilder.url("jdbc:mysql://monorail.proxy.rlwy.net:44759/comic");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/comic");
        dataSourceBuilder.username("root");
        //226beEGgFdHaCfEDEB-gEbehgbggA4ED
        dataSourceBuilder.password("chuhung2406...");
        return dataSourceBuilder.build();
    }
}
