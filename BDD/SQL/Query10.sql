with autore_num_prog as(
	SELECT codice, count(*) as num_programmi
	FROM autore
	group by codice
)

SELECT a1, a2
from autore_num_prog a1 join autore_num_prog a2 on a1.num_programmi = a2.num_programmi
where a1.codice < a2.codice