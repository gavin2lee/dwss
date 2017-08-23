package objutils

import (
  "fmt"
  "reflect"
  "os"
)

func CheckError(err error){
  if err != nil {
    fmt.Fprintf(os.Stderr,"Fatal error:%s\n",err.Error())
    os.Exit(1)
  }
}

func ParseObjInfo(obj interface{}) (ret []string){
  return doParseObjInfo(obj,0)
}

func doParseObjInfo(obj interface{},indent int) (ret []string){
  i := 100

  strIndent := ""
  for i:=0;i<indent;i++{
    strIndent = strIndent + "-"
  }

  defer func(){
    if err := recover();err != nil {
      fmt.Println(err, i)
    }
  }()
  oType := reflect.TypeOf(obj)

  if k:= oType.Kind(); k != reflect.Struct {
    return make([]string,0,0)
  }
  var ss = make([]string,0,0)

  oValue := reflect.ValueOf(obj)

  for i:=0;i < oType.NumField(); i++ {
    field := oType.Field(i)
    value := oValue.Field(i).Interface()

    if k:=field.Type.Kind(); k == reflect.Struct {
      ss = append(ss,strIndent+field.Name+":"+field.Type.Name() + " ->")
      for _,val := range doParseObjInfo(value, indent+1) {
        ss = append(ss,val)
      }
    }else{
      ss = append(ss,strIndent+field.Name+":"+field.Type.Name() + " -> "+fmt.Sprintf("%v",value))
    }
  }
  return ss
}
