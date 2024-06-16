#!/bin/bash
# Drop database completelly

# TODO: change to use .env
/opt/mssql-tools/bin/sqlcmd -S localhost,1433 -U sa -P "yourStrong(!)Password" -Q "use master; drop database lutris; create database lutris;"