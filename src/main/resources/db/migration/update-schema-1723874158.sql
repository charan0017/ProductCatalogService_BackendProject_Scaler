ALTER TABLE product
    MODIFY id VARCHAR(36);

ALTER TABLE product
    DROP FOREIGN KEY FK_product_category;

ALTER TABLE product
    DROP FOREIGN KEY FK_PRODUCT_ON_CATEGORY;

ALTER TABLE product
    MODIFY category_id VARCHAR(36);

ALTER TABLE category
    MODIFY id VARCHAR(36);

ALTER TABLE product
    ADD CONSTRAINT FK_product_category FOREIGN KEY (category_id) REFERENCES category (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);