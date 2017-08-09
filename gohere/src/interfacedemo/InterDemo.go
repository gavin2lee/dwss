package main;

import (
  "fmt"
  "os"
  "time"
)

type Bird struct{
  Name string;
  Category string;
  Id int64;
}

func (b *Bird) Fly(){
  fmt.Fprintf(os.Stdout,"%s %s flying\n",time.Now(), b.Name)
}


type IFlyAnimal interface{
  Fly()
}


func main(){
  //swallow := new(Bird)
  //swallow.Name = "SwallowYellow"
  var animal IFlyAnimal = &Bird{Name:"SwallowYellow"}
  animal.Fly()
}
