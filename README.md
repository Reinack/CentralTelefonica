Task
El objetivo del presente trabajo obligatorio es modelar e implementar la primera versión prototipo de un sistema de atención telefónica.
En esta primera versión el desarrollo se focalizará en:
1) Precarga de información.
2) Aplicación para trabajadores
3) Aplicación para simular llamadas
4) Aplicación para monitoreo
5) Cálculo de costos
6) Interfaz gráfica de escritorio
   
Requerimientos de diseño para esta versión:
1) Maximizar la modularidad y claridad del código. Para esto utilice el indicador que dice
que ningún método debería tener más código que el que se puede visualizar en una
pantalla.
2) Minimizar la duplicación de código. Evitar métodos o porciones de código que realizan
la misma tarea.
3) División física de las clases en paquetes.
4) División lógica.
5) Uso del patrón de diseño “Fachada”.
6) Experto
7) Utilizar una arquitectura M.V.C.
8) Utilizar polimorfismo donde corresponda.
9) Utilizar manejo de excepciones.
  
Información general:
Deben existir N sectores definidos en él sistema. Cada sector tiene un nombre y un
conjunto de trabajadores asignados. Al ingresar, el sistema le asigna un puesto de trabajo al
trabajador. Los trabajadores atienden llamadas de los clientes que realizan consultas y
gestiones telefónicas referentes a dicho sector. Si todos los trabajadores del sector están
ocupados, el cliente deberá esperar en línea hasta que quede un trabajador libre. Cuando la
llamada finaliza el sistema podrá registrar cargos al cliente por la llamada realizada.

1) Precarga de información.
NO es necesario implementar una interfaz de usuario para el mantenimiento de la
información (salvo que se especifique explícitamente en algún caso de uso). El sistema
deberá tener precargada la información, de modo que al iniciarse ya cuente con un
conjunto de datos definido.
La información que se debe precargar es:
*Clientes - Información básica: cédula de identidad, nombre completo, y saldo
*Trabajadores - Información básica: cédula de identidad, contraseña, y nombre completo.
*Sectores - información básica: nombre, número y cantidad de puestos de trabajo.
*Cantidad máxima de llamadas totales (tanto en curso como en espera) que soporta el
sistema = 5.
*Valor del costo fijo x segundo de las llamadas = $1
2) Aplicación para Trabajadores
Esta aplicación es utilizada por los trabajadores. Los casos de uso disponibles en esta
aplicación son los siguientes:
CU: Ingresar a la aplicación
Curso normal:
1) El trabajador ingresa su número de cédula y su contraseña.
2) El sistema registra que ese trabajador estará atendiendo clientes en su sector,
asignándole un puesto de trabajo (El primero que esté libre).
3) El sistema ejecuta directamente el caso de uso Atender llamadas (en otra interfaz de
usuario)
Cursos alternativos:
2) Si la cédula de identidad y/o la contraseña no coinciden, mensaje: “Acceso denegado”
Si no hay puestos de trabajo disponibles en el sector, mensaje: “No hay puestos
disponibles”. Fin caso de uso.
CU: Atender llamadas.
Curso normal:
1)El sistema muestra el nombre completo del trabajador, el nombre del sector, el número
del puesto de trabajo asignado, la cantidad de llamadas atendidas (inicialmente 0), y el
tiempo promedio (en segundos) de las llamadas en ese puesto de trabajo (inicialmente 0).
2) El sistema espera una llamada entrante de un cliente.
3) Cuando un cliente llama, el sistema asigna una llamada al puesto de trabajo y muestra el
mensaje “Llamada en curso…” y el nombre completo del cliente que llama. El sistema
actualiza la cantidad de llamadas atendidas del puesto de trabajo y registra fecha y hora de
inicio de atención de la llamada y la muestra.
4) El trabajador ingresa una descripción sobre la llamada.
5) El trabajador habla con el cliente y luego indica que desea finalizar la llamada.
6) El sistema registra fecha y hora de fin de la llamada, la descripción, el trabajador que
atendió la llamada y registra que ese puesto de atención ya no tiene una llamada en curso.
El sistema muestra mensaje “Llamada finalizada…”, actualiza el tiempo promedio de las
llamadas para ese puesto de trabajo y muestra la duración (*) y el costo de la llamada.
7) El caso de uso vuelve al curso normal 2)
(*) La duración de la llamada siempre se calcula como el tiempo transcurrido entre la
fecha/hora de fin de la llamada y la fecha/hora de inicio de atención de la llamada. Se mide
en segundos.
Cursos alternativos:
2) Si hay llamadas en espera para el sector, el sistema le asigna una llamada al puesto de
trabajo y continúa el caso de uso en el curso normal 3)
4) El cliente finaliza la llamada. El caso de uso continúa en el punto 5)

