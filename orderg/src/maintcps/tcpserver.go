package main;

import(
  "fmt"
  "net"
  "os"
  "common/objutils"
)

func main(){
  service := ":9008"
  tcpAddr,err := net.ResolveTCPAddr("tcp",service)

  objutils.CheckError(err)

  listener,err := net.ListenTCP("tcp",tcpAddr)

  objutils.CheckError(err)

  fmt.Println("=== tcpserver started ===")
  fmt.Println("listening ",tcpAddr.String())

  for{
    conn,err := listener.Accept()
    if err != nil {
      fmt.Fprintf(os.Stderr,"Fatal error:%s\n",err.Error())
      continue
    }
    handleClient(conn)
    conn.Close()
  }

}

func handleClient(conn net.Conn){
  fmt.Println("=== handleClient ===")
  var buf [512]byte
  for{
    n,err := conn.Read(buf[0:])
    if err != nil {
      fmt.Fprintf(os.Stderr,"Fatal error:%s\n",err.Error())
      return
    }

    rAddr := conn.RemoteAddr()
    fmt.Println("RECV from client", rAddr.String(),string(buf[0:n]))
    _,err2 := conn.Write([]byte("welcome client!"))
    if err2 != nil {
      fmt.Fprintf(os.Stderr,"Fatal error:%s\n",err2.Error())
      return
    }
  }
}
