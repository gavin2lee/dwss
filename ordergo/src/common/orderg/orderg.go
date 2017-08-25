package orderg

type OrderId struct{
  SerialNo int
}

type CustomerId struct{
  ClientNo string
  Name string
  Gender string
}

type Product struct{
  PrdCode string
  PrdName string
  Price float64
}

type Customer struct{
  Cid CustomerId
  RegisterDate string
  RegisterTime string
  Status string
}

type Order struct{
  Oid OrderId
  Prd Product
  Amount int
  OrderDate string
  OrderTime string
  Status string
}
