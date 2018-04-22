import { Component } from '@angular/core';
import {ConfigurationService} from "./services/configuration.service";
import {Configuration} from "./model/configuration";

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

  pipes = [
    {name: 'test1', localPort: '1010', remoteIp: '192.168.56.105', remotePort: '210'},
    {name: 'test2', localPort: '1020', remoteIp: '192.168.56.103', remotePort: '220'},
    {name: 'test3', localPort: '1030', remoteIp: '192.168.56.144', remotePort: '230'},
  ]

  constructor(private configurationService: ConfigurationService) {}



    startProxy(): void {
      //this.configuration=this.configurationService.getConfiguration();
      //console.log(this.configuration);

      //this.profileList = this.configurationService.getProfileList();
      //console.log(this.profileList);

      this.profiles = this.configurationService.getProfileList();
      console.log("ngOnInit: " + this.profiles);
    }


  profiles: string[] = [];

  curProfile: string = this.profiles[0];


  setProfile(profile: string): void {
    //console.log(profile);
    this.curProfile = this.profiles.find(value => value === profile);
  }


  ngOnInit() {
    this.profiles = this.configurationService.getProfileList();
    console.log("ngOnInit: " + this.profiles);
  }





}
