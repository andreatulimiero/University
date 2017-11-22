ALTER TABLE comune ALTER COLUMN DensitaDemografica TYPE numeric USING CAST
(regexp_replace(DensitaDemografica, ',', '.') AS numeric);
ALTER TABLE comune ALTER COLUMN SuperficieKmq TYPE numeric USING CAST (regexp_replace(SuperficieKmq,',',
'.') AS numeric);
ALTER TABLE comune ALTER COLUMN Latitudine TYPE numeric USING CAST (regexp_replace(Latitudine, ',', '.') AS
numeric);
ALTER TABLE comune ALTER COLUMN Longitudine TYPE numeric USING CAST (regexp_replace(Longitudine, ',', '.')
AS numeric);