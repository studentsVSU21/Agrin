alter table pesticide drop constraint pesticide_pk cascade;
alter table pesticide drop column pesticide_id cascade;
alter table pesticide add column id BIGSERIAL NOT NULL;
alter table pesticide add constraint pesticide_pk primary key (id);