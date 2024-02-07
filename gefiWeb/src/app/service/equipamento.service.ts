import { Equipamento } from './../models/equipamento';
import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { catchError, tap, map } from 'rxjs/operators';
import { environment } from '../../environments/environment';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

const apiUrl = `${environment.apiUrl}/equipamentos`;

@Injectable({
  providedIn: 'root'
})
export class EquipamentoService {

  constructor(private http: HttpClient) { }

  
  getEquipamentos(): Observable<Equipamento[]> {
    return this.http.get<Equipamento[]>(apiUrl)
      .pipe(
        tap(equipamentos => console.log('leu os equipamentos')),
        catchError(this.handleError('getEquipamentos', []))
      );
  }

  getEquipamento(id: number): Observable<Equipamento> {
    const url = `${apiUrl}/${id}`;
    return this.http.get<Equipamento>(url).pipe(
      tap(_ => console.log(`leu o equipamento id=${id}`)),
      catchError(this.handleError<Equipamento>(`getEquipamento id=${id}`))
    );
  }

  addEquipamento (equipamento): Observable<Equipamento> {
    return this.http.post<Equipamento>(apiUrl, equipamento, httpOptions).pipe(
      // tslint:disable-next-line:no-shadowed-variable
      tap((equipamento: Equipamento) => console.log(`adicionou o produto com w/ id=${equipamento.id}`)),
      catchError(this.handleError<Equipamento>('addEquipamento'))
    );
  }

  updateEquipamento(id, equipamento): Observable<any> {
    const url = `${apiUrl}/${id}`;
    return this.http.patch<Equipamento>(url, equipamento, httpOptions).pipe(
      tap((equipamento: Equipamento) => console.log(`atualiza o equipamento com id=${id}`)),
      catchError(this.handleError<any>('updateEquipamento'))
    );
  }

  deleteEquipamento (id): Observable<Equipamento> {
    const url = `${apiUrl}/${id}`;

    return this.http.delete<Equipamento>(url, httpOptions).pipe(
      tap(_ => console.log(`remove equipamento com id=${id}`)),
      catchError(this.handleError<Equipamento>('deleteEquipamento'))
    );
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error);

      return of(result as T);
    };
  }
}