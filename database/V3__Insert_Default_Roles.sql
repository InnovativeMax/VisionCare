/*
---------------------------------------------------------
VisionCare ERP
Version     : 1.0
Script      : V3__Insert_Default_Roles.sql
Description : Inserts Default Roles
---------------------------------------------------------
*/

USE visioncare;

INSERT INTO roles
(
    role_code,
    display_name,
    description,
    sort_order
)
VALUES
    ('ADMIN',       'Administrator',       'Full System Access',            1),
    ('SALES',       'Sales Executive',     'Handles Sales & Billing',       2),
    ('OPTOMETRIST', 'Optometrist',         'Manages Eye Prescriptions',     3),
    ('INVENTORY',   'Inventory Manager',   'Handles Inventory & Stock',     4),
    ('FINANCE',     'Finance',             'Handles Payments & Accounts',   5),
    ('PRODUCTION',  'Production Staff',    'Handles Production Workflow',   6);