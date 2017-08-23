package main;

import(
  "fmt"
  "net"
  "os"

  "time"

  "common/objutils"
)

func startClient(clientName string,ch chan int){
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
  for i:=0;i<10;i++ {
    _,err := conn.Write([]byte(fmt.Sprintf("Hello Server! I'm %s-%d \n",clientName, i)))
    objutils.CheckError(err)

    n,err := conn.Read(buf[0:])
    objutils.CheckError(err)

    fmt.Println(clientName + " - Reply from server ", rAddr.String(), string(buf[0:n]))

    time.Sleep(1 * time.Second)
  }


  conn.Close()

  ch <- 1
  return
}

func main(){
  clientNames := []string{"A","B","C","D","E","F"}
  chs := make([]chan int,len(clientNames))
  for i:=0; i<len(clientNames);i++{
    chs[i] = make(chan int)
    go startClient(clientNames[i],chs[i])
  }

  retNum := 0
  for _,ch := range chs {
    val := <- ch
    retNum = retNum + val
  }

  fmt.Println("retNum: ", retNum)

  os.Exit(0)
}
