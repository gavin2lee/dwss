package main


import (
  "fmt"
)

func main(){
  slice := []int{10,20,30,40,50}

  fmt.Println(slice)

  newSlice := slice[1:3]

  fmt.Println(newSlice)

//verify the underground array
  slice1 := []int{10,20,30,40,50}

  fmt.Println("=== slice1  ===")

  for _,v := range slice1 {
    fmt.Printf("%d\n", v)
  }

  slice2 := slice1[3:5]
  fmt.Println("=== slice2 ===")
  for index,val := range slice2 {
    fmt.Printf("%d - %d \n", index,val)
  }

  slice2[0] = 400

  fmt.Println("=== slice1 ===")
  for idx,val := range slice1 {
    fmt.Printf("%d - %d \n", idx, val)
  }

  fmt.Println("=== slice2 ===")
  for index,val := range slice2 {
    fmt.Printf("%d - %d \n", index,val)
  }

  fmt.Printf("len - %d,cap - %d \n",len(slice2), cap(slice2))

  slice2[0] = 40

  fmt.Println("=== slice2 append ===")
  slice2 = append(slice2,60)

  slice2[0] = 4000

  for idx,val := range slice2 {
    fmt.Printf("%d - %d \n", idx, val)
  }

  fmt.Println("=== slice1 ===")
  for idx,val := range slice1 {
    fmt.Printf("%d - %d \n", idx,val)
  }

  fmt.Printf("len - %d,cap - %d \n",len(slice2), cap(slice2))

}
