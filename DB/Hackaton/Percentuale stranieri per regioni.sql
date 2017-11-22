select regione, sum(popresidente) as residenti, sum(popstraniera) as stranieri, round((sum(popstraniera::decimal)*100)/sum(popresidente::decimal), 3) as perc_stranieri
from comune
group by regione
order by perc_stranieri desc