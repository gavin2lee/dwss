package main;

import (
  "fmt"
)

func main(){
  testEmbedType()
}


type user struct{
  id int64
  name string
}

func (u *user) notify(){
  fmt.Printf("Send message to %s...\n",u.name)
}

type admin struct{
  user
  level int64
}

func testEmbedType(){
  fmt.Println("test embed type...")

  admin1 := admin{
    user : user {
      id:100,
      name:"Jim",
    },
    level:1001,
  }

  admin1.user.notify()
  fmt.Println("===========")

  admin1.notify()
}
