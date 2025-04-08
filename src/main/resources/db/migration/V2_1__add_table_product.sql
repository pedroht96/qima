DROP TABLE IF EXISTS product;

CREATE TABLE product_db.product (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    price NUMERIC(10, 2) NOT NULL,
    category_id BIGINT REFERENCES product_db.category(id) ON DELETE SET NULL,
    available BOOLEAN DEFAULT TRUE
);