
import { Routes } from '@angular/router';
import { LibraryComponent } from './library/library.component';
import { LayoutComponent } from './layout/layout.component';
import { authGuard } from './auth/auth.guard';
import { LoginComponent } from './auth/login/login.component';

export const routes: Routes = [
// Public route for login
{
path: '',
component: LoginComponent,
data: { title: 'Login' }
},
{
path: 'layout',
component: LayoutComponent,
canActivate: [authGuard],
children: [
{ path: '', redirectTo: 'login', pathMatch: 'full' },
{
path: 'library',
component: LibraryComponent,
data: { title: 'Library' }
}
]
},
{
path: '**',
redirectTo: ''
}
];