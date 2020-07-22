insert into user_role (role_id, user_id)
values (
        (select rol_id from role where rol_name='ROLE_ADMIN'),
        (select us_id from users where us_username= 'admin')
             );
insert into user_role (role_id, user_id)
values (
        (select rol_id from role where rol_name='ROLE_USER'),
        (select us_id from users where us_username= 'user')
             );
insert into user_role (role_id, user_id)
values (
        (select rol_id from role where rol_name='ROLE_OFFICE_MANAGER'),
        (select us_id from users where us_username= 'om')
             );
insert into user_role (role_id, user_id)
values (
        (select rol_id from role where rol_name='ROLE_HR'),
        (select us_id from users where us_username= 'hr')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_HR'),
        (select au_id from authority where au_name= 'USER_READ')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_HR'),
        (select au_id from authority where au_name= 'USER_READ_ALL')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_HR'),
        (select au_id from authority where au_name= 'USER_DELETE')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_OFFICE_MANAGER'),
        (select au_id from authority where au_name= 'USER_READ')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_USER'),
        (select au_id from authority where au_name= 'USER_READ')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_ADMIN'),
        (select au_id from authority where au_name= 'USER_WRITE')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_ADMIN'),
        (select au_id from authority where au_name= 'USER_READ')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_ADMIN'),
        (select au_id from authority where au_name= 'USER_READ_ALL')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_ADMIN'),
        (select au_id from authority where au_name= 'USER_DELETE')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_ADMIN'),
        (select au_id from authority where au_name= 'ROLE_READ')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_ADMIN'),
        (select au_id from authority where au_name= 'ROLE_WRITE')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_ADMIN'),
        (select au_id from authority where au_name= 'ROLE_DELETE')
             );