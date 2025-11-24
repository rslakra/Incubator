#Author: Rohtash Lakra
#!/bin/bash
set -e # terminate execution on command failure
#Usage:
# ./renameDatabase red_tech Red30Tech true
#./renameDatabase red_tech Red30Tech
#
# connect to database
mySqlConn="mysql -u root"
OLD_DB=$1
NEW_DB=$2
flag=${3:-false}
echo
echo "Databases - Old:${OLD_DB}, New:${NEW_DB}, flag:${flag}"
echo
# open old database
queryOpenOrCreateDb="USE ${OLD_DB}"
# check create a new db or open existing db
if [[ "$flag" == true ]]; then
    queryOpenOrCreateDb="CREATE DATABASE ${NEW_DB}"
    #$mysqlconn -e "${queryOpenOrCreateDb}"
fi
echo "${queryOpenOrCreateDb}"
$mySqlConn -e "${queryOpenOrCreateDb}";
echo
# get all the tables in the old db
tableNames=$($mySqlConn -N -e "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES \
                           WHERE table_schema='${OLD_DB}'")
#iterate each table
for tableName in $tableNames; do
    renTableQuery="RENAME TABLE ${OLD_DB}.${tableName} to ${NEW_DB}.${tableName}"
    echo "${renTableQuery}"
    $mySqlConn -e "${renTableQuery}";
done;
echo
# drop old database if new db is created
if [[ "$flag" == true ]]; then
    dropDbQuery="DROP DATABASE ${OLD_DB}"
    echo "${dropDbQuery}"
    $mySqlConn -e "${dropDbQuery}";
fi
echo
