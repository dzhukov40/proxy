import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Poxy';

  pipes = [
    {name: 'test1', localPort: '1010'},
    {name: 'test2', localPort: '1020'},
    {name: 'test3', localPort: '1030'}
  ]


}
