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