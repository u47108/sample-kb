# Una API Restful de Farmacias en Turno Público 
El proyecto se separa en capas BE microservicios de lógica y consulta a otras APIS en el caso propuesto las APIS no eran con seguridad si se necesitara el uso del x-api-key se deberia utilizar secretos para guardar las claves.

El microservicio BFF interactua directamente con el Front FE, por lo que se configuraron los filtros CORS.
El front FE no está completo,nunca tuve una API de google maps para habilitar, no me desgaste en eso.

Mi primer enfoque pensé en usar la cuenta que tenia en Kubernates pero se me vencieron los $300 dolares y para habilitar otra cuenta de facturación esta lenta la cosa con el tema del covid-19 al momento de escribir este manual aún no me habilitan. 

Los Servicios REST (API),están basados en Spring Boot

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
    
* mvn clean install

* java -jar target/*.jar

## Instalación

Se deben configurar las siguientes variables de entorno proyecto BE:

- **COMUNAS_ENDPOINT**: Endpoint del minsal(ej: *https://midastest.minsal.cl/farmacias/maps/index.php/utilidades/maps_obtener_comunas_por_regiones*)
- **COMUNAS_PARAM_NAME**: PARAMETRO  (*reg_id*)
- **COMUNAS_PARAM_VALUE**: PARAMETRO valor (*7*)
- **WS_TIMEOUT**: Tiempo de espera en milisegundos de conexión y operación con servicios externos (*15000*)

## Documentacion API

*http://localhost:8080/v2/api-docs*

## Deployment
kubectl create -f deploy-demo-bff.yaml

kubectl describe deployment/deploy-demo-bff
kubectl describe pod -l app=demo-bff

## Service
kubectl create -f aplicacion.yaml

kubectl describe service demo-bff

minikube service demo-bff

## Test 

curl $(minikube ip):${NodePort}

e.g. curl http://192.168.99.100:30658

```
![GCP Diagrama](https://github.com/u47108/sample-kb/blob/master/Demogcp.png) 
