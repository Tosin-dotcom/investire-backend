--liquibase formatted sql
--changeset iyiola.oluwatosin:20250430144800_modify_user_table

ALTER TABLE users
    ADD COLUMN first_name VARCHAR(255) NOT NULL,
    ADD COLUMN last_name VARCHAR(255) NOT NULL,
    DROP COLUMN full_name;
