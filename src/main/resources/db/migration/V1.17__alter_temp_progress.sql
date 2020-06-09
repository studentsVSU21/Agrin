alter table temp_progress drop column date_end;
alter table temp_progress drop column date_start;
alter table temp_progress drop constraint temp_progress_pk;

alter table temp_progress add column report_date date not null;
alter table temp_progress add column pesticide_id BIGINT not null;
alter table temp_progress add column id BIGSERIAL not null;

alter table temp_progress add constraint temp_progress_id_pk primary key (id);
alter table temp_progress add constraint progress_id_fk foreign key (progress_id) references app_progress(progress_id);
alter table temp_progress add constraint pesticide_id_fk foreign key ( pesticide_id ) references  pesticide(id);

ALTER TABLE temp_progress RENAME TO expense_report;