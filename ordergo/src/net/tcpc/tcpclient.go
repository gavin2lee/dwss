package main;

import(
  "fmt"
  "net"
  "os"
  "common/objutils"
)

func main(){
  var buf [512]byte

  if len(os.Args) != 2 {
    fmt.Fprintf(os.Stderr,"Usage:%s host:port\n", os.Args[0])
    os.Exit(1)
  }

  service := os.Args[1]

  tcpAddr,err := net.ResolveTCPAddr("tcp",service)
  objutils.CheckError(err)

  conn,err := net.DialTCP("tcp",nil,tcpAddr)
  objutils.CheckError(err)

  rAddr := conn.RemoteAddr()
  n,err := conn.Write([]byte("Hello Server!"))
  objutils.CheckError(err)

  n,err = conn.Read(buf[0:])
  objutils.CheckError(err)

  fmt.Println("Reply from server ", rAddr.String(), string(buf[0:n]))
  conn.Close()

  os.Exit(0)
}
