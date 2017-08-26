package main 

import (
    "fmt"
    "net"
    
    "strconv"
    "time"
    
    "common/objutils"
)

func main() {
    port := 12356
    service := ":10002"
    
    gaddr := net.UDPAddr{
        IP: net.IPv4(224, 3, 5, 6),
        Port:port,
    }
    
    fmt.Println("Send to ", gaddr)
    
    var round int64
	round = 0
	
	udpAddr,err := net.ResolveUDPAddr("udp", service)
    objutils.CheckError(err)
    
    udpConn,err := net.ListenUDP("udp", udpAddr)
    objutils.CheckError(err)
    
    

	conn, err := net.DialUDP("udp", nil, &gaddr)

	objutils.CheckError(err)
	
	fmt.Println(udpAddr.String())
	
	for {
		round = round + 1
		_,err = conn.Write([]byte(udpAddr.String() + "|" + strconv.FormatInt(round, 10)))
		objutils.CheckError(err)
		
		fmt.Println("SEND ", round)


		var buf [512]byte
		
		

        udpConn.SetReadDeadline(time.Now().Add(3*time.Second))
		n, addr, err := udpConn.ReadFromUDP(buf[0:])
		if err != nil {
		    fmt.Println(err)
		}

		fmt.Println("Reply from server ", addr.String(), string(buf[0:n]))

		
		time.Sleep(5*time.Second)
	}
}

