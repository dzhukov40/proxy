import { HttpClient } from '@angular/common/http';
import {Injectable} from "@angular/core";
import {Configuration} from "../model/configuration";


@Injectable()
export class ConfigurationService {


  configuration: Configuration;
  profileList: string[];

  // мы тут инжектим класс через конструктор для [http] запросов
  constructor(private http: HttpClient) {}

  getConfiguration() {
    this.http.get('http://localhost:8080/configuration').subscribe( (data:Configuration) => this.configuration=data );
    return this.configuration;
  }

  getProfileList(): string[] {
    this.http.get('http://localhost:8080/configuration/profile/list').subscribe( (data:string[]) => { return data } );
    return null;
  }

}
