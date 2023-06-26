CREATE TABLE users (
  id integer PRIMARY KEY,
  username varchar(30) NOT NULL,
  password varchar(255) NOT NULL,
  email varchar(50) NOT NULL,
  first_name varchar(30) NOT NULL,
  last_name varchar(30) NOT NULL,
  address varchar(100) NOT NULL,
  phone varchar(11) NOT NULL,
  date_of_birth date NOT NULL,
  join_date date NOT NULL,
  is_blocked NUMBER(1,0) DEFAULT 0,
  is_active NUMBER(1,0) DEFAULT 1,
  created_at timestamp,
  updated_at timestamp
);


CREATE SEQUENCE users_sequence;

CREATE OR REPLACE TRIGGER users_on_insert
  BEFORE INSERT ON users
  FOR EACH ROW
BEGIN
  SELECT users_sequence.nextval
  INTO :new.id
  FROM dual;
END;

ALTER TABLE users 
ADD role_id NUMBER(2,0) NOT NULL;

ALTER TABLE users
ADD FOREIGN KEY (role_id) REFERENCES role(role_id);
--==============================================================================

CREATE TABLE role (
    role_id NUMBER(2,0) PRIMARY KEY,
    role_type varchar(30)
);

--==============================================================================
CREATE TABLE token (
    id INTEGER PRIMARY KEY,
    token VARCHAR(255),
    expire_date date,
    token_type VARCHAR(30),
    expired number(1,0) DEFAULT 0,
    revoked number(1,0) DEFAULT 0,
    user_id INTEGER NOT NULL,
    
    CONSTRAINT FK_TOKEN_USER_ID FOREIGN KEY (user_id) REFERENCES users(id)
);
CREATE SEQUENCE token_sequence;

CREATE OR REPLACE TRIGGER token_on_insert
  BEFORE INSERT ON token
  FOR EACH ROW
BEGIN
  SELECT token_sequence.nextval
  INTO :new.id
  FROM dual;
END;



--==============================================================================
CREATE TABLE category (
  id integer PRIMARY KEY,
  category varchar(30) NOT NULL
);
CREATE SEQUENCE category_sequence;

CREATE OR REPLACE TRIGGER category_on_insert
  BEFORE INSERT ON category
  FOR EACH ROW
BEGIN
  SELECT category_sequence.nextval
  INTO :new.id
  FROM dual;
END;
--==============================================================================

CREATE TABLE product (
  id integer PRIMARY KEY,
  name varchar(30) NOT NULL,
  description varchar(500) NOT NULL,
  price decimal(12, 2) NOT NULL,
  is_stocked NUMBER(1,0) DEFAULT 0,
  status NUMBER(1,0) DEFAULT 1,
  image_url varchar(4000),
  purchase_num integer DEFAULT 0,
  quantity_id integer NOT NULL,
  category_id integer NOT NULL,
  discount_id integer,
  created_at timestamp,
  updated_at timestamp,
  CONSTRAINT fk_product_Category FOREIGN KEY (category_id) REFERENCES category(id),
  CONSTRAINT fk_product_Quantity FOREIGN KEY (quantity_id) REFERENCES quantity(id),
  CONSTRAINT fk_product_Discount FOREIGN KEY (discount_id) REFERENCES discount(id)
);




CREATE SEQUENCE product_sequence;

CREATE OR REPLACE TRIGGER product_on_insert
  BEFORE INSERT ON product
  FOR EACH ROW
BEGIN
  SELECT product_sequence.nextval
  INTO :new.id
  FROM dual;
END;
--==============================================================================
CREATE TABLE ratings (
  rating number(1,1) DEFAULT 0,
  product_id integer NOT NULL,
  users_id integer NOT NULL,
  rating_date date NOT NULL,
  
  CONSTRAINT ratings_PK
    PRIMARY KEY(product_id,users_id)

);

--==============================================================================

CREATE TABLE transaction (
  id integer PRIMARY KEY,
  description varchar(500),
  amount number(5,2) NOT NULL,
  payment_method varchar(20) NOT NULL,
  status number(1,0),
  order_id integer NOT NULL,
  CONSTRAINT fk_product_orders FOREIGN KEY (order_id) REFERENCES orders(id)
);

CREATE SEQUENCE transaction_sequence;

CREATE OR REPLACE TRIGGER transaction_on_insert
  BEFORE INSERT ON transaction
  FOR EACH ROW
BEGIN
  SELECT transaction_sequence.nextval
  INTO :new.id
  FROM dual;
END;

--==============================================================================


CREATE TABLE discount (
  id integer PRIMARY KEY,
  disc_percent number(2,0) NOT NULL,
  is_active number(1,0) DEFAULT 1,
  expire_date date NOT NULL
);

CREATE SEQUENCE discount_sequence;

CREATE OR REPLACE TRIGGER discount_on_insert
  BEFORE INSERT ON discount
  FOR EACH ROW
BEGIN
  SELECT discount_sequence.nextval
  INTO :new.id
  FROM dual;
END;
--==============================================================================


CREATE TABLE orders (
  id integer PRIMARY KEY,
  order_date date NOT NULL,
  is_delivered number(1,0) DEFAULT 0,
  total_amount number(5,2) NOT NULL,
  users_id integer NOT NULL,
  CONSTRAINT fk_order_users
    FOREIGN KEY (users_id)
      REFERENCES users(id)
);
CREATE INDEX order_users_id_index ON  orders (users_id);


CREATE SEQUENCE order_sequence;

CREATE OR REPLACE TRIGGER order_on_insert
  BEFORE INSERT ON orders
  FOR EACH ROW
BEGIN
  SELECT order_sequence.nextval
  INTO :new.id
  FROM dual;
END;
--==============================================================================


CREATE TABLE order_items (
  order_id integer NOT NULL,
  product_id integer NOT NULL,
  quantity NUMBER(4,0) NOT NULL,
  price_per_unit DECIMAL(5,2) NOT NULL,
  
  CONSTRAINT orider_items_PK
    PRIMARY KEY (order_id,product_id),
  
  CONSTRAINT fk_order_items_order
    FOREIGN KEY (order_id)
      REFERENCES orders(id),
  CONSTRAINT fk_order_items_product
    FOREIGN KEY (product_id)
      REFERENCES product(id)
);



CREATE TABLE quantity (
  id integer PRIMARY KEY,
  item_qnt integer NOT NULL,
  min_qnt integer NOT NULL
);

CREATE SEQUENCE quantity_sequence;

CREATE OR REPLACE TRIGGER quantity_on_insert
  BEFORE INSERT ON quantity
  FOR EACH ROW
BEGIN
  SELECT quantity_sequence.nextval
  INTO :new.id
  FROM dual;
END;
--==============================================================================




