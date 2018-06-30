/*
Это сервер на nodeJS для отдачи статики
  - так получиться ускорить процесс разработки UI
    - меняя файлы статики, мы сразу сможем увидеть изменения в браузере при перезагрузке страницы
  - запустить сервер [ node debugStaticServer.js ]
  - (*) мы использовали фреймворк, который необходимо установить в системе
    - установка Express - [ npm install express ]
 */

const hostname = '127.0.0.1';
const port = 3000;
const staticPath = 'src/main/resources/static/';

// берём Express - это фреймворк, делающий создание большинства сайтов очень простым.
var express = require('express'); // подключаем модуль
var app = express();              // создаём Express-приложение

// Всё, что вы сложите в папку /public, может быть запрошено из браузера и будет отображено.
app.use(express.static('src/main/resources/static/'));

// запускаем сервер на порту 8080
app.listen(port);
console.log(`Server running at http://${hostname}:${port}/`); // отправляем сообщения в лог
console.log(`All static content from: ./${staticPath}`);
