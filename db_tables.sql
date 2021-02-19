
CREATE TABLE auction 
(
id int NOT NULL,
userId int  NOT NULL, 
productId int  NOT NULL, 
price double  NOT NULL, 
createdOn timestamp, status int DEFAULT 0
);


CREATE TABLE AUCTIONSTATUSTYPE 
(
ID int NOT NULL,
TYPE varchar(255) DEFAULT NULL
);

CREATE TABLE delivery (
  id int NOT NULL,
  createdAt date,
  orderid int NOT NULL,
  productName int NOT NULL,
  userId int NOT NULL,
  status int NOT NULL DEFAULT 0
);

CREATE TABLE orders (
  id int NOT NULL,
  buyerId int DEFAULT NULL,
  productId int DEFAULT NULL,
  auctionId int DEFAULT NULL,
  createdOn timestamp,
  status int DEFAULT NULL
);


CREATE TABLE orderStatusLog (
  id int NOT NULL,
  orderId int DEFAULT NULL,
  createdOn timestamp,
  status int DEFAULT NULL
);



CREATE TABLE orderStatusType (
  id int NOT NULL,
  type varchar(100) DEFAULT NULL
);



CREATE TABLE paymentMethod (
  id int NOT NULL,
  name varchar(100) DEFAULT NULL,
  num varchar(100) DEFAULT NULL,
  expireDate varchar(100) DEFAULT NULL,
  userId int DEFAULT NULL,
  ccv int DEFAULT NULL
);



CREATE TABLE transactions(
  id int NOT NULL,
  userId int NOT NULL,
  description varchar(100) NOT NULL,
  amount varchar(100) NOT NULL,
  status int NOT NULL DEFAULT 0
);

ALTER TABLE auction
  ADD PRIMARY KEY (id);

--
-- Indexes for table AUCTIONSTATUSTYPE
--
ALTER TABLE AUCTIONSTATUSTYPE
  ADD PRIMARY KEY (ID);

--
-- Indexes for table delivery
--
ALTER TABLE delivery
  ADD PRIMARY KEY (id);

--
-- Indexes for table orders
--
ALTER TABLE orders
  ADD PRIMARY KEY (id);

--
-- Indexes for table orderStatusLog
--
ALTER TABLE orderStatusLog
  ADD PRIMARY KEY (id);

--
-- Indexes for table orderStatusType
--
ALTER TABLE orderStatusType
  ADD PRIMARY KEY (id);

--
-- Indexes for table paymentMethod
--
ALTER TABLE paymentMethod
  ADD PRIMARY KEY (id);

--
-- Indexes for table product
--
ALTER TABLE product
  ADD PRIMARY KEY (id);

--
-- Indexes for table transaction
--
ALTER TABLE transactions
  ADD PRIMARY KEY (id);

