package main

import (
	"fmt"
	"log"
	"net/http"
	"strings"

	"common/objutils"
)

func simpleRootDefaultFuncHandler(w http.ResponseWriter, r *http.Request) {
	err := r.ParseForm()
	objutils.CheckError(err)
	fmt.Println(r.Form)
	fmt.Println("path", r.URL.Path)
	fmt.Println("schema", r.URL.Schema)
	fmt.Println(r.Form["url_long"])

	for k, v := range r.Form {
		fmt.Println("key:", k)
		fmt.Println("val:", strings.Join(v, ""))
	}

	fmt.Fprintf(w, "Hello http client!")
}

func main() {
    http.HandleFunc("/", simpleRootDefaultFuncHandler)
    err := http.ListenAndServe(":9999", nil)
    
    if err != nil {
        log.Fatalf("ListenAndServe", err)
    }
}
