#!/bin/sh
#kill process according to process name
if [ $# -lt 1 ]
then
  echo "procedure_name is must not null"
  echo "default procedure_name is SpringBootDemo-1.0-SNAPSHOT.jar"
  NAME="SpringBootDemo-1.0-SNAPSHOT.jar"
  #exit 1
else
  NAME=$1
fi

PROCESS=`ps -ef|grep "$NAME"|grep -v grep|grep -v PPID|awk '{ print $2}'`
for i in $PROCESS
do
  echo "kill the $NAME process [ $i ]"
  kill -9 $i
done
