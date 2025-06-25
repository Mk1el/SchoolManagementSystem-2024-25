import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatMenuModule } from '@angular/material/menu';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
  CommonModule,
  MatToolbarModule,
  MatIconModule,
  MatMenuModule,
  MatButtonModule
],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  todayDate: string = '';
  user: any = {
    username: '',
    role: ''
  };

  constructor(private router: Router) {}

  ngOnInit(): void {
    // Format date: e.g. Monday, 24 June 2025
    const today = new Date();
    this.todayDate = today.toLocaleDateString('en-US', {
      weekday: 'long',
      day: '2-digit',
      month: 'long',
      year: 'numeric'
    });

    const userData = localStorage.getItem('auth_user');
    if (userData) {
      const parsed = JSON.parse(userData);
      this.user.username = parsed.username;
      this.user.role = parsed.role;
    }
  }

  logout(): void {
    localStorage.clear();
    this.router.navigate(['/']);
  }
}
