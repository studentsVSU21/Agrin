alter table Progress drop column date_start;
alter table Progress drop column date_end;
alter table Progress add column date_start DATE default null;
alter table Progress add column date_end DATE default null;