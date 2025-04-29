--liquibase formatted sql
--changeset iyiola.oluwatosin:20250428123200_create_users_table

CREATE TYPE user_status AS ENUM ('verification_completed', 'awaiting_verification');
CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    status user_status DEFAULT 'awaiting_verification',
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);
