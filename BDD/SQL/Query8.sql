SELECT PPL.linguaggio, PPL.codice
FROM (
	SELECT codice, linguaggio, count(*) as no
	FROM autore NATURAL JOIN programma
	GROUP BY codice, linguaggio
	) as PPL
where no >= ALL(
	SELECT count(*)
	FROM autore NATURAL JOIN programma
	GROUP BY codice, linguaggio
	HAVING PPL.linguaggio = linguaggio
)