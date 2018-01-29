SELECT codice, anno, count(*)
FROM programma natural join autore natural join programmatore
group by codice, anno
