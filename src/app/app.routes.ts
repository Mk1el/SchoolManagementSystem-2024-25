
import { Routes } from '@angular/router';
import { LibraryComponent } from './library/library.component';
import { LayoutComponent } from './layout/layout.component';
import { authGuard } from './auth/auth.guard';
import { LoginComponent } from './auth/login/login.component';

export const routes: Routes = [
// Public route for login
{
path: 'login',
component: LoginComponent,
data: { title: 'Login' }
},
// Protected app shell
{
path: '',
component: LayoutComponent,
canActivate: [authGuard],
children: [
// default redirect inside shell
{ path: '', redirectTo: 'library', pathMatch: 'full' },
// Library feature
{
path: 'library',
component: LibraryComponent,
data: { title: 'Library' }
}
]
},
// wildcard redirect
{
path: '**',
redirectTo: ''
}
];