SELECT codice, nome
FROM programmatore
WHERE codice not in (
				SELECT codice
				FROM autore natural join programma
				WHERE programma.linguaggio != 'Java'
			)