package main


import (
  "fmt"
)

func main(){
  slice := []int{10,20,30,40,50}

  fmt.Println(slice)

  newSlice := slice[1:3]

  fmt.Println(newSlice)
}
