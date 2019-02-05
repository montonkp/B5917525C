import {Component} from '@angular/core';
import {TokenStorage} from './service/token-storage';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  private token: TokenStorage;

  logout() {
    this.token.signOut();
  }

}
