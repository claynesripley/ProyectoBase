Feature: Alta cuenta
Verificar que se realice un alta de cuenta

Scenario: Alta de Cuenta
   Given El usuario ingresa al Login Page
   When El usuario ingresa el "<usuario>" y "<contraseña>"
   Then Redirecciona al Home Page
   When El usuario da click en Menu
   And El usuario da click en Catalogo de Productos
   And El usuario ingresa a cuentas de ahorros
   And El usuario crea una cuenta simple
   And El usuario ingresa documento "<documento>" y moneda
   And El usuario prevalida
   And El usuario ingresa datos de cliente "<ejecutivo>", marca virtual y personal
   And El usuario captura la cuenta
   And El usuario prevalida
   And El usuario hace commit
   And El usuario marca recibido y acepta "<documento>"
   Then Se muestra el codigo de transacción alta cuenta
   Examples:
       | usuario      | contraseña | documento |  ejecutivo | 
       #| SCISNEROSA1  | 123456     |  4793147 | 100005 | 


Scenario: Buscar Cuenta
   Given El usuario ingresa al Login Page
   When El usuario ingresa el "<usuario>" y "<contraseña>"
   Then Redirecciona al Home Page
   When El usuario da click en Menu
   And El usuario da click en Operaciones Minoristas
   And El usuario da click en Buscar Cuenta
   And El usuario ingresa el arreglo a buscar "<arreglo>"
   Then Se validan los datos de cuenta
   #Then Se valida el estado
   Examples:
       | usuario        | contraseña| arreglo         |
       | SCISNEROSSPAS  | 123456    | AA230325B7T0    |