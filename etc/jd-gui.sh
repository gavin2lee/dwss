#!/bin/bash
echo $0
echo `pwd`
cd .
echo `pwd`
DIR=`S=\`readlink "$0"\`; [ -z "$S" ] && S=$0; dirname $S`
echo ${DIR}
cd ${DIR}
echo `pwd`
java -jar /opt/jd-gui/jd-gui.jar &
