
import { Routes } from '@angular/router';
import { LibraryComponent } from './library/library.component';
import { LayoutComponent } from './layout/layout.component';
import { authGuard } from './auth/auth.guard';
import { LoginComponent } from './auth/login/login.component';
import { ConfigSchoolsComponent } from './config-schools/config-schools.component';
import { StudentsComponent } from './students/students.component';

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
path: 'config-schools',
component: ConfigSchoolsComponent,
data: { title: 'Schools-Configuration' }
},
{
path: 'students',
component: StudentsComponent,
data: { title: 'Students' }
},
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