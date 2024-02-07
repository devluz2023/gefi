import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { AutenticadorService } from '../app/service/autenticador.service';
import { Usuario } from '../app/models/usuario';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'gefi-web';
  currentUser: Usuario;


  constructor(
      private router: Router,
      private authenticationService: AutenticadorService
  ) {
      this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }

  logout() {
      this.authenticationService.logout();
      this.router.navigate(['/login']);
  }
}
