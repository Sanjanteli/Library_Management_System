import { Component } from '@angular/core';
import { AuthenticationServiceService } from './authentication-service.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Library Management System';
  constructor(  private authenticationService: AuthenticationServiceService,
    public loginService:AuthenticationServiceService,private router: Router) { }
}
