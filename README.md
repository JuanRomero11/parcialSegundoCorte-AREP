# Parcial Segundo Corte Arep
## Profesor: Luis Daniel Benavides
## AREP - Arquitecturas Empresariales 2021-1

Diseñé, construya y despliegue los siguientes servicios en un microcontenedor docker desplegado en una instancei a EC2 de AWS y otro desplegador en AWS lambda con AWS gateway. Cada estudiante debe seleccionar para desarrollar dos funciones matemáticas de acuerdo a los dos últimos dígitos de su cédula como se especifica en la lista (Si sus dos últimos dígitos de su cédula son el mismo use el siguiente dígito que sea diferente). Todas las funciones reciben un solo parámetro de tipo "Double" y retornan una parámetro de tipo "Double".

0. log

1. ln

2. sin

3. cos

4. tan

5. acos

6. asin

7. atan

8. sqrt

9. exp (el número de eauler elevado ala potendia del parámetro)


Implemente los servicios para responder al método de solicitud HTTP GET. Deben usar el nombre de la función especificado en la lista y el parámetro debe ser pasado en la variable de query con nombre "value".


Ejemplo de una llamado:

EC2
https://amazonxxx.x.xxx.x.xxx:{port}/cos?value=3.141592

Lambda + API Gateway
https://amazonxxx.x.xxx.x.xxx/sin?value=3.141592


Salida. El formato de la salida y la respuesta debe ser un JSON con el siguiente formato

{

 "operation": "cos",

 "input":  3.141592,

 "output":  -0.999999

}


#### instacia EC2

http://ec2-3-83-182-251.compute-1.amazonaws.com:35001/exp?value=3.1

http://ec2-3-83-182-251.compute-1.amazonaws.com:35001/atan?value=3.1

#### lambda 

https://ie50ekyb6h.execute-api.us-east-1.amazonaws.com/parcial/atan?value=3.14

https://ie50ekyb6h.execute-api.us-east-1.amazonaws.com/parcial/exp?value=3.14

#### VIDEO 

## Prerrequisitos

Como primera medida se debera intalar el JDK y maven y si es necesario un editor como ECLIPSE,NETBEANS,etc. Si solo se quiere ejecutar el proyecto de manera local solo se necesitara el JDK y maven. Para el JDK JAVA se instala la versión 8, a continuacion se adjuntara el link donde muestran las instrucciones detalladas de como descargar este y ademas en esta mismo link se podra realizar la descarga y futura instalacion, de igual forma se adjunta el link de la pagina oficial de maven en el cual esta el instructivo detallado y los paquetes necesarios para la version del sistema operativo en el cual se ejecutara, y por ultimo es recomdable descargar la aplicacion git tambien se anexara el link desde la pagina oficial, se anexan los links debido a que se especifican mas en cada uno con respecto a cada sistema operativo y no uno en especifico.

### Instalacion

Descargar JDK e instrucciones de instalacion
https://www.oracle.com/technetwork/es/java/javase/downloads/index.html
Descargar maven e instrucciones de instalacion
https://maven.apache.org/download.cgi
Descargar Git e instrucciones de instalacion
https://git-scm.com/downloads

## Construccion

Este proyecto basicamente esta construido en maven y el editor que se utilizo fue Visual Studio Code, teniendo el JDK antes mencionado, especificamente se utilizo el lenguaje Java, con el mini framework Spring propio.


## Descripcion del 

En cuanto al diseño de parcialPrimerParte se encuentra dos paquetes principales uno que contiene la interfaz Operations, y la implementan las clases ubicadas en el otro paquete Exp y Atan esto se realiza para tener una mejor alcance y que posteriormente extensibilidad, y en el app este sera la clase principal capaz de resivir el parametro value y manejar las funciones lambda para los respectivos paths atan y exp.

En cuanto al diseño de parcialSegundaParte se encuentra una clase principal con dos metodos uno para calcular el atan y otro para calcular el exp.


## Como usar el Proyecto

En este mismo repositorio puede clonar o descargar el proyecto a traves de la aplicacion git ya instalada en su computador. Recomendacion, Si se clona utilizar el comando :

        git clone https://github.com/JuanRomero11/parcialSegundoCorte-AREP.git
        
