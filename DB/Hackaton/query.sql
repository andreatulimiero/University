-- Normalizzazione dati
ALTER TABLE comune ALTER COLUMN regione TYPE varchar USING CAST
(regexp_replace(regione, ’-’, ’ ’) AS varchar);

-- View
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

-- Query
select digit.regione, digit.digitalizzazione_generale, integrazione.perc_stranieri from
(select dig_0.regione, (dig_0.deg_dig*5) + (dig_1.deg_dig*4) + (dig_2.deg_dig*3) + (dig_3.deg_dig*2) + (dig_4.deg_dig*1) as digitalizzazione_generale
from dig_0 join dig_1 on dig_0.regione = dig_1.regione join dig_2 on dig_2.regione = dig_0.regione join dig_3 on dig_3.regione = dig_0.regione join dig_4 on dig_4.regione = dig_0.regione
group by dig_0.regione, dig_0.deg_dig, dig_1.deg_dig, dig_2.deg_dig, dig_3.deg_dig, dig_4.deg_dig) 
as digit join
(select UPPER(regione) as regione, sum(popresidente) as residenti, sum(popstraniera) as stranieri, round((sum(popstraniera::decimal)*100)/sum(popresidente::decimal), 3) as perc_stranieri
from comune
group by regione)
as integrazione
on digit.regione = integrazione.regione
group by digit.regione, digit.digitalizzazione_generale, integrazione.perc_stranieri 
order by digit.digitalizzazione_generale desc