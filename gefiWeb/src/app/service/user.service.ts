import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Usuario } from '../models/usuario';
import { catchError, tap, map } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};
const apiUrl = `${environment.apiUrl}/usuarios`;



@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) { }


  
  getUsuarios(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(apiUrl)
      .pipe(
        tap(usuarios => console.log('leu os Usuarios')),
        catchError(this.handleError('getUsuarios', []))
      );
  }

  getUsuario(id: number): Observable<Usuario> {
    const url = `${apiUrl}/${id}`;
    return this.http.get<Usuario>(url).pipe(
      tap(_ => console.log(`leu o Usuario id=${id}`)),
      catchError(this.handleError<Usuario>(`getUsuario id=${id}`))
    );
  }

  addUsuario (usuario): Observable<Usuario> {
    return this.http.post<Usuario>(apiUrl, usuario, httpOptions).pipe(
      // tslint:disable-next-line:no-shadowed-variable
      tap((usuario: Usuario) => console.log(`adicionou o usuario com w/ id=${usuario.id}`)),
      catchError(this.handleError<Usuario>('addUsuario'))
    );
  }

  updateUsuario(id, Usuario): Observable<any> {
    const url = `${apiUrl}/${id}`;
    return this.http.put(url, Usuario, httpOptions).pipe(
      tap(_ => console.log(`atualiza o Usuario com id=${id}`)),
      catchError(this.handleError<any>('updateUsuario'))
    );
  }

  deleteUsuario (id): Observable<Usuario> {
    const url = `${apiUrl}/${id}`;

    return this.http.delete<Usuario>(url, httpOptions).pipe(
      tap(_ => console.log(`remove o usuario com id=${id}`)),
      catchError(this.handleError<Usuario>('deleteUsuario'))
    );
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error);

      return of(result as T);
    };
  }
}
  
  
