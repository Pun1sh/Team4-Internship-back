insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_USER'),
        (select au_id from authority where au_name= 'BOOKING_READ')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_USER'),
        (select au_id from authority where au_name= 'BOOKING_WRITE')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_USER'),
        (select au_id from authority where au_name= 'BOOKING_DELETE')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_USER'),
        (select au_id from authority where au_name= 'QUEUE_WRITE')
             );
             insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_USER'),
        (select au_id from authority where au_name= 'QUEUE_DELETE')
             );
             insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_USER'),
        (select au_id from authority where au_name= 'QUEUE_READ')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_OFFICE_MANAGER'),
        (select au_id from authority where au_name= 'BOOKING_READ')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_OFFICE_MANAGER'),
        (select au_id from authority where au_name= 'BOOKING_WRITE')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_OFFICE_MANAGER'),
        (select au_id from authority where au_name= 'BOOKING_DELETE')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_OFFICE_MANAGER'),
        (select au_id from authority where au_name= 'QUEUE_WRITE')
             );
             insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_OFFICE_MANAGER'),
        (select au_id from authority where au_name= 'QUEUE_DELETE')
             );
             insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_OFFICE_MANAGER'),
        (select au_id from authority where au_name= 'QUEUE_READ')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_HR'),
        (select au_id from authority where au_name= 'BOOKING_READ')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_HR'),
        (select au_id from authority where au_name= 'BOOKING_WRITE')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_HR'),
        (select au_id from authority where au_name= 'BOOKING_DELETE')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_HR'),
        (select au_id from authority where au_name= 'QUEUE_WRITE')
             );
             insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_HR'),
        (select au_id from authority where au_name= 'QUEUE_DELETE')
             );
             insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_HR'),
        (select au_id from authority where au_name= 'QUEUE_READ')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_ADMIN'),
        (select au_id from authority where au_name= 'BOOKING_READ')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_ADMIN'),
        (select au_id from authority where au_name= 'BOOKING_WRITE')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_ADMIN'),
        (select au_id from authority where au_name= 'BOOKING_DELETE')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_ADMIN'),
        (select au_id from authority where au_name= 'QUEUE_WRITE')
             );
             insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_ADMIN'),
        (select au_id from authority where au_name= 'QUEUE_DELETE')
             );
             insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_ADMIN'),
        (select au_id from authority where au_name= 'QUEUE_READ')
             ); 