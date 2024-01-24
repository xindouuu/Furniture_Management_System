DROP DATABASE IF EXISTS proj_demo;
CREATE DATABASE proj_demo;
USE proj_demo;

CREATE TABLE furn(
		`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
		`name` VARCHAR(64) NOT NULL,
		`maker` VARCHAR(64) NOT NULL,
		`price` DECIMAL(11,2) NOT NULL,
		`sales` INT(11) NOT NULL,
		`stock` INT(11) NOT NULL
);

INSERT INTO furn (`name`, `maker`, `price`, `sales`, `stock`) VALUES
('Bookshelf', 'Library Furniture Co.', 129.99, 40, 15),
('Desk', 'Office Essentials', 79.99, 60, 25),
('Couch', 'Comfy Living', 399.99, 25, 8),
('Dining Table', 'Family Gatherings', 149.99, 45, 18),
('Wardrobe', 'Closet Creations', 199.99, 35, 12),
('Coffee Table', 'Relaxation Furniture', 69.99, 90, 40),
('Side Table', 'Modern Accents', 29.99, 120, 60),
('Mirror', 'Reflective Designs', 49.99, 80, 30),
('Office Chair', 'Ergo Comfort', 89.99, 70, 22),
('Dresser', 'Fashionable Furnishings', 159.99, 55, 20),
('Chair', 'ABC Furniture', 49.99, 100, 50),
('Sofa', 'XYZ Furnishings', 299.99, 50, 20),
('Table', '123 Home Decor', 89.99, 75, 30),
('Lamp', 'Cozy Lighting', 19.99, 200, 100),
('Bed', 'Dreamy Beds', 499.99, 30, 10);

SELECT * from furn


