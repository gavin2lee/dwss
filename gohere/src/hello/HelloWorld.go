package main;

import(
  "fmt"
  "custstruct"
)

func main(){
  fmt.Println("Hello World");

  var f1 float32 = 1235678900;
  var f2 float32 = 20;

  var f3 = f1 + f2;

  fmt.Println(f3);


  user := new(custstruct.User);
  user.Id = 100;
  user.Name = "张三";

  user.SayHi();

  fmt.Println(user);
}
