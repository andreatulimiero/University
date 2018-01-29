SELECT linguaggio, avg(num_programmatori)
FROM (
	SELECT id, count(codice) as num_programmatori
	FROM programma natural join autore
	group by id
	) as programma_num_programmatori
	natural join programma
GROUP BY linguaggio