package main

import (
  "fmt"
)

func main(){
  array := [5]*int{0:new(int), 1:new(int)}

  *array[0] = 10
  *array[1] = 20

  fmt.Printf("array[0]:%d \n",array[0])
  fmt.Printf("*array[0]:%d \n",*array[0])

  fmt.Println(array)
}
