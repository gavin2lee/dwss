#!/bin/sh
echo "Prepare environment variables"
chmod u+x "set-env.sh"
. "./set-env.sh"

echo "GOPATH $GOPATH"
echo "GOBIN $GOBIN"

echo "clean bin directory"
rm -frv ../bin/*

echo "Copy resources to bin"
cp -frv views ../bin/

echo "compile httpsvr"
go install web/httpsvr

StartHttpsvr()
{
	echo "start http server"
	cd ../bin
	echo `pwd`
    ./httpsvr $*
}

echo "command:$1"
httpsvrPid=`ps -ef | grep httpsvr | grep -v grep | awk '{print $2}'`
if [ "$1" = "start" ]; then
	if [ -n "$httpsvrPid" ]; then
		echo "httpsvrPid<$httpsvrPid> already exists"
		exit 1
	else
		StartHttpsvr
	fi
fi

if [ "$1" = "restart" ]; then
	if [ -n "$httpsvrPid" ]; then
		echo "httpsvrPid<$httpsvrPid> already exists and go to stop"
		kill -9 $httpsvrPid
	fi
	
	StartHttpsvr
fi

echo "successfully built"