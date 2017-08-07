package main;

import (
  "fmt"
  "net"
  "os"
  "time"
)

var port string = ":9009"


func checkError(err error){
  // fmt.Println(err)
  if err != nil {
    fmt.Fprintf(os.Stderr,"Fatal error:%s \n", err.Error())
    os.Exit(1)
  }
}


func main(){
  addr,err := net.ResolveTCPAddr("tcp4", port)
  checkError(err)

  listener,err := net.ListenTCP("tcp",addr)
  checkError(err)

  for {
    fmt.Fprintf(os.Stdout,"Server listen on %s%s \n", addr.String(),port)
    conn,err := listener.Accept()

    fmt.Println("conn:",conn)
    fmt.Println("err:",err)

    if err != nil {
      continue
    }

    daytime := time.Now().String()

    conn.Write([]byte(daytime))
    conn.Close()
  }

}
