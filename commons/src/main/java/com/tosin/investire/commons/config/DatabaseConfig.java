package com.tosin.investire.commons.config;


import com.tosin.investire.dao.jooq.tables.daos.UsersDao;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DatabaseConfig {

    private final DSLContext dslContext;

    @Bean
    public UsersDao usersDao() {
        return new UsersDao(dslContext.configuration());
    }


}
