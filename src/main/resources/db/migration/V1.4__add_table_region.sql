create table region (
    id BIGSERIAL NOT NULL,
    name_region VARCHAR(255) NOT NULL,
    constraint region_pk primary key (id)
);

alter table app_order add column region_id BIGINT;
alter table app_order add constraint region_id_fk foreign key (region_id) references region(id);

