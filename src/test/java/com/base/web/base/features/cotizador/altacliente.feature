Feature: Prueba T24
Verificar que se realice un alta

Scenario: Alta Cliente
   Given El usuario ingresa al Login Page
   When El usuario ingresa el "<usuario>" y "<contraseña>"
   Then Redirecciona al Home Page
   When El usuario da click en Menu
   And El usuario da click en Cliente
   And El usuario da click en Individual Customer
   And El usuario ingresa datos de cliente "<mnemocino>", "<dni>", "<apaterno>", "<amaterno>", "<nombre>", "<ncompleto>", "<estadocivil>", "<nacimiento>", "<gbdirec>", "<empresa>"
   Then Se muestra el codigo de alta
   Examples:
       | usuario      | contraseña | mnemocino |  dni | apaterno | amaterno | nombre |  ncompleto | estadocivil |  nacimiento |  gbdirec | empresa  |
       | SCISNEROSSPAS  | 123456     |  D72145815 | 72145815 | laynes | castro | carlos | carlos laynes castro | SOLTERO | 19980112 | 1 | asd |



@Somoketest
Scenario: Buscar Cliente
   Given El usuario ingresa al Login Page
   When El usuario ingresa el "<usuario>" y "<contraseña>"
   Then Redirecciona al Home Page
   When El usuario da click en Menu
   And El usuario da click en Operaciones Minoristas
   And El usuario da click en Buscar Cliente
   And El usuario ingresa datos de cliente "<dni>"
   Then Se validan los datos
   #Then Se valida el estado
   Examples:
       | usuario      | contraseña|  dni | 
       | SCISNEROSSPAS  | 123456  | 72145815 | 