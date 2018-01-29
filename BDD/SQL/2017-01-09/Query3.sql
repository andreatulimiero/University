SELECT provincia, anno, sum(promossi)
FROM (provincia JOIN scuola ON provincia.codprovincia = scuola.provincia) JOIN esito ON esito.codscuola = scuola.codice
WHERE scuola.tipo = 'classico' or scuola.tipo = 'scientifico'
group by provincia, anno