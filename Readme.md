# Order Api Project

## function

- cart
    - create (O)
    - detail (O)
    - update (O)
    - delete (O)
    
- order
    - create (O)
    - detail (O)
    - update (O)
    - pay (O)
    - cancel (O)
    
## Module 

| proeject | description |
|---|---|
| core | spring core |
| core-web | spring web core (response, exception, error) |
| domain | function service layer |
| domain-mapper | using mapstruct dto to entity and entity to dto collection |
| domain-rds | using rds and hibernate jpa implementation(function order) |
| domain-redis | using redis implementation(function cart) |
| external-api | api for customer(function cart, order), business logic implementation|
