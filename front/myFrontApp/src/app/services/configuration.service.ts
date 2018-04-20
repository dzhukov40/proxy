import { HttpClient } from '@angular/common/http';
import {Injectable} from "@angular/core";


@Injectable()
export class ConfigurationService {

  // мы тут инжектим класс через конструктор для [http] запросов
  constructor(private http: HttpClient) {}

  getConfiguration() {
    return this.http.get('http://localhost:8080/configuration');
  }

}
