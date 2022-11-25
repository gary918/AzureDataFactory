INSERT INTO ctl.JobControl ([SourceContainer],[TargetContainer],[CustomerID],[EnabledFlag],[LoadStatus],[LatestExecutionFlag]) VALUES ('source','target','customer1',1,'Stop',1);

delete from [ctl].[JobControl] where CustomerID='customer2'

INSERT INTO ctl.JobControl ([SourceContainer],[TargetContainer],[CustomerID],[EnabledFlag],[LoadStatus],[LatestExecutionFlag]) VALUES ('source','target','customer2',1,'Stop',1);