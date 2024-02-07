import { Injectable } from '@angular/core';
import { HttpClient,  HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { BehaviorSubject, Observable, of, throwError } from 'rxjs';
import { catchError, tap, map } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { Usuario } from '../models/usuario';


const httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
};

const apiUrl = `${environment.apiUrl}/autenticar`;

  

@Injectable({
  providedIn: 'root'
})
export class AutenticadorService {

  private currentUserSubject: BehaviorSubject<Usuario>;
  public currentUser: Observable<Usuario>;

  constructor(private http: HttpClient) {
      this.currentUserSubject = new BehaviorSubject<Usuario>(JSON.parse(localStorage.getItem('currentUser')));
      this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): Usuario {
      return this.currentUserSubject.value;
  }

  
  login(login: string, senha: string) {
   
    return this.http.post<any>(apiUrl, { login,senha })
        .pipe(map(usuario => {
            // store user details and basic auth credentials in local storage to keep user logged in between page refreshes
            usuario.authdata = window.btoa(login + ':' + senha);
            localStorage.setItem('currentUser', JSON.stringify(usuario));
            this.currentUserSubject.next(usuario);
            return usuario;
        }));
  }


  logout() {
      // remove user from local storage to log user out
      localStorage.removeItem('currentUser');
      this.currentUserSubject.next(null);
  }



  
}


