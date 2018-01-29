SELECT DISTINCT CODICE
FROM autore NATURAL JOIN programma
WHERE programma.linguaggio = 'Java' AND programma.anno > 2000