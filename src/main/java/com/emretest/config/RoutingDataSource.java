package com.emretest.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        // Kullanıcının veritabanı erişim tipini belirleyin
        // Eğer işlem okuma ise "readDataSource" yönlendirilir, yazma işlemi için "writeDataSource" kullanılır
        return DataSourceContextHolder.getDataSourceType();
    }
}