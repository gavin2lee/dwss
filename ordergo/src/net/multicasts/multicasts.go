package main

import (
	"fmt"
	"net"

	"common/objutils"
)

func main() {
	port := 12356

	gaddr := net.UDPAddr{
		IP:   net.IPv4(224, 3, 5, 6),
		Port: port,
	}

	fmt.Println("Listen ", gaddr)
	conn, err := net.ListenMulticastUDP("udp", nil, &gaddr)

	objutils.CheckError(err)

	for {
		handleUdpClient(conn)
	}
}

func handleUdpClient(conn *net.UDPConn) {
	var buf [512]byte
	n, addr, err := conn.ReadFromUDP(buf[0:])

	if err != nil {
		fmt.Println(err)
		return
	}

	fmt.Println("Recv from client ", addr.String(), string(buf[0:n]))
	n, err = conn.WriteToUDP([]byte("Welcome Client"), addr)

	if err != nil {
		fmt.Println("State:", n, "-", err)
	}

	raddr := net.UDPAddr{
		IP:   net.IPv4(192, 168, 0, 103),
		Port: 10002,
	}

	udpConn, err := net.DialUDP("udp", nil, &raddr)
	if err != nil {
		fmt.Println("dial", err)
		return
	}

	_, err = udpConn.Write([]byte("Welcome client"))
	if err != nil {
		fmt.Println("write", err)
	}
	udpConn.Close()

	fmt.Println("resp ")
}
