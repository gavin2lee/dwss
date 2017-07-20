#!/bin/bash

echo $0 $1;


declare -a excludedFiles="'aaa.txt' 'zookeeper-3.4.9.tar.gz'";
#zookeeper-3.4.9.tar.gz

#excludedFiles[1]="ccc.txt";
echo "array[0]"${excludedFiles}:0;
echo "array[1]"${excludedFiles[1]};

checkExcludesFile(){
  echo "count:$# $1";
  targetFile=$1;
  for exFile in $excludedFiles; do
    echo "check $exFile";
    arg=$
    if [ "$exFile" = "$targetFile" ];then
        echo "excluded!";
        return 1;
    fi
  done;
  echo "not excluded";
  return 0;
}

subFiles=`ls $1`;
CLASSPATH="";

for subFile in $subFiles; do
  #echo $subFile;
  checkExcludesFile $subFile;
  ret=$?
  echo "return:"$ret
  if [ "$ret" == "1" ]; then
    echo "bad news";
    continue;
  fi;
  CLASSPATH=$subFile:$CLASSPATH
done;

echo $CLASSPATH
