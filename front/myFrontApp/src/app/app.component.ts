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


  name= '';

  pipes = [
    {name: 'test1', localPort: '1010', remoteIp: '192.168.56.105', remotePort: '210'},
    {name: 'test2', localPort: '1020', remoteIp: '192.168.56.103', remotePort: '220'},
    {name: 'test3', localPort: '1030', remoteIp: '192.168.56.144', remotePort: '230'},
  ]

  constructor(private configurationService: ConfigurationService) {}

/*
  pipes = [];
ngOnInit() {
    this.pipeService.getPipes().subscribe( pipes => { console.log })
  }*/



  ngOnInit() {
      this.configurationService.getConfiguration().subscribe( (data:Configuration) => console.log );
    }




    startProxy(): void {
      this.configurationService.getConfiguration().subscribe( (data:Configuration) => this.configuration=data );

      console.log(this.configuration);

    }








}
