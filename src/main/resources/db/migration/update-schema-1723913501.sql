ALTER TABLE product
    DROP COLUMN quantity;

ALTER TABLE product
    ADD quantity INT NOT NULL;

ALTER TABLE product
    MODIFY category_id VARCHAR(36) NOT NULL;

ALTER TABLE product
    MODIFY name VARCHAR(500) NOT NULL;

ALTER TABLE product
    DROP COLUMN price;

ALTER TABLE product
    ADD price DECIMAL(19, 2) NOT NULL;

ALTER TABLE product
    MODIFY price DECIMAL(19, 2) NOT NULL;