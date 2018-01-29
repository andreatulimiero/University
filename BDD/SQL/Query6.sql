SELECT codice, min(anno)
FROM programmatore NATURAL JOIN autore NATURAL JOIN programma
WHERE programmatore.categoria = 10 AND programma.linguaggio != 'Java'
group by codice