Despues de que el proyecto esta clonado se accede a la consola del computador en este caso accedemos a la terminal de comandos de Windows(cmd) y entramos directamente en la carpeta en donde esta nuestro proyecto y como primer paso compilamos con el comando 

        mvn package

 la clase principal como se puede evidenciar es App si se quiere ejecutar local se ubica en el directorio raiz de parcialPrimerParte o en el segundo directorio raiz parcialSegundaParte y ejecuta el siguiente comando

        java -cp target/classes;target/dependency/* edu.escuelaing.edu.app

al correr debe ser en el puerto 4567

## Despliegue

El despliegue se realiza en dos partes parcialPrimerParte el cual se despliega en una instancia EC2 utilizando docker y la segunda parte se realiza haciendo uso de parcialSegundaParte el cual sera desplegada en una funcion lambda apoyandonos con Gateway.


### Creacion de imagenes

- como primer paso se generan las iamgenes de la Api ya sea de parcialPrimerParte o de  parcialSegundaParte, con los siguientes comandos(estos comandos se tiene que ejecutar en la respectiva carpeta raiz de cada proyecto).

        docker build --tag parcialPrimerParte .
        
        docker build --tag parcialSegundaParte .

### Creacion de Containers Local

- Para la creacion de los Containers de parcialPrimerParte y parcialSegundaParte se ejecutan los siguientes comandos(estos comando se tiene que ejecutar en la respectiva carpeta raiz de
cada proyecto).

        docker run -d -p 35001:6000 --name primeraparte primeraparte 
        docker run -d -p 35002:6000 --name segundaparte segundaparte 
        

###  Añadir iamgenes en repositorio de DockerHub

Para la añadir las imagenes en un repositorio de DockerHub, para ello se debe crear localmente una imagen con el nombre del repositorio a donde se va a subir y posteriormente realizar un push para empujar la imagen al repositorio(estos comando se tiene que ejecutar en la respectiva carpeta raiz de cada proyecto).

        docker tag parcial juanromero31/parcial
        docker push juanromero31/parcial
        
### containers en AWS 

Al tener configurados los grupos de seguridad los puertos (se puede evidenciar en el video) se realizan los siguientes pasos:

1. Instalar Docker en la instancia EC2: sudo yum update -y && sudo yum install docker.

2. Iniciar el servicios Docker: sudo service docker start.

3. Configurar al usuario: sudo usermod -a -G docker ec2-user.

4. Para estar seguro y guardar los cambios, salir y volver a entrar a la instancia

5. Descargar la imagen pública de DockerHub con el comando docker pull juanromero31/parcial

6. Crear una instanacia basada en la imagen con el comando docker run -d -p 35001:6000 --name parcial juanromero31/parcial(el nombre y el puerto son opcionales).

7. Para acceder a log puede ejecutar el comando docker logs -f parcial.

Para realizar el test en la url se puede acceder en este caso a 


http://ec2-3-83-182-251.compute-1.amazonaws.com:35001/exp?value=3.1

http://ec2-3-83-182-251.compute-1.amazonaws.com:35001/atan?value=3.1

siempre varia el nombre de la instancia o el puerto de eleccion es decir:

http://nombre de tu instancia EC2.amazonaws.com:puerto/exp?value=valor
                                                        atan

Pruebas realizadas:

![alt text](https://github.com/JuanRomero11/parcialSegundoCorte-AREP/blob/main/images/pruebaEC2.PNG)


## Funcion lambda y Gateway

La explicacion mas detallada en cuanto al despliegue del api parcialSegundaParte se encuentra en el video debido a que es mas interactivo con la plataforma aws y no tanto de realizar comandos en consola sin embargo coloco unas iamgenes de guia y una breve explicacion.


https://ie50ekyb6h.execute-api.us-east-1.amazonaws.com/parcial/atan?value=3.14

https://github.com/JuanRomero11/parcialSegundoCorte-AREP/blob/main

Como primer paso se debe crear la funcion lambda y que va a manejar un lenguaje en este caso JAVA8 y despues de ello lo que hacemos es subir el .jar del proyecto parcialSegundaParte ubicado en el target despues de ello cambiamos el paquete y colocamos en metodo como aparece en la imagen 

![alt text](https://github.com/JuanRomero11/parcialSegundoCorte-AREP/blob/main/images/lambda.PNG)

despues de ello se crea el Gateway 

![alt text](https://github.com/JuanRomero11/parcialSegundoCorte-AREP/blob/main/images/CrearGateway.PNG)

Se configurar las siguientes funciones para los paths y el valor que vamos a pedir, estas son solicitud Metodo y la plantilla del mapeo.

![alt text](https://github.com/JuanRomero11/parcialSegundoCorte-AREP/blob/main/images/solicitudMetodo.PNG)

![alt text](https://github.com/JuanRomero11/parcialSegundoCorte-AREP/blob/main/images/plantillaMapeo.PNG)

como resultado deben quedar las funciones Gateway de la siguiente forma

![alt text](https://github.com/JuanRomero11/parcialSegundoCorte-AREP/blob/main/images/funcionesGateway.PNG)

Como resultado final de la funcion lambda es el siguiente 

![alt text](https://github.com/JuanRomero11/parcialSegundoCorte-AREP/blob/main/images/pruebaLambda.PNG)

Resultados obtenidos:

![alt text](https://github.com/JuanRomero11/parcialSegundoCorte-AREP/blob/main/images/prueba.PNG)

![alt text](https://github.com/JuanRomero11/parcialSegundoCorte-AREP/blob/main/images/pruebaLambda.PNG)



## Integracion continua

[![CircleCI](https://circleci.com/gh/circleci/circleci-docs.svg?style=svg)]()

## Autor
Juan Guillermo Romero 

## License
Este codigo tiene una licencia Apache License 2.0 la cual se detalla en https://github.com/JuanRomero11/parcialSegundoCorte-AREP/blob/main/LICENSE