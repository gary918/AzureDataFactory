# Data Ingestion by Using Azure Data Factory
To copy data from on-premise environment, we need to have a self-hosted integration runtime (IR). Refer to the [video](https://www.youtube.com/watch?v=weiHOeje-QA) for more detailed introduction.
<div style="align: center">
<img src="images/self-IR.PNG" alt="architecture">
</div>

<p style="text-align: center;">Figure 1. Self-hosted IR</p>

## Copy Data From SQL Server to Azure Blob Storage
* Need to have a separate server for the self-hosted IR.
* [Copy data to and from SQL Server by using Azure Data Factory](https://docs.microsoft.com/en-us/azure/data-factory/connector-sql-server)
* [Copy data from and to ODBC data stores using Azure Data Factory](https://docs.microsoft.com/en-us/azure/data-factory/connector-odbc)
## Copy Data From PostgreSQL Server to Azure Blob Storage
* To simulate a on-premise PostgreSQL server, you may [install a server on Azure VM](https://docs.microsoft.com/en-us/azure/virtual-machines/linux/postgresql-install). 
* Test the self-hosted IR's network connection with PostgreSQL server. Use the PowerShell command:
```
   Test-NetConnection <postgresql_server_ip> -port <port>
```
* If the TCP connection or Ping fail, run the following command in the PostgreSQL server.
```
   iptables -I INPUT 1 -m tcp -p tcp --dport 5432 -j ACCEPT
```
* Install ODBC Driver for PostgreSQL in self-hosted IR
* Configure ODBC for PostgreSQL
* Follow the steps [here](https://docs.microsoft.com/en-us/azure/data-factory/connector-postgresql).
## Copy Data From Local File System to Azure Blob Storage
* data file -> Azure Blob Storage -> ADF -> Azure Blob Storage/ADLS
https://www.sqlservercentral.com/articles/azure-data-factory-your-first-data-pipeline
* data file -> shared files on self-hosted IR -> ADF -> Azure Blob Storage/ADLS
* data file -> remote shared folder -> ADF -> Azure Blob Storage/ADLS
https://docs.microsoft.com/en-us/azure/data-factory/connector-file-system