#!/bin/sh

curDir=`pwd`
projectBaseDir=`cd ..&&pwd`
projectName=`echo $projectBaseDir | awk -F '/' '{print $(NF)}'`
#projectBaseDir=/home/gavin/Dev/GitRepo/dwss/ordergo

echo "projectName:$projectName"
echo "projectBaseDir:$projectBaseDir"

SetGobin()
{
	echo "set gobin env"
	if [ "$GOBIN" = "" ]; then
    	export GOBIN=$projectBaseDir/bin
	else
    	export GOBIN=$GOBIN:$projectBaseDir/bin
	fi
}

SetGopath()
{
	echo "set gopath env"
	if [ -z "$GOPATH" ]; then
    	export GOPATH=$projectBaseDir
	else
    	export GOPATH=$GOPATH:$projectBaseDir
	fi
}

containGoPathRet=`echo "$GOPATH" | grep "$projectName" | awk '{print $1}'`

if [ -z "$containGoPathRet" ]; then
	SetGopath
else
    echo "$projectName already contained in GOPATH"
fi

containGoBinRet=`echo "$GOBIN" | grep "$projectName" | awk '{print $1}'`

if [ -z "$containGoBinRet" ]; then
	SetGobin
else
	echo "$projectName already contained in GOBIN"
fi

echo "GOPATH $GOPATH"
echo "GOBIN $GOBIN"

echo "Set env finished"
#source
