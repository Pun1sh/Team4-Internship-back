insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_USER'),
        (select au_id from authority where au_name= 'OFFICE_READ')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_USER'),
        (select au_id from authority where au_name= 'FLOOR_READ')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_USER'),
        (select au_id from authority where au_name= 'ROOM_READ')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_USER'),
        (select au_id from authority where au_name= 'PLACE_READ')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_ADMIN'),
        (select au_id from authority where au_name= 'OFFICE_READ')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_ADMIN'),
        (select au_id from authority where au_name= 'OFFICE_WRITE')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_ADMIN'),
        (select au_id from authority where au_name= 'OFFICE_DELETE')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_ADMIN'),
        (select au_id from authority where au_name= 'FLOOR_READ')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_ADMIN'),
        (select au_id from authority where au_name= 'FLOOR_WRITE')
              );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_ADMIN'),
        (select au_id from authority where au_name= 'FLOOR_DELETE')
               );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_ADMIN'),
        (select au_id from authority where au_name= 'ROOM_READ')
                );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_ADMIN'),
        (select au_id from authority where au_name= 'ROOM_WRITE')
                );
insert into role_authority (role_id, authority_id)
values (
         (select rol_id from role where rol_name='ROLE_ADMIN'),
         (select au_id from authority where au_name= 'ROOM_DELETE')
                );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_ADMIN'),
        (select au_id from authority where au_name= 'PLACE_READ')
                );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_ADMIN'),
        (select au_id from authority where au_name= 'PLACE_WRITE')
                );
insert into role_authority (role_id, authority_id)
values (
         (select rol_id from role where rol_name='ROLE_ADMIN'),
         (select au_id from authority where au_name= 'PLACE_DELETE')
                );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_HR'),
        (select au_id from authority where au_name= 'OFFICE_READ')
                );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_HR'),
        (select au_id from authority where au_name= 'FLOOR_READ')
                );
insert into role_authority (role_id, authority_id)
values (
         (select rol_id from role where rol_name='ROLE_HR'),
         (select au_id from authority where au_name= 'ROOM_READ')
                );
insert into role_authority (role_id, authority_id)
values (
         (select rol_id from role where rol_name='ROLE_HR'),
         (select au_id from authority where au_name= 'PLACE_READ')
                );
insert into role_authority (role_id, authority_id)
values (
         (select rol_id from role where rol_name='ROLE_OFFICE_MANAGER'),
         (select au_id from authority where au_name= 'OFFICE_READ')
                );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_OFFICE_MANAGER'),
        (select au_id from authority where au_name= 'OFFICE_WRITE')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_OFFICE_MANAGER'),
        (select au_id from authority where au_name= 'OFFICE_DELETE')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_OFFICE_MANAGER'),
        (select au_id from authority where au_name= 'FLOOR_READ')
             );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_OFFICE_MANAGER'),
        (select au_id from authority where au_name= 'FLOOR_WRITE')
              );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_OFFICE_MANAGER'),
        (select au_id from authority where au_name= 'FLOOR_DELETE')
               );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_OFFICE_MANAGER'),
        (select au_id from authority where au_name= 'ROOM_READ')
                );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_OFFICE_MANAGER'),
        (select au_id from authority where au_name= 'ROOM_WRITE')
                );
insert into role_authority (role_id, authority_id)
values (
         (select rol_id from role where rol_name='ROLE_OFFICE_MANAGER'),
         (select au_id from authority where au_name= 'ROOM_DELETE')
                );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_OFFICE_MANAGER'),
        (select au_id from authority where au_name= 'PLACE_READ')
                );
insert into role_authority (role_id, authority_id)
values (
        (select rol_id from role where rol_name='ROLE_OFFICE_MANAGER'),
        (select au_id from authority where au_name= 'PLACE_WRITE')
                );
insert into role_authority (role_id, authority_id)
values (
         (select rol_id from role where rol_name='ROLE_OFFICE_MANAGER'),
         (select au_id from authority where au_name= 'PLACE_DELETE')
                );