package main;

import (
  "fmt"
  "errors"
)

type IAnimal interface{
  Breathe() (canBreathe bool,err error)
}

type Animal struct{
  name string
  alive bool
}

type Mammal struct {
  Animal
  age int
}

type Bird struct{
  Animal
  category string
}

type Cat struct{
  Mammal
  color string
}

type Owner struct{
  name string
  gender string
  age int
}

type Dog struct {
  *Mammal
  owner Owner
}

func main(){
  testAnimal()
  testMammal()
  testCat()
  testDog()
}

func testDog(){
  fmt.Println("==== testDog ====")
  var dog = Dog{&Mammal{Animal{"DogCalvin",true},20},Owner{"Bob","male",50}}
  fmt.Println("Dog:",dog)
  fmt.Println("Mammal:", *dog.Mammal)
}

func testCat(){
  fmt.Println("==== testCat ====")
  var cat = Cat{Mammal{Animal{"CatJim",true},10},"black"}
  var pcat *Cat = new(Cat)
  *pcat = cat
  fmt.Println("cat:",cat)
  fmt.Println("&cat:", &cat)
  fmt.Println("pcat:", pcat)

  fmt.Printf("&cat:%d\n", &cat)
  fmt.Printf("pcat:%d\n", pcat)
}

func testMammal(){
  fmt.Println("==== testMammal ====")
  var ianimal = Mammal{age:10}
  fmt.Println(ianimal)
  var mammal = ianimal
  mammal.Animal = Animal{"Calvin", true}
  fmt.Println("init animal object of mammal")
  fmt.Println(mammal)
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

  fmt.Println("assign to pointer")
  panimal := &animal
  fmt.Printf("panimal:%s\n",panimal)
  fmt.Println(panimal)
  pianimal := &ianimal
  fmt.Printf("pianimal:%s\n", pianimal)
  fmt.Println(pianimal)
}
