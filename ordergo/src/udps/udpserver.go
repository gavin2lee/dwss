package main 

import (
    "fmt"
    "net"
//    "os"
    
    "common/objutils"
)

func main() {
    service := ":10001"
    udpAddr,err := net.ResolveUDPAddr("udp", service)
    objutils.CheckError(err)
    
    conn,err := net.ListenUDP("udp", udpAddr)
    objutils.CheckError(err)
    
    fmt.Println("udp listen " + service)
    
    for{
        handleUdpClient(conn)
    }
}

func handleUdpClient(conn * net.UDPConn){
    var buf [512]byte
    n,addr,err := conn.ReadFromUDP(buf[0:])
    
    if err != nil {
        fmt.Println(err)
        return
    }
    
    fmt.Println("Recv from client ", addr.String(), string(buf[0:n]))
    conn.WriteToUDP([]byte("Welcome Client"), addr)
}

