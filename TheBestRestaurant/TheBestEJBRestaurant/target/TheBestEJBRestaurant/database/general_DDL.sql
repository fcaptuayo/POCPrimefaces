CREATE SEQUENCE table_restaurant_id_seq;

CREATE TABLE table_restaurant
(
	id INTEGER DEFAULT nextval('table_restaurant_id_seq'::regclass) NOT NULL
		CONSTRAINT table_restaurant_pk
      PRIMARY KEY,
	capacity INTEGER NOT NULL,
	location VARCHAR(50) NOT NULL
);


CREATE SEQUENCE waiter_id_seq;

CREATE TABLE waiter
(
	id INTEGER DEFAULT nextval('waiter_id_seq'::regclass) NOT NULL
		CONSTRAINT waiter_pk
      PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL
);

CREATE SEQUENCE client_id_seq;

CREATE TABLE client
(
	id INTEGER DEFAULT nextval('client_id_seq'::regclass) NOT NULL
		CONSTRAINT client_pk
      PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	observation VARCHAR(500) NOT NULL
);

CREATE SEQUENCE bill_id_seq;

CREATE TABLE bill
(
	id INTEGER DEFAULT nextval('bill_id_seq'::regclass) NOT NULL
		CONSTRAINT bill_pk
      PRIMARY KEY,
	creation_date TIMESTAMP NOT NULL,
  waiter_id INTEGER
  CONSTRAINT bill_waiter_fk
    REFERENCES waiter,
  table_restaurant_id INTEGER
  CONSTRAINT bill_table_restaurant_fk
    REFERENCES table_restaurant,
  client_id INTEGER
  CONSTRAINT bill_client_fk
    REFERENCES client
);

CREATE SEQUENCE product_id_seq;

CREATE TABLE product
(
	id INTEGER DEFAULT nextval('product_id_seq'::regclass) NOT NULL
		CONSTRAINT product_pk
      PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	price FLOAT(50) NOT NULL,
  bill_id INTEGER
  CONSTRAINT product_bill_fk
    REFERENCES bill
);





