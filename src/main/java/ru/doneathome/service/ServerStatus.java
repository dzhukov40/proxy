package ru.doneathome.service;

public enum ServerStatus {

    /**
     * ожидаем подключение
     */
    WAIT_CONNECTION,

    /**
     * есть активное подключение
     */
    HAS_ACTIVE_CONNECTION,

    /**
     * сервер остановлен
     */
    STOPPED

}
