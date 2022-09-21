## Тестовое задание ##
### АльфаСтрахование-ОМС ###

Генерируем jar:
> maven: lyfecycle/package

Билдим image
> docker build -t app .

Запускаем docker-compose
 
> docker-compose up -d
 
### основные команды ###
> получить список пользователей: по умолчаниюзапущено 2 контейнера с портами 8080 и 8090
> http://localhost:8080/user/getusers/all
> 
> получить список незабаненных
> http://localhost:8090/user/getusers/allnobanned
> 
> создать
> http://localhost:8080/user/create
>>{
"id": 22,
"login": "test",
"password": "testpass",
"name": "testName",
"surname": "7gg",
"patronymic": "ff",
"banned": false
}
> 
> Обновить данные
> http://localhost:8080/user/update
> засылается та же модель json
> 
> Забанить по id
> http://localhost:8080/user/banuser?id=1