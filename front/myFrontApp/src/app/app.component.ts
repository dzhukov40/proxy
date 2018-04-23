import { Component } from '@angular/core';
import {ConfigurationService} from "./services/configuration.service";
import {Configuration} from "./model/configuration";
import {Profile} from "./model/profile";
import {pipe} from "rxjs/Rx";
import {Pipe} from "./model/pipe";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [ConfigurationService]
})
export class AppComponent {
  title = 'Poxy';

  color;

  car = 'volvo';

  configuration: Configuration;
  //profileList: string[];


  name= '';


  profile: Profile = {
    name: "локальный", pipes: [
      {name: 'test1', localhostPort: 1010, remoteIP: '192.168.56.105', remotePort: 210},
      {name: 'test2', localhostPort: 1010, remoteIP: '192.168.56.105', remotePort: 210},
      {name: 'test3', localhostPort: 1010, remoteIP: '192.168.56.105', remotePort: 210}
    ]
  };

  curProfile: Profile;



  constructor(private configurationService: ConfigurationService) {}



    startProxy(): void {
      //this.pipes = this.configuration.profiles[0].pipes;

    }


  profileList: string[] = [];




  setProfile(profile: string): void {
    //console.log(profile);
    // this.curProfile = this.profiles.find(value => value === profile);
  }

  /*
   * вызывается один раз после установки свойств компонента, которые участвуют в привязке.
   * Выполняет инициализацию компонента
   * - тянем с бека все необходимые данные
   */
  ngOnInit() {
    this.configurationService.getConfiguration().subscribe( response => {
      this.configuration = response;
      this.curProfile = this.configuration.profiles[0];
      console.log("ngOnInit: configuration -> " + this.configuration);
    });

    this.configurationService.getProfileList().subscribe( response => {
      this.profileList = response;
      console.log("ngOnInit: profiles -> " + this.profileList);
    });

  }





}
