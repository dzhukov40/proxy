import { HttpClient } from '@angular/common/http';
import {Injectable} from "@angular/core";
import {Configuration} from "../model/configuration";
import {Observable} from "rxjs/Observable";
import {environment} from "../../environments/environment";
import {getResponseURL} from "@angular/http/src/http_utils";
import "rxjs/add/operator/map" // методы для класса [Observable] надо видимо подключать по кусочкам

// пример -> https://blog.angular-university.io/angular-http/
@Injectable()
export class ConfigurationService {


  configuration: Configuration;
  profileList: string[];

  // мы тут инжектим класс через конструктор для [http] запросов
  constructor(private http: HttpClient) {}

  // возвращаемый тип это для асинхронной работы, на него надо будет подписаться
  // видим что мы типизировали запрос [get] указав к какому типу привести ответ
  getConfiguration(): Observable<Configuration> {
    return this.http.get<Configuration>(environment.apiUrl + '/configuration');
  }

  getProfileList(): Observable<string[]> {
    return this.http.get<string[]>(environment.apiUrl + '/configuration/profile/list');
  }

}
