import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { RouterModule } from '@angular/router';
@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [CommonModule, MatListModule, MatIconModule, RouterModule],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent implements OnInit{
  role: string ='';
  filteredMenu: any[] = [];
 
   allMenuItems = [
    { label: 'Dashboard', icon: 'dashboard', route: '/layout', roles: ['SUPER_USER', 'LIBRARIAN', 'SCHOOL_ADMIN', 'USER'] },
    { label: 'Configure Schools', icon: 'school', route: '/layout/config-schools', roles: ['SUPER_USER'] },
    { label: 'Bank Accounts', icon: 'account_balance', route: '/layout/bank-accounts', roles: ['SUPER_USER'] },
    { label: 'Classes', icon: 'class', route: '/layout/classes', roles: ['SUPER_USER', 'SCHOOL_ADMIN'] },
    { label: 'Streams', icon: 'device_hub', route: '/layout/streams', roles: ['SUPER_USER', 'SCHOOL_ADMIN'] },
    { label: 'Subjects', icon: 'menu_book', route: '/layout/subjects', roles: ['SUPER_USER', 'SCHOOL_ADMIN'] },
    { label: 'Grades', icon: 'grading', route: '/layout/grades', roles: ['SUPER_USER', 'SCHOOL_ADMIN'] },
    { label: 'Library', icon: 'library_books', route: '/layout/library', roles: ['LIBRARIAN'] },
    { label: 'Students', icon: 'people', route: '/layout/students', roles: ['SCHOOL_ADMIN', 'USER'] },
    { label: 'Logout', icon: 'logout', route: '/logout', roles: ['SUPER_USER', 'LIBRARIAN', 'SCHOOL_ADMIN', 'USER'] }
  ];

   ngOnInit(): void {
    const storedUser = localStorage.getItem('auth_user')
    if(storedUser){
      const parsed = JSON.parse(storedUser);
      this.role = parsed?.role?.toUpperCase();
    }
    this.filteredMenu = this.allMenuItems.filter(item =>
      item.roles.includes(this.role)
    )
  }

}
