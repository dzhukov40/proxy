import { HttpClient } from '@angular/common/http';
import {Injectable} from "@angular/core";


@Injectable()
export class PipesService {

  // мы тут инжектим класс через конструктор для [http] запросов
  constructor(private http: HttpClient) {}

  getPipes() {
    return this.http.get('http://localhost:8080/pipe/getPipes')
  }

  pipes = [
    {name: 'test1', localPort: '1010', remoteIp: '192.168.56.105', remotePort: '210'},
    {name: 'test2', localPort: '1020', remoteIp: '192.168.56.103', remotePort: '220'},
    {name: 'test3', localPort: '1030', remoteIp: '192.168.56.144', remotePort: '230'},
  ]

}
