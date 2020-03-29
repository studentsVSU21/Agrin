CREATE TABLE "Progress" (
	"Progress_ID" BIGINT NOT NULL,
	"date_end" DATE NOT NULL,
	"date_start" DATE NOT NULL,
	"processed_area" FLOAT NOT NULL DEFAULT '0',
	"status" varchar(255) NOT NULL,
	CONSTRAINT "Progress_pk" PRIMARY KEY ("Progress_ID")
);


CREATE TABLE "Order" (
	"Order_ID" BIGINT NOT NULL,
	"phone_number" BIGINT NOT NULL,
	"email" varchar(50) NOT NULL DEFAULT 'NULL',
	"area" FLOAT(50) NOT NULL,
	"Progress_ID" BIGINT NOT NULL,
	CONSTRAINT "Order_pk" PRIMARY KEY ("Order_ID")
);


CREATE TABLE "Pesticide" (
	"name" varchar(50) NOT NULL,
	"volume" FLOAT NOT NULL,
	"Pesticide_ID" BIGINT NOT NULL,
	CONSTRAINT "Pesticide_pk" PRIMARY KEY ("Pesticide_ID")
);


CREATE TABLE "Pesticide_In_Order" (
	"Pesticide_ID" BIGINT NOT NULL,
	"Order_ID" BIGINT NOT NULL,
	CONSTRAINT "Pesticide_In_Order_pk" PRIMARY KEY ("Pesticide_ID","Order_ID")
);



CREATE TABLE "User_orders" (
	"Order_ID" BIGINT NOT NULL,
	"User_ID" BIGINT NOT NULL,
	CONSTRAINT "User_orders_pk" PRIMARY KEY ("Order_ID","User_ID")
);



CREATE TABLE "User" (
	"User_ID" BIGINT NOT NULL,
	"ROLE_USER" varchar(255) NOT NULL,
	"password" varchar(255) NOT NULL,
	"fio" varchar(255) NOT NULL,
	"email" varchar(255) NOT NULL,
	"phone_number" varchar(255)NOT NULL,
	CONSTRAINT "User_pk" PRIMARY KEY ("User_ID")
) ;



CREATE TABLE "User_In_Progress" (
	"Progress_ID" BIGINT NOT NULL,
	"User_ID" BIGINT NOT NULL,
	CONSTRAINT "User_In_Progress_pk" PRIMARY KEY ("Progress_ID","User_ID")
) ;



CREATE TABLE "temp_progress" (
	"Progress_ID" BIGINT NOT NULL,
	"Date_start" TIMESTAMP NOT NULL,
	"Date_end" TIMESTAMP NOT NULL,
	CONSTRAINT "temp_progress_pk" PRIMARY KEY ("Progress_ID")
);



CREATE TABLE "blackList" (
	"email" varchar(255) NOT NULL DEFAULT 'NULL',
	"number_phone" varchar(255) NOT NULL DEFAULT 'NULL',
	"ID" varchar(255) NOT NULL DEFAULT 'NULL'
);




ALTER TABLE "Order" ADD CONSTRAINT "Order_fk0" FOREIGN KEY ("Progress_ID") REFERENCES "Progress"("Progress_ID");


ALTER TABLE "Pesticide_In_Order" ADD CONSTRAINT "Pesticide_In_Order_fk0" FOREIGN KEY ("Pesticide_ID") REFERENCES "Pesticide"("Pesticide_ID");
ALTER TABLE "Pesticide_In_Order" ADD CONSTRAINT "Pesticide_In_Order_fk1" FOREIGN KEY ("Order_ID") REFERENCES "Order"("Order_ID");

ALTER TABLE "User_orders" ADD CONSTRAINT "User_orders_fk0" FOREIGN KEY ("Order_ID") REFERENCES "Order"("Order_ID");
ALTER TABLE "User_orders" ADD CONSTRAINT "User_orders_fk1" FOREIGN KEY ("User_ID") REFERENCES "User"("User_ID");


ALTER TABLE "User_In_Progress" ADD CONSTRAINT "User_In_Progress_fk0" FOREIGN KEY ("Progress_ID") REFERENCES "Progress"("Progress_ID");
ALTER TABLE "User_In_Progress" ADD CONSTRAINT "User_In_Progress_fk1" FOREIGN KEY ("User_ID") REFERENCES "User"("User_ID");

ALTER TABLE "temp_progress" ADD CONSTRAINT "temp_progress_fk0" FOREIGN KEY ("Progress_ID") REFERENCES "Progress"("Progress_ID");

