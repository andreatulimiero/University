﻿create table scuola ( ANNOSCOLASTICO varchar, AREAGEOGRAFICA varchar, REGIONE varchar, PROVINCIA varchar,
CODICEISTITUTORIFERIMENTO varchar, DENOMINAZIONEISTITUTORIFERIMENTO varchar, CODICESCUOLA
varchar, DENOMINAZIONESCUOLA varchar, INDIRIZZOSCUOLA varchar, CAPSCUOLA varchar,
CODICECOMUNESCUOLA varchar, DESCRIZIONECOMUNE varchar, DESCRIZIONECARATTERISTICASCUOLA
varchar, DESCRIZIONETIPOLOGIAGRADOISTRUZIONESCUOLA varchar, INDICAZIONESEDEDIRETTIVO varchar,
INDICAZIONESEDEOMNICOMPRENSIVO varchar, INDIRIZZOEMAILSCUOLA varchar, INDIRIZZOPECSCUOLA
varchar, SITOWEBSCUOLA varchar );

COPY scuola FROM 'C:\\Users\tulim\Works\university\DB\Hackaton\scuole.csv' WITH
CSV
DELIMITER AS ','
NULL AS 'Non Disponibile'
HEADER;