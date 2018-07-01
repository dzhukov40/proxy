/*
  Будет один *.js файл в котором опишем все ф-ии
 */


// ----------------------------------------------------------------------
// подключаем jquery
// ----------------------------------------------------------------------

// подключаем jquery (*) gbitv это в index.html
// <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>


// (*) это второй вариант подключения, но тогда проблемы использовать ф-ии в [index.html]
// http://kulibaba.net/programming/javascript/include-scripts
// Но как быть, если вам необходимо подключить скрипт внутри другого скрипта?
/*function include(url) {
    var script = document.createElement('script');
    script.src = url;
    document.getElementsByTagName('head')[0].appendChild(script);
}*/
// теперь у нас есть такая функция, которая прокинит это в heder нашей страницы
//include("https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js");


// определяем куда с фронта слать запросы
var host = "localhost";
var port = 8080;


function sayHello(){

    alert("sayHello()");
   // testPipeGet('ураааа');

}









// ----------------------------------------------------------------------
// выбор профиля
// ----------------------------------------------------------------------

function initProfileForm() {

    let initStringBody = ' <form onchange="sayHello()"><select Профиль class="form_profile">\n' +
        '                   <option атрибуты>Пункт 1</option>\n' +
        '                   <option атрибуты>Пункт 2</option>\n' +
        '                 </select>' +
        '                 </form>';


    $('.form_profile').remove(); // очищаем все, что было
    $('.div_form_profile').append(initStringBody); // вставляем
}




// ----------------------------------------------------------------------
// работа с таблицей
// ----------------------------------------------------------------------


function initTableForm() {

    let initStringBody = '<table class="main_table"><caption>Карта проксирования портов</caption>\n' +
        '                   <tr>\n' +
        '                   <th>Россия</th>\n' +
        '                   <th>Великобритания</th>\n' +
        '                   <th>Европа</th>\n' +
        '                   <th>Длина ступни, см</th>\n' +
        '                   </tr>\n' +
        '                   <tr><td>34,5</td><td>3,5</td><td>36</td><td>23</td></tr>\n' +
        '                   <tr><td>35,5</td><td>4</td><td>36⅔</td><td>23–23,5</td></tr>\n' +
        '                   <tr><td>36</td><td>4,5</td><td>37⅓</td><td>23,5</td></tr>\n' +
        '                   <tr><td>36,5</td><td>5</td><td>38</td><td>24</td></tr>\n' +
        '                   <tr><td>37</td><td>5,5</td><td>38⅔</td><td>24,5</td></tr>' +
        '                 </table>';


    $('.main_table').remove(); // очищаем все, что было
    $('.div_main_table').append(initStringBody); // вставляем

}





