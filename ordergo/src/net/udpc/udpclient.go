package main 

/*
UDP Client

*/
import (
    "fmt"
    "net"
    "os"
    
    "common/objutils"
)

func main() {
    if len(os.Args) != 2 {
        fmt.Fprintf(os.Stderr, "Usage : %s host:port", os.Args[0])
        os.Exit(1)
    }
    
    service := os.Args[1]
    
    udpAddr,err := net.ResolveUDPAddr("udp", service)
    objutils.CheckError(err)
    
    conn,err := net.DialUDP("udp", nil, udpAddr)
    objutils.CheckError(err)
    
    _,err = conn.Write([]byte("Hello Server!"))
    objutils.CheckError(err)
    
    var buf [512]byte
    
    n,addr,err := conn.ReadFromUDP(buf[0:])
    objutils.CheckError(err)
    
    fmt.Println("Reply from server ", addr.String(), string(buf[0:n]))
    
    conn.Close()
    
    os.Exit(0)
}

