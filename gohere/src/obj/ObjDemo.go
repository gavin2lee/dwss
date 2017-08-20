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

type Speaker interface{
  SayHi()
  Break()
}

type Learner interface{
  Study()
}

type People struct{
  name string
}

func (p People) SayHi(){
  fmt.Printf("%s Say Hi right now\n",p.name)
}

/*
func (p *People) Break(){
  fmt.Printf("%s Break 1 right now\n", p.name)
}*/
//*
func (p People) Break(){
  fmt.Printf("%s Break 2 right now\n", p.name)
}
//*/
func main(){
  testAnimal()
  testMammal()
  testCat()
  testDog()
  testPeople()
  testDeduceType1()
  testDeduceType2()
}

func testDeduceType2(){
  fmt.Println("=== testDeduceType2 ===")
  p := People{"Lily"}
  var dog = Dog{&Mammal{Animal{"DogCalvin",true},20},Owner{"Bob","male",50}}
  var s Speaker = People{"Kate"}

  objList := make([]interface{},6)
  objList[0],objList[1],objList[2],objList[3],objList[4]=p,dog,s,"hello",6

  for idx,elmt := range objList {
    switch val := elmt.(type) {
    case int:
      fmt.Printf("objList[%d] is an int,value=%d\n",idx,val)
    case string:
      fmt.Printf("objList[%d] is a string,value=%s\n", idx,val)
    case People:
      fmt.Printf("objList[%d] is a People,value=%v\n", idx,val)
    case Dog:
      fmt.Printf("objList[%d] is a Dog,value=%v\n", idx,val)
    default:
      fmt.Printf("objList[%d] is an unknown type\n", idx)
    }
  }
}

func testDeduceType1(){
  fmt.Println("=== testDeduceType1 ===")
  p := People{"Lily"}
  var dog = Dog{&Mammal{Animal{"DogCalvin",true},20},Owner{"Bob","male",50}}
  var s Speaker = People{"Kate"}

  objList := make([]interface{},6)
  objList[0],objList[1],objList[2],objList[3],objList[4]=p,dog,s,"hello",6

  for idx,elmt := range objList {
    if val,ok := elmt.(int); ok {
      fmt.Printf("objList[%d] is an int,value=%d\n",idx,val)
    }else if val,ok := elmt.(string); ok {
      fmt.Printf("objList[%d] is a string,value=%s\n", idx,val)
    }else if val,ok := elmt.(People); ok {
      fmt.Printf("objList[%d] is a People,value=%v\n", idx,val)
    }else if val,ok := elmt.(Dog); ok {
      fmt.Printf("objList[%d] is a Dog,value=%v\n", idx,val)
    }else if val,ok := elmt.(Speaker); ok {
      fmt.Printf("objList[%d] is a Speaker, value = %v\n", idx,val)
    }else{
      fmt.Printf("objList[%d] is an unknown type\n", idx)
    }
  }
}

func testPeople(){
  fmt.Println("===   testPeople ===")
  var s Speaker = People{"Kate"}

  s.SayHi()
  s.Break()
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
