insert into mapping_user_table (parent_user_id, child_user_id)
values (
        (select us_id from users where us_email='hr2@mail.ru'),
        (select us_id from users where us_email= 'om@mail.ru')
             );
insert into mapping_user_table (parent_user_id, child_user_id)
values (
        (select us_id from users where us_email='hr2@mail.ru'),
        (select us_id from users where us_email= 'user@mail.ru')
             );
insert into mapping_user_table (parent_user_id, child_user_id)
values (
        (select us_id from users where us_email='hr@mail.ru'),
        (select us_id from users where us_email= 'admin@mail.ru')
             );
insert into mapping_user_table (parent_user_id, child_user_id)
values (
        (select us_id from users where us_email='hr@mail.ru'),
        (select us_id from users where us_email= 'hr2@mail.ru')
             );
insert into user_role (role_id, user_id)
values (
        (select rol_id from role where rol_name='ROLE_HR'),
        (select us_id from users where us_username= 'hr2')
             );

