create or replace view dig_0 as 
(
select regione, count(*) as deg_dig from scuola where indirizzopecscuola is not null and indirizzoemailscuola is not null and sitowebscuola is not null
group by regione
);

create or replace view dig_1 as 
(
select regione, count(*) as deg_dig from scuola where indirizzopecscuola is null and indirizzoemailscuola is not null and sitowebscuola is not null
group by regione
);

create or replace view dig_2 as 
(
select regione, count(*) as deg_dig from scuola where indirizzopecscuola is null and indirizzoemailscuola is null and sitowebscuola is not null
group by regione
);

create or replace view dig_3 as 
(
select regione, count(*) as deg_dig from scuola where indirizzopecscuola is null and sitowebscuola is null and indirizzoemailscuola is not null
group by regione
);

create or replace view dig_4 as
(
select regione, count(*) as deg_dig from scuola where indirizzopecscuola is null and indirizzoemailscuola is null and sitowebscuola is null
group by regione
);