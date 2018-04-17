import { Component } from '@angular/core';
import { PipesService } from './services/pipes.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [PipesService]
})
export class AppComponent {
  title = 'Poxy';

  pipes = [];

  constructor(private pipeService: PipesService) {}

  ngOnInit() {
    this.pipeService.getPipes().subscribe( pipes => { console.log })
  }


}
