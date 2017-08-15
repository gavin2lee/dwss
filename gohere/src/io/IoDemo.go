package main;

import(
  "fmt"
  "os"
  "path/filepath"
)

func main(){
  testFileOperations()
}

func testFileOperations(){
  filename := "test.data"
  f,err := os.Create(filename)
  if err == nil {
    fmt.Println("create successfully")
    fmt.Println("file:", f,f.Name())

    dir,err2:=filepath.Abs((f.Name()))
    if err2 == nil{
      fmt.Println("dir:", dir)
    }

  }else{
    fmt.Println("err:",err)
  }
}
