create table comune( Comune varchar, ISTAT varchar, Provincia varchar, SiglaProv varchar, Regione varchar, AreaGeo
varchar, PopResidente integer, PopStraniera integer, DensitaDemografica varchar, SuperficieKmq varchar, AltezzaCentro
integer, AltezzaMinima integer, AltezzaMassima integer, ZonaAltimetrica varchar, TipoComune varchar, GradoUrbaniz
varchar, IndiceMontanita varchar, ZonaClimatica varchar, ZonaSismica varchar, ClasseComune varchar, Latitudine varchar,
Longitudine varchar );

COPY comune FROM 'C:\\Users\tulim\Works\university\DB\Hackaton\comuni.csv' WITH CSV DELIMITER AS ';' HEADER;