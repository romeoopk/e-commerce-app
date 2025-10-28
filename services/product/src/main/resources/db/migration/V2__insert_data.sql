-- Insert categories
INSERT INTO category (id, description, name) VALUES
(1, 'Electronic devices and accessories', 'Electronics'),
(2, 'Home and kitchen appliances', 'Home & Kitchen'),
(3, 'Fashion and clothing items', 'Fashion'),
(4, 'Sports and outdoor equipment', 'Sports & Outdoors'),
(5, 'Books and educational materials', 'Books');

-- Insert products
INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES
(1, 'Latest smartphone with high-resolution camera', 'Smartphone', 50.0, 699.99, 1),
(2, 'Wireless Bluetooth headphones with noise cancellation', 'Wireless Headphones', 25.0, 199.99, 1),
(3, 'High-performance laptop for professionals', 'Laptop', 15.0, 1299.99, 1),
(4, 'Programmable coffee maker with thermal carafe', 'Coffee Maker', 30.0, 89.99, 2),
(5, 'Non-stick cookware set 10 pieces', 'Cookware Set', 20.0, 149.99, 2),
(6, 'Robot vacuum cleaner with smart mapping', 'Robot Vacuum', 12.0, 299.99, 2),
(7, 'Casual cotton t-shirt available in multiple colors', 'Cotton T-Shirt', 100.0, 19.99, 3),
(8, 'Classic denim jeans with comfortable fit', 'Denim Jeans', 75.0, 49.99, 3),
(9, 'Running shoes with cushioned sole', 'Running Shoes', 40.0, 79.99, 3),
(10, 'Professional basketball with grip texture', 'Basketball', 60.0, 29.99, 4),
(11, 'Lightweight tent for 4 people', 'Camping Tent', 18.0, 129.99, 4),
(12, 'Yoga mat with non-slip surface', 'Yoga Mat', 35.0, 24.99, 4),
(13, 'Bestselling fiction novel', 'Fiction Novel', 200.0, 14.99, 5),
(14, 'Learn programming with practical examples', 'Programming Guide', 80.0, 39.99, 5),
(15, 'Cookbook with healthy recipes', 'Healthy Cookbook', 45.0, 24.99, 5);