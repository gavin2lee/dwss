package main

import (
	"fmt"
	"log"
	"net/http"
	"html/template"
	"strings"
	
	"os"

	"common/objutils"
)

func simpleRootDefaultFuncHandler(w http.ResponseWriter, r *http.Request) {
	err := r.ParseForm()
	objutils.CheckError(err)
	fmt.Println(r.Form)
	fmt.Println("path", r.URL.Path)
	fmt.Println("scheme", r.URL.Scheme)
	fmt.Println(r.Form["url_long"])

	for k, v := range r.Form {
		fmt.Println("key:", k)
		fmt.Println("val:", strings.Join(v, ""))
	}

	fmt.Fprintf(w, "Hello http client!")
}

func login(w http.ResponseWriter, r *http.Request) {
	fmt.Println("method:", r.Method)
	
	r.ParseForm()

	if r.Method == "GET" {
		t, err := template.ParseFiles("views/tpl/login/login.gtpl")
		if err != nil {
		    fmt.Println(err)
		    log.Fatalf("ParseFiles",err)
		}
		t.Execute(w, nil)
	} else {
		fmt.Println("username:", r.Form["username"])
		fmt.Println("password:", r.Form["password"])
	}
}

func main() {
    var service string
    if len(os.Args) > 1 {
        service = os.Args[1]
    }else{
        service = ":9999"
    }
    
	http.HandleFunc("/", simpleRootDefaultFuncHandler)
	http.HandleFunc("/login", login)
	
	fmt.Println("http listen "+service)
	log.Println("http server listen "+service)
	
	err := http.ListenAndServe(service, nil)

	if err != nil {
		log.Fatalf("ListenAndServe", err)
	}
	
	fmt.Println("2 http listen "+service)
	log.Println("2 http server listen "+service)
}
