/*
---------------------------------------------------------
VisionCare ERP
Version     : 1.0
Script      : V4__Insert_Default_Admin.sql
Description : Inserts Default Administrator
---------------------------------------------------------
*/

USE visioncare;

INSERT INTO users
(
    user_code,
    full_name,
    username,
    password_hash,
    role_id,
    theme,
    must_change_password,
    last_login,
    is_active,
    created_at,
    created_by,
    updated_at,
    updated_by
)
SELECT
    'USR000001',
    'System Administrator',
    'admin',
    '$2a$10$s8SnRw6Kmp7OChPqLdEIc.oLqbCaUaaIWBKTs4PUFWWwJLNBcyhTa',
    id,
    'LIGHT',
    TRUE,
    NULL,
    TRUE,
    CURRENT_TIMESTAMP,
    NULL,
    CURRENT_TIMESTAMP,
    NULL
FROM roles
WHERE role_code = 'ADMIN';