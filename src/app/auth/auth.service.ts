import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'https://api.example.com/auth/login';
  constructor(private http: HttpClient) { }

  login(credentials: {email: string; password: string}): Observable<{token: string; user: any}>{
    return this.http.post<{token: string; user: any}>(this.apiUrl, credentials)
  }
}
