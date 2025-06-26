import { Component } from '@angular/core';
import { RouterOutlet, NavigationEnd, RouterModule } from '@angular/router';
import { HeaderComponent } from './layout/header/header.component';
import { SidebarComponent } from './layout/sidebar/sidebar.component';
import { filter } from 'rxjs';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HeaderComponent,SidebarComponent, CommonModule,RouterModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  showLayout = true;
  constructor(private router: Router){
     this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe((event: any) => {
      const excludedRoutes = ['/', '/login'];
      this.showLayout = !excludedRoutes.includes(event.urlAfterRedirects);
    });
  }
}
