with vista as (
select codice, linguaggio, count(*) as num
from autore natural join  programma
group by codice, linguaggio
)
select codice, linguaggio
from vista v1
where v1.num >= ALL (
	select v2.num
	from vista v2
	where v1.codice = v2.codice
)
order by codice