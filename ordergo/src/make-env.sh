#!/bin/sh

if [ -z "$GOPATH" ]; then
    export GOPATH=/home/gavin/Dev/GitRepo/dwss/ordergo
    
    echo "GOBIN $GOBIN"
else
    export GOPATH=$GOPATH:/home/gavin/Dev/GitRepo/dwss/ordergo
fi
if [ "$GOBIN" = "" ]; then
    export GOBIN=/home/gavin/Dev/GitRepo/dwss/ordergo/bin
    echo "GOBIN <$GOBIN>"
else
    export GOBIN=$GOBIN:/home/gavin/Dev/GitRepo/dwss/ordergo/bin
    echo "GOBIN <<$GOBIN>>"
fi
echo "GOPATH $GOPATH"
echo "GOBIN $GOBIN"

#source
