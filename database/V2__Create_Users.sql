/*
---------------------------------------------------------
VisionCare ERP
Version     : 1.0
Script      : V2__Create_Users.sql
Description : Creates Users Table
---------------------------------------------------------
*/

USE visioncare;

CREATE TABLE users (

                       id BIGINT PRIMARY KEY AUTO_INCREMENT,

                       user_code VARCHAR(20) NOT NULL UNIQUE,

                       full_name VARCHAR(100) NOT NULL,

                       username VARCHAR(50) NOT NULL UNIQUE,

                       password_hash VARCHAR(255) NOT NULL,

                       role_id BIGINT NOT NULL,

                       theme ENUM('LIGHT','DARK')
        NOT NULL DEFAULT 'LIGHT',

                       must_change_password BOOLEAN
                           NOT NULL DEFAULT TRUE,

                       last_login DATETIME NULL,

                       is_active BOOLEAN
                           NOT NULL DEFAULT TRUE,

                       created_at DATETIME
                           NOT NULL DEFAULT CURRENT_TIMESTAMP,

                       created_by BIGINT NULL,

                       updated_at DATETIME
                           NOT NULL DEFAULT CURRENT_TIMESTAMP
                           ON UPDATE CURRENT_TIMESTAMP,

                       updated_by BIGINT NULL,

                       CONSTRAINT fk_users_role
                           FOREIGN KEY (role_id)
                               REFERENCES roles(id)

);