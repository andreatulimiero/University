SELECT codice, nome, promossi
FROM scuola JOIN esito ON esito.codscuola = scuola.codice
WHERE scuola.provincia = 15 and esito.anno = 2015