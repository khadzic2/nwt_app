CREATE DATABASE IF NOT EXISTS db_user;
CREATE DATABASE IF NOT EXISTS db_item;
CREATE DATABASE IF NOT EXISTS db_itemcart;
CREATE DATABASE IF NOT EXISTS db_order;
CREATE DATABASE IF NOT EXISTS db_system_events;

CREATE USER IF NOT EXISTS 'springuser'@'%' IDENTIFIED BY 'ThePassword';

GRANT ALL PRIVILEGES ON db_user.* TO 'springuser'@'%';
GRANT ALL PRIVILEGES ON db_item.* TO 'springuser'@'%';
GRANT ALL PRIVILEGES ON db_itemcart.* TO 'springuser'@'%';
GRANT ALL PRIVILEGES ON db_order.* TO 'springuser'@'%';
GRANT ALL PRIVILEGES ON db_system_events.* TO 'springuser'@'%';

USE db_user;
create table if not exists token
(
    id int not null
    primary key,
    expired bit(1)  not null,
    revoked bit(1)  not null,
    token   text not null,
    token_type varchar(255) not null,
    user_id int DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FKe32ek7ixanakfqsdaokm4q9y2 (user_id),
  CONSTRAINT FKe32ek7ixanakfqsdaokm4q9y2 FOREIGN KEY (user_id) REFERENCES user (id)
);
create table if not exists user
(
  id int NOT NULL,
  email varchar(255) NOT NULL,
  name varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  role smallint DEFAULT NULL,
  username varchar(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_ob8kqyqqgmefl0aco34akdtpe (email),
  UNIQUE KEY UK_sb8bbouer5wak8vyiiy4pf2bx (username)
);


USE db_item;
create table if not exists db_item.item
(
  id int NOT NULL,
  compared bit(1) NOT NULL,
  description varchar(255) DEFAULT NULL,
  manufacturingdays int DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  price double DEFAULT NULL,
  status varchar(255) DEFAULT NULL,
  image_id bigint DEFAULT NULL,
  itemcategory_id int DEFAULT NULL,
  specifications_id int DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FKrl4dencftq7wtvpmybgj0omn3 (image_id),
  KEY FK8baqvq4535sfmo6irqkap52kr (itemcategory_id),
  KEY FK6jvh4g4y9mmpb2q97y0f0ueth (specifications_id),
  CONSTRAINT FK6jvh4g4y9mmpb2q97y0f0ueth FOREIGN KEY (specifications_id) REFERENCES specifications (id),
  CONSTRAINT FK8baqvq4535sfmo6irqkap52kr FOREIGN KEY (itemcategory_id) REFERENCES item_category (id),
  CONSTRAINT FKrl4dencftq7wtvpmybgj0omn3 FOREIGN KEY (image_id) REFERENCES product_image (id)
);

create table if not exists db_item.item_category
(
 id int NOT NULL,
  category_name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

create table if not exists db_item.product_image
(
  id bigint NOT NULL AUTO_INCREMENT,
  imagedata mediumblob,
  name varchar(255) DEFAULT NULL,
  type varchar(255) DEFAULT NULL,
  PRIMARY KEY (id));

create table if not exists db_item.specifications
(
  id int NOT NULL,
  colors varchar(255) DEFAULT NULL,
  depth varchar(255) DEFAULT NULL,
  height varchar(255) DEFAULT NULL,
  material varchar(255) DEFAULT NULL,
  width varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

USE db_itemcart;
create table if not exists cart
(
    id int NOT NULL,
  user_id int NOT NULL,
  PRIMARY KEY (id)
);

create table if not exists item_cart
(
  id int NOT NULL,
  item_id int NOT NULL,
  order_id int DEFAULT NULL,
  cart_id int DEFAULT NULL,
  selected_specifications_id int NOT NULL,
  PRIMARY KEY (id),
  KEY FKqo72rduipl4c9r24tvd62oob3 (cart_id),
  KEY FKt4ogiou540upv3yhajrdsrren (selected_specifications_id),
  CONSTRAINT FKqo72rduipl4c9r24tvd62oob3 FOREIGN KEY (cart_id) REFERENCES cart (id),
  CONSTRAINT FKt4ogiou540upv3yhajrdsrren FOREIGN KEY (selected_specifications_id) REFERENCES selected_specifications (id)
);
create table if not exists selected_specifications
(
  id int NOT NULL,
  color varchar(255) NOT NULL,
  depth varchar(255) NOT NULL,
  height varchar(255) NOT NULL,
  material varchar(255) NOT NULL,
  width varchar(255) NOT NULL,
  PRIMARY KEY (id)
);

USE db_order;
create table if not exists db_order.date
(
   id int NOT NULL,
  delay_date date DEFAULT NULL,
  delivery_date date NOT NULL,
  PRIMARY KEY (id)
);

create table if not exists db_order.order_items
(
 id int NOT NULL,
  item_id int NOT NULL,
  order_id int NOT NULL,
  PRIMARY KEY (id),
  KEY FKbioxgbv59vetrxe0ejfubep1w (order_id),
  CONSTRAINT FKbioxgbv59vetrxe0ejfubep1w FOREIGN KEY (order_id) REFERENCES orders (id)
);

create table if not exists db_order.orders
(
    id int NOT NULL,
  user_id int NOT NULL,
  date_id int NOT NULL,
  state_id int NOT NULL,
  PRIMARY KEY (id),
  KEY FK7q9v44ue1fwruouiflpovu9f4 (date_id),
  KEY FKa0lyfl15wni9t4kvyic3tcuog (state_id),
  CONSTRAINT FK7q9v44ue1fwruouiflpovu9f4 FOREIGN KEY (date_id) REFERENCES date (id),
  CONSTRAINT FKa0lyfl15wni9t4kvyic3tcuog FOREIGN KEY (state_id) REFERENCES state (id)
);

create table if not exists db_order.state
(
 id int NOT NULL,
  state varchar(255) NOT NULL,
  PRIMARY KEY (id)
);



USE db_system_events;
create table if not exists db_system_events.action
(
   id int NOT NULL,
  action_type varchar(255) NOT NULL,
  resource_name varchar(255) NOT NULL,
  response_type varchar(255) NOT NULL,
  service varchar(255) NOT NULL,
  timestamp datetime(6) NOT NULL,
  username varchar(255) NOT NULL,
  PRIMARY KEY (id)
);