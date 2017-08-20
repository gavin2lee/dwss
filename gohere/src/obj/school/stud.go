package school

import(
  "fmt"
)

type People struct{
  Id int
  Name string
  Gender string
  IsAdult bool
}

type Address struct{
  Province string
  City string
  Street string
  ZipCode string
}

type Student struct{
  People
  Grade int
  Class int
  Addr Address
}

type Speaker interface{
  SayHi()
}

type Identifier interface{
  GetIdentifier() (id int,name string)
}

func (p People) SayHi(){
  fmt.Printf("%s Say Hi\n",p.Name)
}

func (p People) GetIdentifier()(id int,name string){
  fmt.Printf("%s Get Identifier\n",p.Name)
  var localId = p.Id
  var localName = p.Name
  return localId,localName
}
