CREATE TABLE orders(
	system_id INT GENERATED ALWAYS AS IDENTITY,
    order_id character varying(255) UNIQUE ,
	status numeric not null,
    order_Total character varying(255) not null,
	shipping_cost decimal not null,
	customer_id character varying(255) not null,
	item_id character varying(255) not null,
	quantity decimal not null,
	note text,
	time bigint not null
);
 GRANT ALL PRIVILEGES ON TABLE orders TO root;