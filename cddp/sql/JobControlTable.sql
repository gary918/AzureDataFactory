-- Create the schema
IF (NOT EXISTS (SELECT * FROM sys.schemas WHERE name = 'ctl')) 
BEGIN
EXEC ('CREATE SCHEMA [ctl]')
END

-- Create the table
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
DROP TABLE IF EXISTS [ctl].[jobcontrol]
CREATE TABLE [ctl].[JobControl](
	[JobControlID] [int] IDENTITY(1,1) PRIMARY KEY,
	[CustomerID] [varchar](100) NULL,
	[AppID] [varchar](100) NULL,
	[EnabledFlag] [bit] NULL DEFAULT 1,
	[LoadStatus] [varchar](20) NULL DEFAULT 'stopped',
	[LatestExecutionFlag] [bit] NULL DEFAULT 1)

-- Insert the table
INSERT INTO ctl.JobControl ([CustomerID],[AppID],[EnabledFlag],[LoadStatus],[LatestExecutionFlag]) VALUES ('customer1','app1',1,'Stopped',1);
INSERT INTO ctl.JobControl ([CustomerID],[AppID],[EnabledFlag],[LoadStatus],[LatestExecutionFlag]) VALUES ('customer1','app2',1,'Stopped',1);