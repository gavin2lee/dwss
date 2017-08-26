package main

import (
	"fmt"
	"net"

	"strconv"
	"time"

	"common/objutils"
)

func main() {
	var round int64
	round = 0
	
	laddr := net.UDPAddr{
	    IP: net.IPv4(192, 168, 0, 103),
	    Port: 10002,
	}

	raddr := net.UDPAddr{
		IP:   net.IPv4(255, 255, 255, 255),
		Port: 10001,
	}

	conn, err := net.DialUDP("udp", &laddr, &raddr)

	objutils.CheckError(err)
	
	listenConn,err := net.ListenUDP("udp", &laddr)
    objutils.CheckError(err)
    
    fmt.Println("listen ", laddr)

	for {
		round = round + 1
		_,err = conn.Write([]byte("hi server " + strconv.FormatInt(round, 10)))
		objutils.CheckError(err)
		
		fmt.Println("SEND ", round)


		var buf [512]byte
		
		

		n, addr, err := listenConn.ReadFromUDP(buf[0:])
		objutils.CheckError(err)

		fmt.Println("Reply from server ", addr.String(), string(buf[0:n]))

		
		time.Sleep(1*time.Second)
	}
}
