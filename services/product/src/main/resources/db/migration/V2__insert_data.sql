
INSERT INTO category (id, description, name) VALUES
(1, 'Computer Keyboards', 'Keyboards'),
(2, 'Computer Monitors', 'Monitors'),
(3, 'Display Screens', 'Screens'),
(4, 'Computer Mice', 'Mice'),
(5, 'Computer Accessories', 'Accessories');


-- Keyboards
 INSERT INTO product (id, description, name, price, category_id) VALUES
 (1, 'Mechanical keyboard with RGB lighting', 'Mechanical Keyboard 1', 99.99, 1),
 (2, 'Wireless compact keyboard', 'Wireless Compact Keyboard 1', 79.99, 1),
 (3, 'Backlit gaming keyboard with customizable keys', 'Gaming Keyboard 1', 129.99, 1),
 (4, 'Mechanical keyboard with wrist rest', 'Ergonomic Keyboard 1', 109.99, 1),
 (5, 'Wireless keyboard and mouse combo', 'Wireless Combo 1', 69.99, 1);

 -- Monitors
 INSERT INTO product (id, description, name, price, category_id) VALUES
 (6, '27-inch IPS monitor with 4K resolution', '4K Monitor 1', 399.99, 2),
 (7, 'Ultra-wide gaming monitor with HDR support', 'Ultra-wide Gaming Monitor 1', 499.99, 2),
 (8, '24-inch LED monitor for office use', 'Office Monitor 1', 179.99, 2),
 (9, '32-inch curved monitor with AMD FreeSync', 'Curved Monitor 1', 329.99, 2),
 (10, 'Portable USB-C monitor for laptops', 'Portable Monitor 1', 249.99, 2);

 -- Screens
 INSERT INTO product (id, description, name, price, category_id) VALUES
 (11, 'Curved OLED gaming screen with 240Hz refresh rate', 'Curved OLED Gaming Screen 1', 799.99, 3),
 (12, 'Flat QLED monitor with 1440p resolution', 'QLED Monitor 1', 599.99, 3),
 (13, '27-inch touch screen display for creative work', 'Touch Screen Display 1', 699.99, 3),
 (14, 'Ultra-slim 4K HDR display for multimedia', 'Ultra-slim 4K HDR Display 1', 449.99, 3),
 (15, 'Gaming projector with low input lag', 'Gaming Projector 1', 899.99, 3);

 -- Mice
 INSERT INTO product (id, description, name, price, category_id) VALUES
 (16, 'Wireless gaming mouse with customizable RGB lighting', 'RGB Gaming Mouse 1', 59.99, 4),
 (17, 'Ergonomic wired mouse for productivity', 'Ergonomic Wired Mouse 1', 29.99, 4),
 (18, 'Ambidextrous gaming mouse with high DPI', 'Ambidextrous Gaming Mouse 1', 69.99, 4),
 (19, 'Travel-sized compact mouse for laptops', 'Travel Mouse 1', 19.99, 4),
 (20, 'Vertical ergonomic mouse for reduced strain', 'Vertical Ergonomic Mouse 1', 39.99, 4);

 -- Accessories
 INSERT INTO product (id, description, name, price, category_id) VALUES
 (21, 'Adjustable laptop stand with cooling fan', 'Adjustable Laptop Stand 1', 34.99, 5),
 (22, 'Wireless charging pad for smartphones', 'Wireless Charging Pad 1', 24.99, 5),
 (23, 'Gaming headset stand with RGB lighting', 'RGB Headset Stand 1', 49.99, 5),
 (24, 'Bluetooth mechanical keypad for tablets', 'Bluetooth Keypad 1', 39.99, 5),
 (25, 'External hard drive enclosure with USB-C', 'External Hard Drive Enclosure 1', 29.99, 5);
