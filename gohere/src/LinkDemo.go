package main;

import(
  "link"
)

func main(){
  var head * link.Node;
  stud1 := link.Node{link.Student{100,"黎明"},nil};
  stud2 := link.Node{link.Student{101,"张刘"},nil};
  stud3 := link.Node{link.Student{102,"赵敏"},nil};
  stud4 := link.Node{link.Student{103,"王静"},nil};

  head = head.Create();
  head = stud1.Insert(head);
  head = stud2.Insert(head);
  head = stud3.Insert(head);
  head = stud4.Insert(head);

  head.PrintLink();

  head = stud3.Delete(head);
  head.PrintLink();
}
