package main;

import (
  "fmt"
  "math/rand"
  "sync"
  "time"
)

var wg sync.WaitGroup

func init(){
  fmt.Println("ball player init")
  rand.Seed(time.Now().UnixNano())
}

func player(name string,court chan int){
  defer wg.Done()

  for {
    ball,status := <- court

    if !status {
      fmt.Printf("Player %s won\n", name)
      return
    }

    n := rand.Intn(100)

    fmt.Println(n)

    if n % 13 == 0 {
      fmt.Printf("Player %s missed\n", name)

      close(court)
      return
    }

    fmt.Printf("Player %s hit %d \n", name, ball)
    ball++

    court <- ball
  }
}

func main(){
  court := make(chan int)
  wg.Add(2)

  go player("Alan", court)
  go player("Bob", court)

  court <- 1

  wg.Wait()
}
