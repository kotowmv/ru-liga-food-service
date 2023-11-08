# ru-liga-food-service
Training Project for Liga

# Сервис доставки еды

Для запуска необходимо:
1. Запустить контейнер с RabbitMQ в Docker с помощью команды:<br>
   docker run -lt --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
2. Создать базу данных в PostgreSQL с именем "postgres" и схемой "public"
3. Запустить скрипт миграции данных liquibase:<br>
   В IntelliJ Idea:
-  Выбрать вкладку "Maven" справа
-  Перейти по пути: food-delivery-service -> migration -> Plugins -> liquibase -> liquibase:update
-  Запустить скрипт и дождаться его выполнения
4. Запускать сервисы в следующей последовательности:<br>
   Auth -> Gateway -> Notification -> Order -> Kitchen -> Delivery
5. Перейти на страницу авторизации: http://127.0.0.1:8090/login <br>
   Пользователь по умолчанию:<br>
   Логин: admin, пароль: admin
6. Для доступа к Swagger перейти по ссылкам:
-  Order service: http://127.0.0.1:8080/order-service/swagger-ui/index.html
-  Kitchen service: http://127.0.0.1:8080/kitchen-service/swagger-ui/index.html
-  Delivery service: http://127.0.0.1:8080/delivery-service/swagger-ui/index.html