CU: Salir de la aplicación
Curso normal:
1) El trabajador indica que desea salir del sistema.
2) El sistema registra que ese trabajador ya no está atendiendo llamadas en ese puesto de
trabajo.
Cursos alternativos:
2) Hay una llamada en curso. El sistema pregunta al usuario si desea finalizar la llamada en
curso y salir de la aplicación o si desea cancelar la salida.
3) Aplicación para simular llamadas
Esta aplicación es utilizada para simular la interacción con una central telefónica.
En versiones posteriores de este prototipo, esta aplicación sería sustituida por una
aplicación que tenga interacción real con una central telefónica. En la versión actual se
utilizará para poder probar las otras aplicaciones.
CU: Simular llamada
Curso normal:
1) El usuario indica que desea iniciar una llamada.
2) El sistema acepta la llamada, registra la fecha/hora de inicio de la llamada y envía
mensaje “Ingrese su cédula seguida de la tecla numeral”.
3) El usuario ingresa la cédula de identidad de un cliente seguida de la tecla numeral.
4) El sistema envía un mensaje con la lista de sectores definidos seguidos del número de
sector Ej.: “Para comunicarse con Ventas digite 1, Servicio técnico digite 2, Postventa
digite 3,..etc…”
5) El usuario digita el número del sector con el que desea comunicarse.
6) El sistema inicia una llamada en comunicación con el primer trabajador libre del sector.
Y envía el mensaje “Llamada en curso… ud. se está comunicando con el sector ” +
nombre del sector con el que se está comunicando + “Y está siendo atendido por ”+ el
nombre del trabajador que lo está atendiendo + “ Su llamada se ha iniciado en “ + la fecha
y hora de inicio de atención de la llamada.
7) El usuario habla con el trabajador y luego indica que desea finalizar la llamada.
8) El sistema envía el mensaje “Llamada finalizada…Duracion:”+ la duración de la
llamada+ “ Costo: “ + el costo de la llamada + “Su saldo es de:” + el saldo del cliente.
Cursos alternativos:
2) Se ha alcanzado la cantidad máxima de llamadas. Mensaje “Comuníquese más tarde…”.
Fin caso de uso.
4) No existe un cliente registrado con la cédula ingresada. Mensaje: “Cliente no
registrado”.
Fin caso de uso.
6) -El número de sector digitado no es válido. Mensaje “Sector no válido”. Fin caso de uso
-No hay trabajadores atendiendo llamadas en el sector. Mensaje: “Sector no disponible”
Fin caso de uso
-Todos los trabajadores del sector están ocupados. El sistema pone la llamada en espera
para ese sector. Mensaje: “Aguarde en línea, Ud. se encuentra a X llamadas de ser
atendido, la espera estimada es de N minutos” Siendo X la cantidad de llamadas en
espera para ese sector y N los minutos estimados de espera para el sector (*1).
Cuando la llamada es transferida al primer trabajador disponible el caso de uso continua
en el curso normal 6).
IMPORTANTE: En este punto el usuario podrá finalizar la llamada antes de ser atendido.
En ese caso el sistema quita la llamada de la espera de ese sector y termina el caso de
uso.
7) El trabajador finaliza la llamada. El caso de uso continúa en el punto 8) del curso
normal.
(*1) La espera estimada para el sector se calcula como el tiempo promedio de atención del
sector (*2) multiplicado por la cantidad de llamadas en espera en ese sector.
(*2) El tiempo promedio de atención se calcula como el promedio de los tiempos promedio
de atención de cada puesto de trabajo del sector.
4) Aplicación para Monitoreo
CU: Monitorear llamadas
Curso normal:
1) El sistema muestra la lista de sectores definidos.
2) El usuario selecciona un sector.
3) El sistema muestra todas las llamadas del sector en el mismo orden de su aparición, con
la siguiente información: Número de llamada relativo al sector, “En curso” o “Finalizada”
según corresponda, fecha/hora de inicio, fecha/hora de inicio de atención, fecha/hora de
fin, Número de puesto al que fue asignada, Nombre completo del trabajador al que fue
asignada, duración de la llamada, costo, nombre del cliente y saldo del cliente.
4) Opcionalmente el usuario indica que desea ver las llamadas de todos los sectores. El
sistema muestra todas las llamadas recibidas en el mismo orden de su aparición y con la
misma información descrita en el punto 3), pero incluyendo el nombre del sector como
primer dato.
NOTA: En ambos casos solo se muestran las llamadas atendidas (no las llamadas en
espera)
5) Cálculo de costos
Cada llamada puede tener un costo para el cliente. Las llamadas no atendidas no tienen
costo. Deberán diferenciarse los clientes de la siguiente manera:
Exonerados: No pagan las llamadas.
Con Costo: Pagan el costo fijo de la llamada si el tiempo de espera entre que se inició la
llamada y fue atendida, es menor a 1 minuto. Si es mayor pagan la mitad del costo fijo.
Gestores: Pagan la mitad del costo fijo de la llamada, y al costo total de la llamada se le
descuenta el valor de un costo fijo por cada segundo transcurrido entre que inició la
llamada y esta fue atendida. Pero si la duración de la llamada (desde que fue atendida) es
mayor a 3 minutos pagan el costo fijo de la llamada.
IMPORTANTE:
En todos los casos el cálculo del pago nunca puede ser negativo, si diera negativo es 0.
El valor del costo fijo es por segundo, por lo que debe multiplicarse por los segundos de
duración de la llamada (desde que fue atendida por el trabajador hasta que finaliza)
Debe tenerse en cuenta a nivel de diseño que los clientes en el futuro podrán cambiar de
Exonerados a Con Costo o Gestores, etc
4) Interfaz gráfica
El objetivo de este prototipo es emular una situación en la cual cada usuario interactúa con
el sistema desde una computadora diferente.
Para emular esta situación, se deberá implementar una ventana general para testing de la
aplicación, desde la cual se podrán lanzar instancias de las aplicaciones:
La información de todas las ventanas debe obligatoriamente actualizarse de manera
automática, sin necesidad de que el usuario indique que desea actualizar la
información.
