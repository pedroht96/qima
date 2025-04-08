-- Define o caminho de pesquisa para o schema correto
SET search_path TO product_db;

-- Inserindo categorias principais
INSERT INTO product_db.category (name, description, parent_id) VALUES
    ('Electronics', 'Devices and gadgets', NULL),
    ('Computers', 'Desktops and laptops', 1),
    ('Mobile Phones', 'Smartphones and cell phones', 1),
    ('Home Appliances', 'Appliances for home use', NULL),
    ('Furniture', 'Home and office furniture', NULL),
    ('Fashion', 'Clothing and accessories', NULL),
    ('Mens Clothing', 'Apparel for men', 6),
    ('Womens Clothing', 'Apparel for women', 6),
    ('Kids Clothing', 'Apparel for kids', 6),
    ('Health & Beauty', 'Products for health and beauty', NULL);

-- Inserindo subcategorias para "Electronics"
INSERT INTO product_db.category (name, description, parent_id) VALUES
    ('TVs', 'Television sets', 1),
    ('Audio Systems', 'Speakers and sound systems', 1),
    ('Cameras', 'Digital and DSLR cameras', 1);

-- Inserindo subcategorias para "Computers"
INSERT INTO product_db.category (name, description, parent_id) VALUES
    ('Laptops', 'Portable computers', 2),
    ('Desktops', 'Personal computers', 2),
    ('Computer Accessories', 'Accessories for computers', 2);

-- Inserindo subcategorias para "Home Appliances"
INSERT INTO product_db.category (name, description, parent_id) VALUES
    ('Refrigerators', 'Cooling appliances', 4),
    ('Microwaves', 'Microwave ovens', 4),
    ('Washing Machines', 'Laundry appliances', 4),
    ('Air Conditioners', 'Cooling systems', 4);
