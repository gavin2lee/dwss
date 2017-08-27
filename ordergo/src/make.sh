#!/bin/sh

echo "Copy resources to bin"
cp -frv views ../bin/

echo "compile httpsvr"
go install web/httpsvr

if [ "$1" = "start" ]; then
	echo "start http server"
	cd ../bin
	echo `pwd`
    ./httpsvr
fi