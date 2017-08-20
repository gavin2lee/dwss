package main

import(
  "fmt"
  "obj/school"
  "reflect"
)

func main(){
  testReflect()
}

func testReflect(){
  fmt.Println("=== testReflect ===")
  var stud1 = school.Student{}

  fmt.Printf("stud1:%v\n", stud1)

  stud1.Grade = 1
  stud1.Class = 2

  fmt.Printf("stud1:%v\n", stud1)

  stud1.Addr = school.Address{"GuangDong","ShenZhen","Nanxin Street","518000"}

  fmt.Printf("stud1:%v\n", stud1)

  stud1.People = school.People{1000,"Kalvin","male",true}

  fmt.Printf("stud1:%v\n", stud1)

  fmt.Println()

  printObjInfos(stud1)
}

func printObjInfos(obj interface{}){
  fmt.Println("=== print fields via reflect ===")
  t := reflect.TypeOf(obj)

  if k:= t.Kind(); k != reflect.Struct {
    fmt.Printf("it is not a struct,it is %v. \n", k)
    return
  }

  fmt.Printf("Struct name is :%s\n", t.Name())

  fmt.Println("Fields of the struct is:")

  v := reflect.ValueOf(obj)

  for i:=0; i < t.NumField(); i++ {
    field := t.Field(i)
    value := v.Field(i).Interface()

    if field.Anonymous {
      fmt.Printf("%6s:%v\n",field.Name,field.Type)
      tf := v.Field(i)
      for j:=0;j<tf.NumField();j++ {
        fmt.Printf("%6s\n", v.FieldByIndex([]int{i,j}).Interface())
      }
    }else{
      fmt.Printf("%6s: %v = %v \n", field.Name, field.Type, value)
    }


  }

  fmt.Println("Methods of the struct is:")

  for i:=0;i<t.NumMethod();i++ {
    method := t.Method(i)
    fmt.Printf("%6s:%v\n",method.Name,method.Type)
  }
}
