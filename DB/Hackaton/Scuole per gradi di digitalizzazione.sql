select *
from 
(select * from scuola where indirizzopecscuola is not null and indirizzoemailscuola is not null and sitowebscuola is not null) as dig_0,
(select * from scuola where indirizzoemailscuola is not null and sitowebscuola is not null) as dig_1 except dig_0,
(select * from scuola where sitowebscuola is not null) as dig_2 except dig_1,
(select * from scuola where indirizzoemailscuola is not null) as dig_3 except dig_1
(select * from scuola) as dig_4 except dig_0