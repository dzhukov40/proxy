

<!-- это подключение [ajax] -->
src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"

var host = "localhost";
var port = 8080;




export function getBackEnviroment() {
    return new BackEnviroment(host, port);
}


class BackEnviroment {

    constructor(host, port) {
        this.host = host;
        this.port = port;
    }


    get(url) {
        alert(url);
    }





}




function say() {
    var msg = $('#msgform').serialize();
    var name = $('#nameform').serialize();

    var settings = {
        "method": "POST",
        "crossDomain": true,
        "url": "http://" + host + ":" + port + "/chat/say",
        "data": name + "&" + msg
    };

    $.ajax(settings).done(function (response) {
        $('#msgform').trigger("reset");
        loadHistory();
    }).fail(function (jqXHR, textStatus) {
        alert(jqXHR.status + " " + jqXHR.statusText + ". " + jqXHR.responseText);
        console.log(jqXHR.status + " " + jqXHR.statusText + ". " + jqXHR.responseText);
    });
}

function login() {
    var name = $('#nameform').serialize();

    var settings = {
        "method": "POST",
        "crossDomain": true,
        "url": "http://" + host + ":" + port + "/chat/login",
        "data": name
    };

    $.ajax(settings).done(function (response) {
        loadHistory();
    }).fail(function (jqXHR, textStatus) {
        alert(jqXHR.status + " " + jqXHR.statusText + ". " + jqXHR.responseText);
        console.log(jqXHR.status + " " + jqXHR.statusText + ". " + jqXHR.responseText);
    });
}

function logout() {
    var name = $('#nameform').serialize();

    var settings = {
        "method": "POST",
        "crossDomain": true,
        "url": "http://" + host + ":" + port + "/chat/logout",
        "data": name
    };

    $.ajax(settings).done(function (response) {
        loadHistory();
    }).fail(function (jqXHR, textStatus) {
        alert(jqXHR.status + " " + jqXHR.statusText + ". " + jqXHR.responseText);
        console.log(jqXHR.status + " " + jqXHR.statusText + ". " + jqXHR.responseText);
    });
}

<!-- тестовая ф-ия, нет запроса на Бек -->
function test() {

    $("#history").html("<br>приветики");

}



setInterval(test, 10000);