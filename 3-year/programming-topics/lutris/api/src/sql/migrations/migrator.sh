#!/bin/bash
# Call this script to recreate database structure

# TODO: change to use .env
for filename in /tmp/migrations/*.sql; do
    /opt/mssql-tools/bin/sqlcmd -S localhost,1433 -U sa -P "yourStrong(!)Password" -Q "use lutris; $(cat $filename | tr -d '\n')"
done
