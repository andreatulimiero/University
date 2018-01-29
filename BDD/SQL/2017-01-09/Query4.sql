SELECT scuola
FROM scuola
WHERE scuola not in (
	SELECT scuola
	FROM scuola JOIN esito ON scuola.codice = esito.codscuola
	WHERE esito.bocciati > esito.promossi
)