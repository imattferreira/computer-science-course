#!/bin/bash
# Call this script to populate database with dummy data

filename='/tmp/seeds/seeds.sql'
# TODO: change to use .env
/opt/mssql-tools/bin/sqlcmd -S localhost,1433 -U sa -P "yourStrong(!)Password" -Q "use lutris; $(cat $filename | tr -d '\n')"