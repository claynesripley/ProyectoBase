Feature: Alta prestamo
Verificar que se realice un alta de prestamo

Scenario: Alta de Prestamo
   Given El usuario ingresa al Login Page
   When El usuario ingresa el "<usuario>" y "<contraseña>"
   Then Redirecciona al Home Page
   When El usuario da click en Menu
   And El usuario da click en Catalogo de Productos
   And El usuario ingresa a consumo banco ripley
   And El usuario crea un prestamo ya
   And El usuario ingresa documento "<documento>" y moneda 
   And El usuario prevalida
   And El usuario ingresa datos de cliente "<ejecutivo>", marca virtual y personal para prestamo
   And El usuario captura la cuenta prestamo
   And El usuario ingresa monto "<monto>"
   And El usuario ingresa fechamaduracion "<fechamaduracion>"
   And El usuario ingresa tasas "<tarifa1>"
   And El usuario ingresa fechastart "<fechamaduracion>"
   And El usuario prevalida
   And El usuario hace commit
   And El usuario marca recibido y acepta prestamo "<documento>"
   And El usuario hace commit
   Then Se muestra el codigo de transacción de alta prestamo
   Examples:
       | usuario      | contraseña | documento |  ejecutivo | monto |  fechamaduracion | tarifa1 | fechamaduracion |
       | SCISNEROSA1  | 123456     |  4793147 | 100005 | 4000 | 20240104 | 34.33 | D_20230130 |

 Scenario: Buscar Prestamo
   Given El usuario ingresa al Login Page
   When El usuario ingresa el "<usuario>" y "<contraseña>"
   Then Redirecciona al Home Page
   When El usuario da click en Menu
   And El usuario da click en Operaciones Minoristas
   And El usuario da click en Buscar Prestamo
   And El usuario ingresa el arreglo a buscar "<arreglo>"
   Then Se validan los datos de prestamo
   #Then Se valida el estado
   Examples:
       | usuario      | contraseña| arreglo     |
       | SCISNEROSSPAS  | 123456  | AA22015DMPQG|