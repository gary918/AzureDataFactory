/****** Object:  Table [ctl].[JobControl]    Script Date: 22/07/2021 2:55:37 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
DROP TABLE IF EXISTS [ctl].[jobcontrol]
CREATE TABLE [ctl].[JobControl](
	[JobControlID] [int] IDENTITY(1,1) PRIMARY KEY,
	[SourceContainer] [varchar](50) NULL,
	[TargetContainer] [varchar](50) NULL,
	[CustomerID] [varchar](1500) NULL,
	[EnabledFlag] [bit] NULL,
	[LoadStatus] [varchar](20) NULL,
	[LatestExecutionFlag] [bit] NULL)
