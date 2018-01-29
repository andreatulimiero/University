SELECT linguaggio, count(autore.codice)::numeric / count(distinct programma.id) as media_programmatori
FROM programma join autore on programma.id = autore.id
GROUP BY linguaggio