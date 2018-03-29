package ru.doneathome.service;

public enum ServerStatus {

    /**
     * удаленный порт к которому хотим подлючиться не доступен
     */
    WAIT_REMOTE_PORT_AVAILABLE,

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
