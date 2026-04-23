import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http:HttpClient) {

   
  }

  register(data:any):Observable<any>
  {
    return this.http.post('http://localhost:8080/auth/register',data);
  }

  login(data:any):Observable<any>
  {
    return this.http.post('http://localhost:8080/auth/login', data, {
      withCredentials: true
    })
  }
  
  logout() {
  return this.http.post(
    'http://localhost:8080/auth/logout',
    {},
    { withCredentials: true }
  );
  }
  checkAuth(): Observable<any>
  {
    return this.http.get<any>('http://localhost:8080/auth/me',{withCredentials:true});
  }

}
