UPDATE users SET us_position='programmer',
    us_department='development',
    us_location='Belarus,Grodno',
    us_phone= '+375298730987',
    us_skype='programmer',
    us_hr='Sergey Petrov'
WHERE us_username='user';
UPDATE users SET us_position='director',
    us_department='management',
    us_location='Belarus, Minsk',
    us_phone='+375298730933',
    us_skype='director',
    us_hr='Sergey Petrov'
WHERE us_username='admin';
UPDATE users SET us_position='hr',
    us_department='human resources',
    us_location='Belarus, Grodno',
    us_phone='+375298765987',
    us_skype='hr',
    us_hr='Sergey Petrov'
WHERE us_username='hr';
UPDATE users SET us_position='office manager',
    us_department='management',
    us_location='Belarus, Vitebsk',
    us_phone='+375298776987',
    us_skype='office manager',
    us_hr='Sergey Petrov'
WHERE us_username='om';
