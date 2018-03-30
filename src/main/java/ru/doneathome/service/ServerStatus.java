package ru.doneathome.service;

public enum ServerStatus {

    /**
     * Мы стартуем, это начальное состояние
     */
    STARTING,

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
