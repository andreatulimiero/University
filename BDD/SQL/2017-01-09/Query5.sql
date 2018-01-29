SELECT distinct regione
FROM (scuola JOIN provincia ON scuola.provincia = provincia.codprovincia) JOIN esito ON esito.codscuola = scuola.codice
GROUP BY regione, codprovincia
HAVING sum(promossi) < sum(bocciati)