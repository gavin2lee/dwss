package main;

import (
  "fmt"
)

import "reflect"

func main(){
  testEmbedType()
  fmt.Println("========== test closure =============")
  testClosure()
}

var count int = 0;

func increaseCount()(func()){
  var internalCount int = 0;
  f := func(){
    fmt.Printf("internalCount:%d\n",internalCount)
    internalCount++
    fmt.Printf("count:%d\n", count)
    count++
  }

  return f
}

func testClosure(){
  for i:=0;i < 10; i++ {
    increaseCount()()
  }

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

type IAnimal interface{
  breathe()
}

type animal struct{
  name string
  id int64
}

func (a *animal) breathe(){
  fmt.Printf("%s breathing...\n",a.name)

  //fmt.Println(a.(*cat))
}

type mammal struct {
  *animal
  age int
}

type cat struct {
  *mammal
  color string
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


  fmt.Println("====animal ======")
  animal1 := &animal{"Jim",1000}
  mammal1 := &mammal{age:10,animal:animal1}

  fmt.Println(mammal1)
  cat1 := &cat{color:"black",mammal:mammal1}

  fmt.Printf("type of cat:%s\n",reflect.TypeOf(cat1))

  cat1.breathe()

  fmt.Println("===== infomation of cat1 ======")

  fmt.Printf("cat1.name:%s\n",cat1.name)
  fmt.Println("&cat1:", &cat1)
  fmt.Println("&cat1.name", &cat1.name)
  fmt.Println("&cat1.age", &cat1.age)
  fmt.Println("&cat1.color", &cat1.color)

  fmt.Println("===== interface  ===========")
  var ianimal IAnimal = &cat1
  ianimal.breathe()
}
