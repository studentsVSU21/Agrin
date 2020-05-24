alter table progress drop column status;

create table public.status_order (
    id BIGSERIAL NOT NULL,
    name_status VARCHAR NOT NULL,
    constraint status_order_pk primary key (id),
    constraint status_order_name_uq unique (name_status)
);

alter table progress add column status BIGINT NOT NULL;
alter table progress add constraint status_fk foreign key (status) references status_order(id);

insert into status_order(name_status) values ('new');
insert into status_order(name_status) values ('adopted');
insert into status_order(name_status) values ('completed');