/****** Object:  Schema [ctl]    Script Date: 11-02-2021 12:12:24 ******/
IF (NOT EXISTS (SELECT * FROM sys.schemas WHERE name = 'ctl')) 
BEGIN
EXEC ('CREATE SCHEMA [ctl]')
END
