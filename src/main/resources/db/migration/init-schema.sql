CREATE TABLE category
(
    id            VARCHAR(255)  NOT NULL,
    created_at    datetime      NULL,
    updated_at    datetime      NULL,
    status        SMALLINT      NULL,
    name          VARCHAR(100)  NULL,
    `description` VARCHAR(2000) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE product
(
    id            VARCHAR(255)   NOT NULL,
    created_at    datetime       NULL,
    updated_at    datetime       NULL,
    status        SMALLINT       NULL,
    name          VARCHAR(500)   NULL,
    `description` VARCHAR(10000) NULL,
    price         DOUBLE         NULL,
    image_url     VARCHAR(255)   NULL,
    category_id   VARCHAR(255)   NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);