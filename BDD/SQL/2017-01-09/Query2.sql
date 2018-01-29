SELECT codice
FROM (scuola JOIN provincia ON scuola.provincia = provincia.codprovincia) JOIN esito ON esito.codscuola = scuola.codice
WHERE esito.anno > 2010 and esito.bocciati < esito.promossi / 2