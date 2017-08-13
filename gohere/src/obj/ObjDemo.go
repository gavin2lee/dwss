package main;

import (
  "fmt"
  "errors"
)

func main(){
  testAnimal()
}

type IAnimal interface{
  Breathe() (canBreathe bool,err error)
}

type Animal struct{
  name string
  alive bool
}

func (a Animal) Breathe()(canBreathe bool,err error){
  localCanBreathe := true
  var localErr error = nil
  if !a.alive {
    fmt.Printf("%s is not yet alive\n", a.name)
    localCanBreathe = false
    localErr = errors.New("not alive")
  }else{
    fmt.Printf("%s breathing...\n", a.name)
    localCanBreathe = true
    localErr = nil
  }

  return localCanBreathe,localErr
}

func testAnimal(){
  fmt.Println("==== testAnimal =====")
  fmt.Println("set alive true")
  var ianimal = Animal{"Bob",true}
  fmt.Println("ianimal", ianimal)
  canBreathe,err := ianimal.Breathe()
  fmt.Println(canBreathe,err)

  fmt.Println("set alive false")
  var animal = ianimal
  animal.alive = false
  fmt.Println("animal", animal)
  canBreathe,err = animal.Breathe()
  fmt.Println(canBreathe,err)
}
