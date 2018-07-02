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
// глобальные переменные
// ----------------------------------------------------------------------

// ф-ия постоянного инкрементирования
var incr = (function () {
    var i = 0;

    return function () {
        return i++;
    }
})();





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
        '                   <tr class="main_table_column" column_id='+ incr() +'>\n' +
        '                   <th>Россия</th>\n' +
        '                   <th>Великобритания</th>\n' +
        '                   <th>Европа</th>\n' +
        '                   <th>Длина ступни, см</th>\n' +
        '                   </tr>\n' +
        '                   <tr class="main_table_column" column_id='+ incr() +'><td>34,5</td><td>3,5</td><td>36</td><td>23</td></tr>\n' +
        '                   <tr class="main_table_column" column_id='+ incr() +'><td>35,5</td><td>4</td><td>36⅔</td><td>23–23,5</td></tr>\n' +
        '                   <tr class="main_table_column" column_id='+ incr() +'><td>36</td><td>4,5</td><td>37⅓</td><td>23,5</td></tr>\n' +
        '                   <tr class="main_table_column" column_id='+ incr() +'><td>36,5</td><td>5</td><td>38</td><td>24</td></tr>\n' +
        '                   <tr class="main_table_column" column_id='+ incr() + '><td>37</td><td>5,5</td><td>38⅔</td><td>24,5</td></tr>' +
        '                 </table>';


    $('.main_table').remove(); // очищаем все, что было
    $('.div_main_table').append(initStringBody); // вставляем

}

function addTableColumn(event) {

    let addColumnString = '<tr class="main_table_column" column_id='+ incr() +'><td>34,5</td><td>3,5</td><td>36</td><td>23</td></tr>\n';

    console.log(event.currentTarget);


    $('.main_table').append(addColumnString);

}

function getCountTableColumn() {

    let rowCount = $('[column_id]').length - 1;

    console.log('getCountTableColumn() return: ' + rowCount);
    return rowCount;
}







