create table customer(
    id BIGSERIAL NOT NULL,
    email VARCHAR NOT NULL,
    phone_number VARCHAR NOT NULL,
    fio VARCHAR NOT NULL
);

alter table app_order drop column email;
alter table app_order drop column phone_number;
alter table app_order add column customer_id BIGINT NOT NULL;