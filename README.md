DOCUMENTACIÓN RETO TÉCNICO



- [ARQUITECTURA] :  El proyecto está diseñado bajo la arquitectura MVVM (Model View View Model) y a su vez el proyecto sigue los conceptos de Clean Architecture. Esto nos permite tener organizado el proyecto con los conceptos de Clean Architecture y está bajo la arquitectura MVVM , la cual es la recomendada para Android.
<p align="center">
<a ><img src="https://firebasestorage.googleapis.com/v0/b/autoservicio-87532.appspot.com/o/arquitectura.png?alt=media&token=d31583c8-4f3b-4230-beb4-fc1441c7b370" alt=""></a>
</p>

- [PRUEBAS UNITARIAS] :  Se implementó pruebas unitarias para la clase "ViewModel" la cual hace el llamado al api y con la información del estado del juego.
<p align="center">
<a ><img src="https://firebasestorage.googleapis.com/v0/b/autoservicio-87532.appspot.com/o/pruebasunitariasruta.png?alt=media&token=4a114455-933b-45d6-97a2-31eeedddc86b" alt=""></a>
</p>
<p align="center">
<a ><img src="https://firebasestorage.googleapis.com/v0/b/autoservicio-87532.appspot.com/o/pruebasunitariasfuncion.png?alt=media&token=dfe6e5c3-cb68-4387-8c8e-af189ca468c0" alt=""></a>
</p>

- [CÓDIGO] :  Desarrollado en Kotlin usando XML con "Constraint layout"


- [CONSUMO DE API] :  Se está usando Retrofit y OkHttp para manejar los llamados al api y consumir el estado del juego. Hay 2 apis :
  1. Uno es el que está consumiendo actualmente por el app (api fue publicado en Firebase ya que no cuento con un servidor para la publicación).
  2. El otro es el que hice en .net y el cual se encuentra en el siguiente enlace como proyecto ya que la publicación solo la hice en mi localhost.
     Enlace : https://github.com/PierreCrow/api_estado_juego
               

- [SERVICIOS DE UBICACIÓN] : Se está usando Play Services Location para capturar la ubicación del usuario. Cada vez que se encuentra la ubicación, se muestra la latitud, longitud y el nombre de la región concatenados en un campo de texto.
<p align="center">
<a ><img src="https://firebasestorage.googleapis.com/v0/b/autoservicio-87532.appspot.com/o/geolocalizacion.png?alt=media&token=93cee50a-7f13-4e5e-93da-24c2692a110c" alt=""></a>
</p>

- [JUEGO] :  El juego está realizado tal cual la documentación , con la excepción del envío del estado del juego al servidor, ya que en Firebase no podía publicar un api que guarde un valor.
  El juego contiene 3 botones :
  1. Consultar estado aleatorio , el cual escoge al azar un estado del juego y lo muestra en las cartas.
  2. Consultar estado servidor , el cual consume el api que está en Firebase que siempre retorna el mismo valor.
  3. Reiniciar juego , reinicia el juego,voltea las cartas y asigna cartas al azar.
<p align="center">
<a ><img src="https://firebasestorage.googleapis.com/v0/b/autoservicio-87532.appspot.com/o/Juego.png?alt=media&token=0cac95eb-2ff5-4637-af36-711c505ac1cb" alt=""></a>
</p>

- [INYECCIÓN DE DEPENDENCIAS] :  Se está usando Dagger Hilt para la inyección de dependencias.


