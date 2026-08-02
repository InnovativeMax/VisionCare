/*
---------------------------------------------------------
VisionCare ERP
Version     : 1.0
Script      : V1__Create_Roles.sql
Description : Creates Roles Table
---------------------------------------------------------
*/

USE visioncare;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;

CREATE TABLE roles (

                       id BIGINT PRIMARY KEY AUTO_INCREMENT,

                       role_code VARCHAR(20) NOT NULL UNIQUE,

                       display_name VARCHAR(100) NOT NULL,

                       description VARCHAR(255),

                       sort_order INT NOT NULL,

                       is_active BOOLEAN NOT NULL DEFAULT TRUE,

                       created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

                       created_by BIGINT NULL,

                       updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
                           ON UPDATE CURRENT_TIMESTAMP,

                       updated_by BIGINT NULL

);