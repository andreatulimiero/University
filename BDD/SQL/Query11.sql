WITH prog_esp_num as (
	SELECT codice, count(distinct linguaggio) as num_esp
	FROM autore natural join programma
	group by codice
)

SELECT p1.codice, p2.codice
FROM prog_esp_num p1 join prog_esp_num p2 on p1.num_esp = p2.num_esp
WHERE p1.codice < p2.codice