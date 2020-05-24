alter table app_order drop constraint order_fk0;
alter table app_order drop column progress_id;
alter table app_order add column progress_id bigint default null;
alter table app_order add constraint progress_fk foreign key ( progress_id ) references progress(progress_id);